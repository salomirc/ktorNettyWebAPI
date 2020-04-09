package com.belsoft.routes

import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get

fun Routing.crashServer(){
    get("/exception"){
        throw(Exception("Exception \"e\" was thrown. The error was requested by the user!"))
    }
}