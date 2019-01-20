package cubex.mahesh.googlenews_and7am19

import cubex.mahesh.googlenews_and7am19.beans.Atricles
import retrofit2.Call
import retrofit2.http.GET

interface NewsAPI {

    @GET("v2/top-headlines?sources=google-news&apiKey=96e3942ff23c4ea38bcfa86a490bd933")
    fun getNews( ) : Call<Atricles>
}