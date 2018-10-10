package co.cognized.SOApp.ui.questionsearch

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import co.cognized.SOApp.model.SOQuestion
import kotlinx.android.synthetic.main.question_list_item.view.*

class QuestionListAdapter(val context: Context) :
        RecyclerView.Adapter<QuestionListAdapter.SOQuestionVH>() {

    interface ListItemClickListener {
        fun onItemClicked(item: SOQuestion)
    }

    class SOQuestionVH(val view: QuestionListItem) : RecyclerView.ViewHolder(view)

    var questions: List<SOQuestion> = emptyList()
    var itemListener: ListItemClickListener? = null

    fun setItemClickListener(itemListener: ListItemClickListener) {
        this.itemListener = itemListener
    }

    fun loadQuestions(questions: List<SOQuestion>, clearList: Boolean) {
        if (clearList) this.questions = questions else this.questions = this.questions + questions
        notifyDataSetChanged()
    }

    // Recycler Adapter
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): QuestionListAdapter.SOQuestionVH {
        // create a new view
        val listItemView = QuestionListItem(context)

        return SOQuestionVH(listItemView)
    }

    override fun onBindViewHolder(holder: SOQuestionVH, position: Int) {
        val question = questions.get(position)
        holder.view.updateView(question)
        holder.view.number.text = (position + 1).toString() + "."
        holder.view.setOnClickListener { v -> itemListener?.onItemClicked(question.copy()) }
    }

    override fun getItemCount() = questions.size
}