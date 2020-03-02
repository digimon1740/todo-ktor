import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get

fun Application.main() {
    install(DefaultHeaders)
    install(CallLogging)
    install(Routing) {
        root()
        hello()
    }
}

// Extracted route
fun Routing.root() {
    get("/") {
        call.respondText("Hello World!", ContentType.Text.Html)
    }
}

fun Routing.hello() {
    get("/hello") {
        call.respondText("My Example Blog2", ContentType.Text.Html)
    }
}