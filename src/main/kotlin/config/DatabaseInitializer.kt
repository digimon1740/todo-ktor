package main.kotlin.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import main.kotlin.entity.Todos
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseInitializer {

    fun init() {
        Database.connect(HikariDataSource(hikariConfig()))
        transaction {
            create(Todos)
        }
    }
}

private fun hikariConfig() =
    HikariConfig().apply {
        driverClassName = "org.h2.Driver"
        jdbcUrl = "jdbc:h2:mem:test"
        maximumPoolSize = 3
        isAutoCommit = false
        username = "sa"
        password = "sa"
        transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        validate()
    }

suspend fun <T> query(block: suspend () -> T): T = newSuspendedTransaction {
    block()
}
