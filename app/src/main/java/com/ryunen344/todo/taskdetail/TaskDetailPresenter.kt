package com.ryunen344.todo.taskdetail

import com.ryunen344.todo.data.Task
import com.ryunen344.todo.data.source.TaskListDataSource
import com.ryunen344.todo.data.source.TaskListRepository


class TaskDetailPresenter(taskId : String?, taskListRepository : TaskListRepository, taskDetailView : TaskDetailContract.View) : TaskDetailContract.Presenter{

    private val mTaskDetailView : TaskDetailContract.View = taskDetailView
    private val mTaskListRepository : TaskListRepository = taskListRepository
    private var mTaskId : String? = taskId

    init {
        mTaskDetailView.setPresenter(this)
    }

    override fun start() {
        openTask()
    }

    fun openTask(){
        if(mTaskId.isNullOrBlank()){
            mTaskDetailView.showMissingTask()
            return
        }

        mTaskDetailView.setLoadingIndicator(true)
        mTaskListRepository.getTask(mTaskId!!, object : TaskListDataSource.GetTaskCallback{
            override fun onTaskLoaded(task : Task) {
                if(!mTaskDetailView.isActive()){
                    return
                }
                mTaskDetailView.setLoadingIndicator(false)
                if(null == task){
                    mTaskDetailView.showMissingTask()
                }else{
                    showTask(task)
                }
            }

            override fun onDataNotAvailable() {
                if(!mTaskDetailView.isActive()){
                    return
                }
                mTaskDetailView.showMissingTask()
            }

        })
    }

    override fun editTask() {
        if(mTaskId.isNullOrBlank()){
            mTaskDetailView.showMissingTask()
            return
        }
        mTaskDetailView.showEditTask(mTaskId!!)
    }

    override fun deleteTask() {
        if(mTaskId.isNullOrBlank()){
            mTaskDetailView.showMissingTask()
            return
        }
        mTaskListRepository.deleteTask(mTaskId!!)
        mTaskDetailView.showTaskDeleted()
    }

    override fun completeTask() {
        if(mTaskId.isNullOrBlank()){
            mTaskDetailView.showMissingTask()
            return
        }
        mTaskListRepository.completeTask(mTaskId!!)
        mTaskDetailView.showTaskMarkedComplete()
    }

    override fun activateTask() {
        if (mTaskId.isNullOrBlank()) {
            mTaskDetailView.showMissingTask()
            return
        }
        mTaskListRepository.activateTask(mTaskId!!)
        mTaskDetailView.showTaskMarkedActive()
    }

    fun showTask(task : Task){
        val title : String = task.title!!
        val description : String = task.description!!

        if(title.isNullOrBlank()){
            mTaskDetailView.hideTitle()
        }else{
            mTaskDetailView.showTitle(title)
        }

        if(description.isNullOrBlank()){
            mTaskDetailView.hideDescription()
        }else{
            mTaskDetailView.showDescription(description)
        }
        mTaskDetailView.showCompletionStatus(task.isCompleted())
    }


}