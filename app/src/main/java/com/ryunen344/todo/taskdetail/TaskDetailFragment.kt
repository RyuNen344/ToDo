package com.ryunen344.todo.taskdetail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.widget.TextView
import android.widget.CheckBox
import com.ryunen344.todo.R
import com.ryunen344.todo.R.id.menu_delete
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.taskdetail_frag.*
import kotlinx.android.synthetic.main.taskdetail_frag.view.*


class TaskDetailFragment : Fragment(),TaskDetailContract.View {
    private val ARGUMENT_TASK_ID : String = "TASK_ID"
    private val REQUEST_EDIT_TASK : Int = 1;

    private var mPresenter : TaskDetailContract.Presenter = TaskDetailPresenter()
    private var mDetailTitle : TextView = task_detail_title
    private var mDetailDescription : TextView = task_detail_description
    private var mDetailCompleteState : CheckBox = task_detail_complete



    override fun onResume() {
        super.onResume()
        mPresenter.start()
    }

    override fun onCreateView(inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?) : View? {
        var root : View = inflater.inflate(R.layout.taskdetail_frag,container,false)
        setHasOptionsMenu(true)
        mDetailTitle = root.task_detail_title
        mDetailDescription = root.task_detail_description
        mDetailCompleteState = root.task_detail_complete

        fab.setOnClickListener {
            mPresenter.editTask()
        }
        return root
    }

    override fun setPresenter(presenter : TaskDetailContract.Presenter) {
        mPresenter = presenter
    }

    override fun onOptionsItemSelected(item : MenuItem?) : Boolean {
        when(item?.itemId){
            R.id.menu_delete ->{
                mPresenter.deleteTask()
                return true
                }
            }
        return false
    }

    override fun onCreateOptionsMenu(menu : Menu?, inflater : MenuInflater?) {
        inflater?.inflate(menu_delete,menu)
    }

    override fun setLoadingIndicator(active : Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMissingTask() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideTitle() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showTitle(title : String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideDescription() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showDescription(description : String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showCompletionStatus(complete : Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showEditTask(taskId : String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showTaskDeleted() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showTaskMarkedComplete() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showTaskMarkedActive() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isActive() : Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



}

fun newInstance(taskId : String?) : TaskDetailFragment {
    val arguments = Bundle()
    arguments.putString("TASK_ID", taskId)
    val fragment = TaskDetailFragment()
    fragment.arguments = arguments
    return fragment
}