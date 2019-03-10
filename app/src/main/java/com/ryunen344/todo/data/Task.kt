package com.ryunen344.todo.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

import com.google.common.base.Objects
import com.google.common.base.Strings

import java.util.UUID

@Entity(tableName = "tasks")
data class Task(var title : String?, var description : String?, var id : String, var completed : Boolean){
    constructor(title : String?, description : String?, id : String) : this(title,description,id,false)

    constructor(title : String? ,description : String?) : this(title,description, UUID.randomUUID().toString(),false)

    constructor(title : String?,description : String?,completed : Boolean) :this(title,description,UUID.randomUUID().toString(),completed)

    @PrimaryKey
    @ColumnInfo(name = "entryid")
    private var mId : String = id

    @ColumnInfo(name = "title")
    private var mTitle : String? = title

    @ColumnInfo(name = "description")
    private var mDescription : String? = description

    @ColumnInfo(name = "completed")
    private var mCompleted : Boolean = completed

    fun getTitleForList() : String{
        if(!mTitle.isNullOrEmpty()){
            return mTitle.toString()
        }else{
            return mDescription.toString()
        }
    }

    fun isCompleted() : Boolean{
        return mCompleted
    }

    fun isActive() : Boolean {
        return !mCompleted
    }

    fun isEmpty() : Boolean{
        return mTitle.isNullOrEmpty() && mDescription.isNullOrEmpty()
    }

    override fun equals(o : Any?) : Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val task : Task = o as Task
        return Objects.equal(mId, task!!.mId) &&
                Objects.equal(mTitle, task!!.mTitle) &&
                Objects.equal(mDescription, task!!.mDescription)
    }

    override fun hashCode() : Int {
        return Objects.hashCode(mId, mTitle, mDescription)
    }

    override fun toString() : String {
        return "Task with title " + mTitle!!
    }
}