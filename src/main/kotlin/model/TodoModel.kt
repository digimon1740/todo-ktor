package model

import java.time.LocalDateTime

data class TodoModel(val content: String,
                     val done: Boolean,
                     val createdAt: LocalDateTime,
                     val updatedAt: LocalDateTime,
                     val deleted: Boolean)