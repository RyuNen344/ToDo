package com.ryunen344.todo.taskdetail

import android.support.annotation.NonNull



class TaskDetailPresenter(taskId : String?,tasksRepository : TasksRepository,taskDetailView : TaskDetailContract.View) : TaskDetailContract.Presenter{

    private val mTaskDetailView : TaskDetailContract.View = taskDetailView
    private val mTasksRepository : TasksRepository = tasksRepository
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
        mTasksRepository.getTask(mTaskId,TasksDataSource().GetTaskCallback(){
            override fun onTaskLoaded(task : Task){
                //TODO:jissou
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
        mTasksRepository.deleteTask(mTaskId)
        mTaskDetailView.showTaskDeleted()
    }

    override fun completeTask() {
        if(mTaskId.isNullOrBlank()){
            mTaskDetailView.showMissingTask()
            return
        }
        mTasksRepository.completeTask(mTaskId)
        mTaskDetailView.showTaskMarkedComplete()
    }

    override fun activateTask() {
        if (mTaskId.isNullOrBlank()) {
            mTaskDetailView.showMissingTask()
            return
        }
        mTasksRepository.activateTask(mTaskId)
        mTaskDetailView.showTaskMarkedActive()
    }

    fun showTask(task : Task){
        val title : String = task.getTitle()
        val description : String = task.getDescription()

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