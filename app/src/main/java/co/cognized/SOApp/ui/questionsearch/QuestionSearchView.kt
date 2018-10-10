package co.cognized.SOApp.ui.questionsearch

import android.content.Context
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import co.cognized.SOApp.R
import co.cognized.SOApp.model.SOQuestion
import co.cognized.SOApp.ui.presenters.QuestionsPresenter

class QuestionSearchView : FrameLayout, IQuestionsView {
    //IQuestionsView
    override fun error(error: String) {
        Log.e("ERROR", error)
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    override fun isLoading(t: Boolean) {
        if (t) progressBar.visibility = View.VISIBLE else progressBar.visibility = View.INVISIBLE
    }

    override fun loadQuestions(questions: List<SOQuestion>, clearData: Boolean) {
        if (questions.isEmpty()) {
            Toast.makeText(context, "No Results Found. Try a different query", Toast.LENGTH_LONG).show()
        }

        (recyclerView.adapter as QuestionListAdapter).loadQuestions(questions, clearData)
    }

    val searchEditText: EditText
    val searchButton: ImageButton
    val recyclerView: RecyclerView
    val progressBar: ProgressBar

    lateinit var presenter: QuestionsPresenter

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0) {}
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        // init view
        val root = LayoutInflater.from(context).inflate(R.layout.questions_view, this)
        searchButton = root.findViewById(R.id.searchButton)
        searchEditText = root.findViewById(R.id.searchEditText)
        recyclerView = root.findViewById(R.id.questionsRecyclerView)
        progressBar = root.findViewById(R.id.progressBar2)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = QuestionListAdapter(context)
        recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (recyclerView?.canScrollVertically(1) == false) {
                    presenter.attemptToLoadMoreQuestions();
                }
            }
        })
        recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

        searchButton.setOnClickListener { v -> onSearchText(searchEditText.text.toString()) }

        searchEditText.setOnKeyListener( View.OnKeyListener {view, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                //Perform Code
                onSearchText(searchEditText.text.toString())
                return@OnKeyListener true
            }
            false
        })
    }

    private fun onSearchText(string: String) {
        if (string.isNotEmpty()) {
            // hide keyboard
            searchEditText.clearFocus()
            (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(windowToken, 0)
            presenter.search(string)
        }
    }

    fun setQuestionSelectListener(listener: QuestionListAdapter.ListItemClickListener) {
        (recyclerView.adapter as QuestionListAdapter).setItemClickListener(listener)
    }
}