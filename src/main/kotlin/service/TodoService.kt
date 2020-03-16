package main.kotlin.service

import main.kotlin.config.query
import main.kotlin.entity.Todo
import main.kotlin.model.TodoRequest
import main.kotlin.model.TodoResponse
import java.time.LocalDateTime

class TodoService {

    suspend fun getAll() = query {
        Todo.all().map {
            TodoResponse(
                id = it.id.value,
                content = it.content,
                done = it.done,
                createdAt = it.createdAt,
                updatedAt = it.updatedAt
            )
        }.toList()
    }

    suspend fun getById(id: Int) = query {
        Todo.findById(id)?.let {
            TodoResponse(
                id = it.id.value,
                content = it.content,
                done = it.done,
                createdAt = it.createdAt,
                updatedAt = it.updatedAt
            )
        }
    }

    suspend fun new(content: String) = query {
        Todo.new {
            this.content = content
        }.let {
            TodoResponse(
                id = it.id.value,
                content = it.content,
                done = it.done,
                createdAt = it.createdAt,
                updatedAt = it.updatedAt
            )
        }
    }

    suspend fun renew(id: Int, req: TodoRequest) {
        query {
            Todo.new(id) {
                this.content = req.content
                this.done = req.done ?: false
                this.updatedAt = LocalDateTime.now()
            }
        }
    }

    suspend fun delete(id: Int) = query {
        Todo.findById(id)?.delete()
    }
}
