package com.todos.todos.models

import java.util.UUID

data class Todos(
    val id:String = UUID.randomUUID().toString(),
    var title: String,
    var description: String,
    var isComplete:Boolean = false
)
