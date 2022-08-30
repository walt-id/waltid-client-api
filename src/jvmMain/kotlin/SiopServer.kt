import co.touchlab.kermit.Logger
import com.beust.klaxon.Klaxon
import id.walt.model.oidc.SIOPv2Request
import id.walt.model.oidc.VCClaims
import id.walt.model.oidc.klaxon
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object SiopServer {

    private val logger = Logger.withTag("SiopServer")

    private val _port = 8091

    fun start() {
        logger.i { "SiopServer starting at $_port..." }
        embeddedServer(CIO, port = _port) {
            install(CallLogging)

            routing {

                route("api") {
                    route("wallet") {
                        route("siopv2") {
                            get("initPresentation") {
                                println("> step: initCredentialPresentation")


                                val requiredParams = setOf("redirect_uri", "nonce", "claims")
                                if (requiredParams.any { call.parameters[it].isNullOrEmpty() })
                                    throw IllegalArgumentException("HTTP context missing mandatory query parameters")
                                val req = SIOPv2Request(
                                    redirect_uri = call.parameters["redirect_uri"]!!,
                                    response_mode = call.parameters["response_mode"] ?: "fragment",
                                    nonce = call.parameters["nonce"],
                                    claims = klaxon.parse<VCClaims>(call.parameters["claims"]!!)!!,
                                    state = call.parameters["state"]
                                )

                                val initCredPresSess =
                                    CredentialPresentationManager.initCredentialPresentation(req, passiveIssuance = false)

                                GlobalScope.launch {
                                    println("==================================")
                                    println("NEW SIOPv2 REQUEST FOR CREDENTIALS")
                                    println("Session-ID: ${initCredPresSess.id}")

                                    print("Please enter DID: ")
                                    val did = readln()

                                    println("Continuing presentation...")
                                    val contPresSess = CredentialPresentationManager.continueCredentialPresentationFor(
                                        initCredPresSess.id,
                                        did
                                    )

                                    contPresSess.presentableCredentials!!.forEach {

                                        println("PRESENTABLE CREDENTIAL: $it - ${Klaxon().toJsonString(it)}")
                                    }

                                    if (contPresSess.presentableCredentials!!.isEmpty()) {
                                        println("Warning: presentableCredentials is empty (local custodian used)!")
                                    }

                                    print("Please enter selected credentials:")
                                    val theCreds = readln()

            val selectedCredentials = theCreds.let { klaxon.parseArray<PresentableCredential>(it) }
                ?: throw BadRequestResponse("No selected credentials given")

            println("FULFILLING PRESENTATION...")
            val siopResp = CredentialPresentationManager.fulfillPresentation(initCredPresSess.id, selectedCredentials)
            val presSiopResp = PresentationResponse.fromSiopResponse(siopResp)

            println("id_token: " + presSiopResp.id_token)
            println("vp_token: " + presSiopResp.vp_token)
            println("state:    " + presSiopResp.state)
        }


        ctx.status(HttpCode.NO_CONTENT)
    }

    // RECV

    fun initPassiveIssuance(ctx: Context) {
        val req = SIOPv2Request.fromHttpContext(ctx)
        val session = CredentialPresentationManager.initCredentialPresentation(req, passiveIssuance = true)

        GlobalScope.launch {
            println("==================================")
            println("NEW SIOPv2 ISSUANCE REQUEST FOR CREDENTIALS")
            println("Request  Session-ID: ${session.id}")

            print("Please enter DID: ")
            val did = readln()

            val sessionId = session.id
            ctx.json(CredentialPresentationManager.continueCredentialPresentationFor(sessionId, did))

            println("Continuing presentation...")
            val contPresSess = CredentialPresentationManager.continueCredentialPresentationFor(sessionId, did)

            if (contPresSess.presentableCredentials!!.isNotEmpty()) {
                println("Warning: presentableCredentials is not empty?")
            }

            println("FULFILLING PRESENTATION...")


            val emptyPresentation = emptyList<PresentableCredential>() // requires empty presentation

            val issuanceSession = CredentialPresentationManager.fulfillPassiveIssuance(
                sessionId,
                emptyPresentation,
                UserInfo(did) // ???, email in walletkit, probably ignored though
            )
            ctx.result(issuanceSession.id)

            issuanceSession.credentials!!.forEachIndexed { i, vc ->
                println("Received credential #${i + 1}: $vc")
            }
            println("SIOPv2 ISSUANCE SUCEEDED FOR ${issuanceSession.credentials!!.size} CREDENTIAL(S)")
            println("Request  Session-ID: ${session.id}")
            println("Issuance Session-ID: ${issuanceSession.id}")
            println("==================================")
        }
    }

}
