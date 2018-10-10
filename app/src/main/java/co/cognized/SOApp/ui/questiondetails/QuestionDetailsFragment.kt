package co.cognized.SOApp.ui.questiondetails

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import co.cognized.SOApp.R
import co.cognized.SOApp.model.SOQuestion

class QuestionDetailsFragment : Fragment() {
    var inflated = false
    var soQuestion: SOQuestion? = null
    lateinit var questionDetailsView: QuestionDetailsView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        questionDetailsView = view.findViewById(R.id.details)

        inflated = true
        updateView()
    }

    private fun updateView() {
        if (soQuestion != null) questionDetailsView.updateView(soQuestion!!.copy())
    }

    fun setQuestion(item: SOQuestion) {
        soQuestion = item

        if (inflated) {
            updateView()
        }
    }
}