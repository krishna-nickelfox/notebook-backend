package com.todos.todos.services

import com.todos.todos.dataSources.TodosRepository
import com.todos.todos.models.Todos
import org.springframework.stereotype.Service

@Service
class TodosService(private val todosRepository: TodosRepository) {


    fun getAll(): List<Todos>{
        return todosRepository.getAll()
    }
    fun getById(id: String): Map<String,Any>{
        return todosRepository.getById(id)
    }
    fun addTodo(todos: Todos):Map<String,String>{
        return todosRepository.addTodo(todos)
    }
    fun updateTodo(id: String,todos: Todos):Map<String,String>{
        return todosRepository.updateTodo(id,todos)
    }
    fun deleteTodo(id: String):Map<String,String>{
        return todosRepository.deleteTodo(id)
    }

    fun toggleTodo(id: String):Map<String,String>{
            return  todosRepository.toggleTodo(id)
    }


}