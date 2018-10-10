package co.cognized.SOApp.ui.questiondetails

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import co.cognized.SOApp.model.SOAnswer
import kotlinx.android.synthetic.main.question_list_item.view.*

class AnswerListAdapter(val context: Context) : RecyclerView.Adapter<AnswerListAdapter.AnswerItemVH>() {
    var answers: List<SOAnswer> = emptyList()

    class AnswerItemVH(val view: AnswerListItemView) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerItemVH {
        val answerView = AnswerListItemView(context)
        return AnswerItemVH(answerView)
    }

    override fun getItemCount(): Int {
        return answers.size
    }

    override fun onBindViewHolder(holder: AnswerItemVH, position: Int) {
        val answer = answers.get(position)
        holder.view.updateView(answer)
    }

    fun updateAnswers(answers: List<SOAnswer>) {
        this.answers = answers
        notifyDataSetChanged()
    }
}