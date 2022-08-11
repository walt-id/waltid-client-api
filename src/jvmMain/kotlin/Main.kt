import co.touchlab.kermit.Logger
import id.walt.servicematrix.ServiceMatrix

fun main() {
    Logger.i { "Running Client API..." }
    ServiceMatrix("service-matrix.properties")

    Logger.i { "Running SIOP Server..." }
    SiopServer.start()
}
