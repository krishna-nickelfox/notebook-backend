package com.todos.todos.controllers

import com.todos.todos.models.Todos
import com.todos.todos.services.TodosService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/todos")
class TodosController(private val todosService: TodosService) {


    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(ex: IllegalArgumentException): ResponseEntity<Map<String, String>> {
        val error = mapOf("error" to (ex.message ?: "Unknown error"))
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }


    @GetMapping("/get-all")
    fun getTodos(): ResponseEntity<List<Todos>> {
        return ResponseEntity(todosService.getAll(), HttpStatus.OK)
    }

    @GetMapping("/get/{id}")
    fun getTodos(@PathVariable("id") id: String): ResponseEntity<Map<String,Any>> {
        return ResponseEntity(todosService.getById(id), HttpStatus.OK)
    }
    @PostMapping("/add")
    fun addTodo(@RequestBody todos: Todos): ResponseEntity<Map<String,Any>> {
        return ResponseEntity(todosService.addTodo(todos), HttpStatus.CREATED)
    }

    @PatchMapping("/update/{id}")
    fun updateTodo(@PathVariable("id") id: String,@RequestBody todo: Todos): ResponseEntity<Map<String,Any>> {
        return ResponseEntity(todosService.updateTodo(id,todo), HttpStatus.OK)
    }

    @DeleteMapping("/delete/{id}")
    fun deleteTodo(@PathVariable("id") id: String): ResponseEntity<Map<String,Any>> {
        return ResponseEntity(todosService.deleteTodo(id), HttpStatus.OK)
    }

    @PutMapping("/update/{id}")
    fun toggleTodo(@PathVariable("id") id:String): ResponseEntity<Map<String,String>> {
        return ResponseEntity(todosService.toggleTodo(id), HttpStatus.OK)
    }


}