package com.example

import io.ktor.http.*
import kotlin.test.*
import io.ktor.server.testing.*
import main.kotlin.main

class ApplicationTest {

    @BeforeTest
    fun setUp() {

    }

    @Test
    fun testRoot() {
        withTestApplication({ main(testing = true) }) {
            handleRequest(HttpMethod.Get, "/todos").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("HELLO WORLD!", response.content)
            }
        }
    }
}
