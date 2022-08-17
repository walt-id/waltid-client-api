import co.touchlab.kermit.Logger
import id.walt.servicematrix.ServiceMatrix

fun main() {
    Logger.i { "Running Client API..." }
    ServiceMatrix("service-matrix.properties")

    id.walt.vclib.registry.VcTypeRegistry

    Logger.i { "Running SIOP Server..." }
    SiopServer.start()
}
