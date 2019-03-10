package com.ryunen344.todo.util

import android.support.annotation.NonNull
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction

class ActivityUtils(){
    fun addFragmentToActivity(@NonNull fragmentManager : FragmentManager,@NonNull fragment : Fragment,frameId : Int){
        var transaction : FragmentTransaction = fragmentManager.beginTransaction()
        transaction.add(frameId,fragment)
        transaction.commit()
    }
}