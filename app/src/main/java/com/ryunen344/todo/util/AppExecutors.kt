package com.ryunen344.todo.util

import android.os.Handler
import android.os.Looper
import android.support.annotation.VisibleForTesting
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class AppExecutors(){
    private val THREAD_COUNT = 3
    private var diskIO : Executor = DiskIOThreadExecutor()
    private var networkIO : Executor = Executors.newFixedThreadPool(THREAD_COUNT)
    private var mainThread : Executor = MainThreadExecutor()

    @VisibleForTesting
    constructor(diskIO : Executor, networkIO : Executor, mainThread : Executor) : this(){
        this.diskIO = diskIO
        this.networkIO = networkIO
        this.mainThread = mainThread
    }

    fun diskIO() : Executor {
        return diskIO
    }

    fun networkIO() : Executor {
        return networkIO
    }

    fun mainThread() : Executor {
        return mainThread
    }

    private class MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())

        override fun execute(command : Runnable) {
            mainThreadHandler.post(command)
        }
    }
}