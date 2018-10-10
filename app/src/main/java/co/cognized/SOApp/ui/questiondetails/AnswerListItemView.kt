package co.cognized.SOApp.ui.questiondetails

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.LinearLayoutCompat
import android.text.Html
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import co.cognized.SOApp.R
import co.cognized.SOApp.model.SOAnswer
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.answer_list_item_view.view.*
import ru.noties.markwon.Markwon
import java.text.DateFormat
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.*

class AnswerListItemView (context: Context): LinearLayoutCompat(context) {
    lateinit var answer: SOAnswer
    lateinit var upvotes: TextView
    lateinit var downvotes: TextView
    lateinit var lastEdited: TextView
    lateinit var name: TextView
    lateinit var image: ImageView


    init {
        val root = LayoutInflater.from(context).inflate(R.layout.answer_list_item_view, this)
        upvotes = root.findViewById(R.id.upvotes)
        downvotes = root.findViewById(R.id.downvotes)
        lastEdited = root.findViewById(R.id.date)
        name = root.findViewById(R.id.user)
        image = root.findViewById(R.id.profileImage)
    }

    @SuppressLint("NewApi")
    fun updateView(answer: SOAnswer) {
        Markwon.setText(answerBody, Html.fromHtml(answer.body, Html.FROM_HTML_MODE_COMPACT))

        upvotes.text = "Upvotes: " + answer.up_vote_count.toString()
        downvotes.text = "Downvotes: " + answer.down_vote_count.toString()
        lastEdited.text = "Last Edited: "
        if (answer.last_edit_date != null) {
            lastEdited.text = "Last Edited: " + DateFormat.getDateInstance(DateFormat.SHORT).format(Date.from(answer.last_edit_date.toLong().let { Instant.ofEpochSecond(it) })).toString()
        }
        name.text = answer.owner?.display_name
        Picasso.get().load(answer.owner?.profile_image).into(image)
    }
}

