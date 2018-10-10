package co.cognized.SOApp.ui.questiondetails

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import co.cognized.SOApp.R
import co.cognized.SOApp.model.SOQuestion
import ru.noties.markwon.Markwon

class QuestionDetailsView : FrameLayout {
    var title: TextView
    var body: TextView
    var answerList: RecyclerView

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0) {}
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        // init view
        val root = LayoutInflater.from(context).inflate(R.layout.question_details_view, this)
        title = root.findViewById(R.id.questionTitle)
        body = root.findViewById(R.id.questionBody)
        answerList = root.findViewById(R.id.answersRecyclerView)
        answerList.layoutManager = LinearLayoutManager(context)
        answerList.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        answerList.adapter = AnswerListAdapter(context)

    }

    @SuppressLint("NewApi")
    fun updateView(question: SOQuestion) {
        if (question.answers != null) {
            (answerList.adapter as AnswerListAdapter).updateAnswers(question.answers)
        }
        Markwon.setText(title, Html.fromHtml(question.title, Html.FROM_HTML_MODE_COMPACT))
        Markwon.setText(body, Html.fromHtml(question.body, Html.FROM_HTML_MODE_COMPACT))
    }
}