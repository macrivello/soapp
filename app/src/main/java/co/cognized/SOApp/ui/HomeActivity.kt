package co.cognized.SOApp.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import co.cognized.SOApp.R
import co.cognized.SOApp.model.SOQuestion
import co.cognized.SOApp.ui.presenters.QuestionsPresenter
import co.cognized.SOApp.ui.questiondetails.QuestionDetailsFragment
import co.cognized.SOApp.ui.questionsearch.QuestionListAdapter
import co.cognized.SOApp.ui.questionsearch.QuestionSearchFragment
import co.cognized.SOApp.ui.questionsearch.QuestionSearchView

class HomeActivity : AppCompatActivity(), QuestionListAdapter.ListItemClickListener{

    lateinit var questionsPresenter: QuestionsPresenter

    lateinit var searchFragment: QuestionSearchFragment
    var detailsFragment: QuestionDetailsFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayShowHomeEnabled(true);
        supportActionBar?.setLogo(R.drawable.ic_action);
        supportActionBar?.setDisplayUseLogoEnabled(true);
        supportActionBar?.setTitle("   " + getString(R.string.app_name))

        setContentView(R.layout.home)
        searchFragment = supportFragmentManager.findFragmentById(R.id.search) as QuestionSearchFragment
        detailsFragment = supportFragmentManager.findFragmentById(R.id.details) as QuestionDetailsFragment?

        val questionView = searchFragment.view as QuestionSearchView//findViewById<QuestionSearchView>(R.id.questionsView)
        questionView.setQuestionSelectListener(this)

        questionsPresenter = QuestionsPresenter(questionView)
        questionView.presenter = questionsPresenter
    }

    override fun onItemClicked(item: SOQuestion) {
        questionsPresenter.setQuestion(item)
        //fragment transaction if needed
        if (supportFragmentManager.fragments.size < 2) { //rough hack for tablet display
            if (detailsFragment == null) detailsFragment = QuestionDetailsFragment()
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, detailsFragment)
                    .addToBackStack(null)
                    .commit()
        }
        detailsFragment?.setQuestion(item)
    }
}