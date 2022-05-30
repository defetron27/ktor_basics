package com.example.plugins

import com.example.models.User
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.insertOne
import org.litote.kmongo.eq

fun Application.configureRouting(collection: CoroutineCollection<User>) {
    install(ContentNegotiation) {
        gson()
    }

    // Starting point for a Ktor app:
    routing {
        /*get("/") {
            call.respondText("Hello World!")
        }
        get("/logging") {
            call.application.environment.log.info("Logging Hello Hi")
            call.respondText("Logging Hello Hi!")
        }
        get("/get_users") {
        }*/
        get("/users") {
            val users = collection.find().toList()
            call.respond(users)
        }
        post("/user") {
            call.parameters
            val requestBody = call.receive<User>()
            val isSuccess = collection.insertOne(requestBody).wasAcknowledged()
            call.respond(isSuccess)
        }
        get("/user") {
            val params = call.parameters
            val users = collection.findOne(User::name eq params["name"])
            call.respond(users ?: "${params["name"]} not found")
        }
    }
    /*routing {
    }*/
}
