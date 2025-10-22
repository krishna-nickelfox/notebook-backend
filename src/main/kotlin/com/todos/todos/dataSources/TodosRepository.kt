package com.todos.todos.dataSources

import com.todos.todos.models.Todos
import org.springframework.stereotype.Repository

@Repository
interface TodosRepository {

    fun getAll(): List<Todos>
    fun getById(id: String): Map<String,Any>
    fun addTodo(todos: Todos):Map<String,String>
    fun updateTodo(id: String,todos: Todos):Map<String,String>
    fun deleteTodo(id: String):Map<String,String>
    fun toggleTodo(id: String):Map<String,String>

}