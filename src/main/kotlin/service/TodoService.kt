package service

import config.query
import entity.Todo
import model.TodoModel
import java.time.LocalDateTime

class TodoService {

    suspend fun getAll() = query {
        Todo.all()
    }

    suspend fun getById(id: Int) = query {
        Todo.findById(id)
    }

    suspend fun new(model: TodoModel) = query {
        Todo.new {
            this.content = model.content
            this.createdAt = LocalDateTime.now()
            this.updatedAt = this.createdAt
        }
    }

    suspend fun renew(id: Int, model: TodoModel) = query {
        Todo.new(id) {
            this.content = model.content
            this.done = model.done
            this.updatedAt = LocalDateTime.now()
        }
    }

    suspend fun delete(id: Int) = query {
        Todo.findById(id)?.delete()
    }
}
