package com.example.models

import com.google.gson.annotations.SerializedName
import org.bson.types.ObjectId
import org.litote.kmongo.Id
import org.litote.kmongo.id.toId

data class User(
//    @BsonId val key: Id<User> = newId(),
//    val id: Id<User>,
    val _id: Id<User> = ObjectId().toId(),
    val name: String,
    val age: Int
)