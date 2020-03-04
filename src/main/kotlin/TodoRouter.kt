import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.request.receive
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.delete
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.put
import io.ktor.routing.route
import service.TodoService


fun Routing.todo(service: TodoService) {
    route("todos") {
        get {
            val startsAt = call.request.queryParameters["starts_at"].toString()
            val beginsAt = call.request.queryParameters["begins_at"].toString()

            call.respondText("Hello dao.Todos ", ContentType.Text.Html)
        }
        get("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@get
            val todo = service.getById(id)
            call.respondText("Hello dao.Todos $todo", ContentType.Text.Html)
        }
        post {
            val body = call.receive<Map<String, Any>>()

        }
        put("/{id}") {

        }
        delete("/{id}") {

        }
    }
}