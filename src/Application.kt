package com.belsoft

import com.belsoft.routes.authenticate
import com.belsoft.routes.json
import com.belsoft.routes.root
import com.belsoft.routes.rootPost
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.content.*
import io.ktor.http.content.*
import io.ktor.features.*
import io.ktor.auth.*
import io.ktor.gson.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(DefaultHeaders) {
        header("X-Engine", "Ktor") // will send this header with each response
    }

    install(Authentication) {
        basic("myBasicAuth") {
            realm = "Ktor Server"
            validate {
                if (
                    when {
                        it.name == "test" && it.password == "password" -> true
                        it.name == "salox" && it.password == "redhat" -> true
                        else -> false
                    }
                ) UserIdPrincipal(it.name) else null
            }
        }
    }

    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
            serializeNulls()
        }
    }

    routing {
        // Static feature. Try to access `/static/ktor_logo.svg`
        static("/static") {
            resources("static")
        }
        root()
        rootPost()
        json()
        authenticate()
    }
}

