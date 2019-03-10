package com.ryunen344.todo

import android.content.Context

class Injection{
    fun provideTasksRepository(context : Context){
        var db : ToDoDatabase = getInstance(context)
    }
}