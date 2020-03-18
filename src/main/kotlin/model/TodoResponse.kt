package main.kotlin.model

import main.kotlin.entity.Todo
import java.time.LocalDateTime

data class TodoResponse(val id: Int,
                        val content: String,
                        val done: Boolean,
                        val createdAt: LocalDateTime,
                        val updatedAt: LocalDateTime) {

    companion object {
        fun of(todo: Todo) =
            TodoResponse(
                id = todo.id.value,
                content = todo.content,
                done = todo.done,
                createdAt = todo.createdAt,
                updatedAt = todo.updatedAt
            )
    }
}