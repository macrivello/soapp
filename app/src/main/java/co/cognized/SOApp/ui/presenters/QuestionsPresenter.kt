package co.cognized.SOApp.ui.presenters

import co.cognized.SOApp.di.DaggerApiInjector
import co.cognized.SOApp.model.SOQuestion
import co.cognized.SOApp.model.SOResponse
import co.cognized.SOApp.network.SOApi
import co.cognized.SOApp.ui.questionsearch.IQuestionsView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class QuestionsPresenter(val view: IQuestionsView) {
    var api : SOApi
    private var subscription: Disposable? = null

    var searchText: String? = null
    var lastResponse: SOResponse? = null
    var selectedQuestion: SOQuestion? = null

    init {
        api = DaggerApiInjector.builder().build().provideApi()
    }

    fun search(text: String, page: Int = 1, newSearch: Boolean = true) {
        searchText = text
        view.isLoading(true)

        subscription = api
                .search(text, page)
                .delay(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnTerminate { view.isLoading(false) }
                .subscribe(
                        { response ->
                            lastResponse = response
                            view.loadQuestions(response.items, newSearch)
                        },
                        { e ->
                            searchText = null
                            lastResponse = null
                            view.error("Error: " + e?.message)
                        }
                )
    }

    fun attemptToLoadMoreQuestions() {
        if (lastResponse?.has_more ?: false && !searchText.isNullOrEmpty()){
            search(searchText!!, lastResponse!!.page + 1, false)
        }
    }

    fun setQuestion(item: SOQuestion) {
        selectedQuestion = item
    }
}