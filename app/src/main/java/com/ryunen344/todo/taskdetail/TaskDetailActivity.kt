package com.ryunen344.todo.taskdetail

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.ryunen344.todo.Injection
import com.ryunen344.todo.R
import com.ryunen344.todo.R.layout.taskdetail_act
import com.ryunen344.todo.util.ActivityUtils
import kotlinx.android.synthetic.main.taskdetail_act.*

class TaskDetailActivity : AppCompatActivity() {
    val EXTRA_TASK_ID : String = "TASK_ID"

    override fun onCreate(savedInstanceState : Bundle?, persistentState : PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        setContentView(taskdetail_act)

        //Set up the toolbar
        setSupportActionBar(toolbar as Toolbar?)
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)

        //get the request task id
        val taskId :String = intent.getStringExtra(EXTRA_TASK_ID)

        var taskDetailFragment : TaskDetailFragment = supportFragmentManager.findFragmentById(R.id.contentFrame) as TaskDetailFragment
        if(taskDetailFragment == null){
            taskDetailFragment = newInstance(taskId)
            ActivityUtils().addFragmentToActivity(supportFragmentManager,taskDetailFragment, R.id.contentFrame);
        }

        TaskDetailPresenter(
                taskId,
                Injection.provideTasksRepository(applicationContext),
                taskDetailFragment)
    }

    override fun onSupportNavigateUp() : Boolean {
        onBackPressed()
        return true
    }

}
