package co.cognized.SOApp.model

data class SOQuestion(val answers: List<SOAnswer>?,
                      val owner: Owner?,
                      val is_answers: Boolean?,
                      val accepted_answer_id: Int?,
                      val answer_count: Int?,
                      val score: Int?,
                      val last_activity_date: Int?,
                      val creation_date: Int?,
                      val question_id: Int?,
                      val link: String?,
                      val title: String?,
                      val body: String?)