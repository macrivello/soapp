package co.cognized.SOApp.ui.questionsearch

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.cognized.SOApp.R
import co.cognized.SOApp.model.SOQuestion

class QuestionSearchFragment : Fragment(), QuestionListAdapter.ListItemClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.question_search_fragment, container, true)
    }

    override fun onItemClicked(item: SOQuestion) {

    }
}