package co.cognized.SOApp.model

data class SOAnswer(val owner: Owner?,
                    val answer_id: Int?,
                    val score: Int?,
                    val body: String?,
                    val down_vote_count: Int?,
                    val up_vote_count: Int?,
                    val last_edit_date: Int?,
                    val is_accepted: Boolean?,
                    val question_id: Int?)