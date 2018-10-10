package co.cognized.SOApp.ui.questionsearch

import android.content.Context
import android.support.v7.widget.LinearLayoutCompat
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import co.cognized.SOApp.R

class SearchBoxView: LinearLayoutCompat {
    val searchBox: AutoCompleteTextView
    val searchButton: ImageView
    val searchHistory: ArrayList<String>

    var searchTermListener: SearchTermListener? = null

    interface SearchTermListener {fun onNewSearch(text: String)}

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0) {}
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        // init view
        val root = LayoutInflater.from(context).inflate(R.layout.search_view, this)
        searchBox = root.findViewById(R.id.searchBox)
        searchButton = root.findViewById(R.id.searchButton)

        searchHistory = ArrayList()
        searchBox.setAdapter(ArrayAdapter(context, android.R.layout.simple_dropdown_item_1line, searchHistory))
    }

    fun search(text: String) {
        if (text.isNullOrEmpty()) return
        if (!searchHistory.contains(text)) searchHistory.add(text)

        searchBox.dismissDropDown()

        searchTermListener?.onNewSearch(text)
    }

}
