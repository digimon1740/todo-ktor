import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import config.DatabaseInitializer
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.jackson.jackson
import io.ktor.routing.Routing
import service.TodoService
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun Application.main() {
    install(DefaultHeaders)
    install(CallLogging)
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
            registerModule(JavaTimeModule().apply {
                addDeserializer(LocalDateTime::class.java,
                    LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME))
            })
        }
    }
    install(Routing) {
        val service = TodoService()
        todo(service)
    }
    DatabaseInitializer.init()
}
