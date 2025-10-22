package com.todos.todos.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class MainController {

    @GetMapping("/")
    fun index(): Map<String, String> {
        var m1 = mutableMapOf<String,String>("msg" to "App is working")
        return  m1
    }

}