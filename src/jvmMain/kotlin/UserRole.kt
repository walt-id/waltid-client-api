import io.javalin.core.security.RouteRole

enum class UserRole : RouteRole {
    UNAUTHORIZED,
    AUTHORIZED
}
