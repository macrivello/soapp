package co.cognized.SOApp.ui.questionsearch

import android.annotation.SuppressLint
import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.LinearLayoutCompat
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import android.widget.TextView
import co.cognized.SOApp.R
import co.cognized.SOApp.model.SOQuestion
import ru.noties.markwon.Markwon
import java.text.DateFormat
import java.time.Instant
import java.util.*

class QuestionListItem(context: Context) : FrameLayout(context) {
    val title: TextView
    val date: TextView
    val user: TextView
    val score: TextView
    val answers: TextView
    val number: TextView

    var question: SOQuestion? = null

    @SuppressLint("NewApi")
    fun updateView(question: SOQuestion) {
        Markwon.setText(title, Html.fromHtml(question?.title, Html.FROM_HTML_MODE_COMPACT))
        if (question?.last_activity_date != null) {
            date.text = "Last Activity: " + DateFormat.getDateInstance(DateFormat.SHORT).format(Date.from(question.last_activity_date.toLong().let { Instant.ofEpochSecond(it) })).toString()
        }
        user.text = question?.owner?.display_name
        score.text = question?.score?.toString()
        var answerString = question?.answer_count.toString()
        question?.accepted_answer_id?.let { answerString += " ✓" }
        answers.text = answerString
    }


    init {
        val root = LayoutInflater.from(context).inflate(R.layout.question_list_item, this)
        root.layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)

        title = root.findViewById(R.id.questionTitle)
        date = root.findViewById(R.id.date)
        user = root.findViewById(R.id.user)
        score = root.findViewById(R.id.score)
        answers = root.findViewById(R.id.answers)
        number = root.findViewById(R.id.number)
    }
}