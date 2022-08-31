import co.touchlab.kermit.Logger
import com.beust.klaxon.Klaxon
import id.walt.model.oidc.SIOPv2Request
import id.walt.model.oidc.VCClaims
import id.walt.model.oidc.klaxon
import id.walt.services.did.DidService
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

object SiopServer {

    private val logger = Logger.withTag("SiopServer")

    private val _port = 8091

    fun start() {
        logger.i { "SiopServer starting at $_port..." }
        embeddedServer(CIO, port = _port) {
            install(CallLogging)
            install(ContentNegotiation) {
                json()
            }

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

                                println("==================================")
                                println("NEW SIOPv2 REQUEST FOR CREDENTIALS")
                                println("Session-ID: ${initCredPresSess.id}")

                                val availableDids = DidService.listDids()
                                println("Available DIDs: $availableDids")

                                if (availableDids.isEmpty()) {
                                    throw IllegalStateException("No DIDs available")
                                }


                                val did = if (availableDids.size == 1) {
                                    println("Chose only available DID.")
                                    availableDids.first()
                                } else {
                                    print("Please enter DID: ")
                                    readln()
                                }

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

                                val selectedCredentials = if (contPresSess.presentableCredentials!!.size == 1) {
                                    println("Chose only available credential")
                                    listOf(contPresSess.presentableCredentials!!.first())
                                } else {
                                    print("Please enter selected credentials:")
                                    val theCreds = readln()

                                    theCreds.let {
                                        klaxon.parseArray(it) ?: throw IllegalArgumentException("No selected credentials given")
                                    }
                                }


                                println("FULFILLING PRESENTATION...")
                                val siopResp = CredentialPresentationManager.fulfillPresentation(
                                    initCredPresSess.id,
                                    selectedCredentials
                                )
                                val presSiopResp = PresentationResponse.fromSiopResponse(siopResp)

                                println("id_token: " + presSiopResp.id_token)
                                println("vp_token: " + presSiopResp.vp_token)
                                println("state:    " + presSiopResp.state)

                                call.respond(presSiopResp)
                            }

                            get("initPassiveIssuance") {
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


                                val session =
                                    CredentialPresentationManager.initCredentialPresentation(req, passiveIssuance = true)

                                println("==================================")
                                println("NEW SIOPv2 ISSUANCE REQUEST FOR CREDENTIALS")
                                println("Request  Session-ID: ${session.id}")

                                val availableDids = DidService.listDids()
                                println("Available DIDs: $availableDids")

                                if (availableDids.isEmpty()) {
                                    throw IllegalStateException("No DIDs available")
                                }


                                val did = if (availableDids.size == 1) {
                                    println("Chose only available DID.")
                                    availableDids.first()
                                } else {
                                    print("Please enter DID: ")
                                    readln()
                                }

                                val sessionId = session.id

                                println("Continuing presentation...")
                                val contPresSess =
                                    CredentialPresentationManager.continueCredentialPresentationFor(sessionId, did)

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

                                issuanceSession.credentials!!.forEachIndexed { i, vc ->
                                    println("Received credential #${i + 1}: $vc")
                                }
                                println("SIOPv2 ISSUANCE SUCEEDED FOR ${issuanceSession.credentials!!.size} CREDENTIAL(S)")
                                println("Request  Session-ID: ${session.id}")
                                println("Issuance Session-ID: ${issuanceSession.id}")
                                println("==================================")


                                call.respondText(klaxon.toJsonString(issuanceSession))
                            }
                        }
                    }
                }
            }
        }.start(wait = true)
    }
}
