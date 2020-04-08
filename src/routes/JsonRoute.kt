package com.belsoft.routes

import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get

fun Routing.json(){
    get("/json") {
        call.respond(mapOf("hello" to "world"))
    }
}