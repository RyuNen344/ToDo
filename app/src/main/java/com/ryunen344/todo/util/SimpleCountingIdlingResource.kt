package com.ryunen344.todo.util

import androidx.test.espresso.IdlingResource;
import java.util.concurrent.atomic.AtomicInteger


class SimpleCountingIdlingResource(resourceName : String) : IdlingResource{
    private val mResourceName : String = resourceName
    private val counter : AtomicInteger = AtomicInteger(0)

    @Volatile
    var resourceCallback : IdlingResource.ResourceCallback? = null

    override fun getName() : String {
        return mResourceName
    }

    override fun isIdleNow() : Boolean {
        return counter.get() == 0
    }

    override fun registerIdleTransitionCallback(callback : IdlingResource.ResourceCallback?) {
        this.resourceCallback = callback
    }

    fun increment(){
        counter.getAndIncrement()
    }

    fun decrement(){
        val counterVal : Int = counter.decrementAndGet()
        if(counterVal == 0){
            resourceCallback!!.onTransitionToIdle()
        }

        if(counterVal < 0){
            throw IllegalArgumentException("Counter has been corrupted!")
        }
    }


}