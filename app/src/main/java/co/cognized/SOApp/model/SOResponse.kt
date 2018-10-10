package co.cognized.SOApp.model

data class SOResponse (val items: List<SOQuestion>, val has_more: Boolean, val page: Int)