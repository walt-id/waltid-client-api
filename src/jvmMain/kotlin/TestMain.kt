import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun main() {
    println("Running...")
    val x = amain("the.server.req")
    println("RESULT: $x")
    Thread.sleep(1000)
}

fun amain(req: String): Int {
    GlobalScope.launch {
        abc(req)
    }
    return 200
}

fun abc(req: String) {
    println("New request: $req")
    println("Please choose...")
    Thread.sleep(2000)
    println("Thank you for our choice.")
    println("Contacting $req...")
    Thread.sleep(2000)
    println("Done")
}
