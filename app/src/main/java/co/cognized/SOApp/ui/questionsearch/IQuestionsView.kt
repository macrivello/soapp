package co.cognized.SOApp.ui.questionsearch

import co.cognized.SOApp.model.SOQuestion

interface IQuestionsView {
    fun loadQuestions(questions: List<SOQuestion>, clearData: Boolean)
    fun isLoading(t: Boolean)
    fun error(error: String)
}