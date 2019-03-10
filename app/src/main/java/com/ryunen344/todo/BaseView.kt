package com.ryunen344.todo

interface BaseView<T>{
    fun setPresenter(presenter : T)
}