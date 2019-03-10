package com.ryunen344.todo.util

import androidx.test.espresso.IdlingResource

class EspressoIdlingResource{
    private val RESOURCE : String = "GLOBAL"
    private var mCountingIdlingResource : SimpleCountingIdlingResource = SimpleCountingIdlingResource(RESOURCE)

    fun increment(){
        mCountingIdlingResource.increment()
    }

    fun decrement(){
        mCountingIdlingResource.decrement()
    }

    fun getIdlingResource() : IdlingResource{
        return mCountingIdlingResource
    }
}