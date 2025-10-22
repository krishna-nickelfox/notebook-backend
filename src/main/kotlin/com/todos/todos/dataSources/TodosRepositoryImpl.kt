package com.todos.todos.dataSources

import com.todos.todos.models.Todos
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Repository
import kotlin.collections.find

@Repository
class TodosRepositoryImpl: TodosRepository {
var todos: MutableList<Todos> = mutableListOf<Todos>()
    override fun getAll(): List<Todos> {
      return todos
    }

    override fun getById(id: String): Map<String, Any> {
        val m1  = mutableMapOf<String, Any>()

        var todo= todos.find { it.id == id }
        if(todo==null){
            throw IllegalArgumentException("Todo Not Found")
        }
        m1["msg"]="todo get successfully :)"
        m1["todo"]= todo
    return  m1

    }

    override fun addTodo(todo: Todos): Map<String, String> {
       todos.add(todo)
        var m1 = mutableMapOf<String, String>()
        m1["msg"]="Todo Added :)"
        return  m1
    }

    override fun updateTodo(
        id: String,
        todo: Todos
    ): Map<String, String> {


        val index = todos.indexOfFirst { it.id == id }
        if (index == -1) throw IllegalArgumentException("Todo Not Found")

        val updatedTodo = todos[index].copy(
            title = todo.title,
            description = todo.description,
            isComplete = todo.isComplete
        )

        todos[index] = updatedTodo

        return mapOf("message" to "Todo updated successfully")


    }

    override fun deleteTodo(id: String): Map<String, String> {

        val index = todos.indexOfFirst { it.id == id }
        if (index == -1) throw IllegalArgumentException("Todo Not Found")

        todos.removeIf { it.id == id }

        return mapOf("msg" to "Todo Deleted Successfully")

    }

    override fun toggleTodo(id: String): Map<String, String> {
        val index = todos.indexOfFirst { it.id == id }
        if (index == -1) throw IllegalArgumentException("Todo Not Found")
        todos = todos.map{
                if(it.id == id){
                    it.isComplete = !it.isComplete
                }
            return@map it

        }.toMutableList()

        return mapOf("msg" to "Todo Update Successfully")

    }
}