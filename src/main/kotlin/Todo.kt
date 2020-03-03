import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.`java-time`.datetime

object Todos : IntIdTable() {
    val content = text("name") // Column<String>
    val done = bool("done")
    val createdAt = datetime("created_at").index()
    val updatedAt = datetime("updated_at")
}