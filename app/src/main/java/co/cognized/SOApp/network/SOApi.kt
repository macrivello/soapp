package co.cognized.SOApp.network

import co.cognized.SOApp.model.SOQuestion
import co.cognized.SOApp.model.SOResponse
import retrofit2.http.GET
import io.reactivex.Observable
import retrofit2.http.Query

// Params
//filter=!2ocTheG0ZYKQa2BJUgsv6WkpgSXylP_REL-yN)EPYL
//site=stackoverflow

interface SOApi {
    @GET("2.2/search")
    fun search(@Query("intitle") term: String, @Query("page") page: Int) : Observable<SOResponse>
}