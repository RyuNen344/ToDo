package com.ryunen344.todo

import android.content.Context
import com.ryunen344.todo.data.source.TaskListRepository
import com.ryunen344.todo.data.source.local.TaskListLocalDataSource
import com.ryunen344.todo.data.source.local.ToDoDatabase
import com.ryunen344.todo.util.AppExecutors

class Injection{
    fun provideTasksRepository(context : Context) : TaskListRepository {
        checkNotNull(context)
        val database = ToDoDatabase.getInstance(context)
        return TaskListRepository.getInstance(TasksRemoteDataSource.getInstance(),
                TaskListLocalDataSource.getInstance(AppExecutors(),
                        database.taskDao()))
    }
}