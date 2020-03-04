package entity

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.`java-time`.datetime

// Table scheme
object Todos : IntIdTable() {
    val content = text("name") // Column<String>
    val done = bool("done")
    val createdAt = datetime("created_at").index()
    val updatedAt = datetime("updated_at")
}

// Entity
class Todo(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Todo>(Todos)

    var content by Todos.content
    var done by Todos.done
    var createdAt by Todos.createdAt
    var updatedAt by Todos.updatedAt
}