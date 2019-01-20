package cubex.mahesh.googlenews_and7am19

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.Toast
import com.bumptech.glide.Glide
import cubex.mahesh.googlenews_and7am19.beans.Atricles
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.indiview.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getNews.setOnClickListener {

            var r:Retrofit = Retrofit.Builder().
                    addConverterFactory(GsonConverterFactory.create()).
                    baseUrl("https://newsapi.org/").build()
            var api = r.create(NewsAPI::class.java)
            var call:Call<Atricles> = api.getNews()
            call.enqueue(object:Callback<Atricles>{
                override fun onResponse(call: Call<Atricles>, response: Response<Atricles>) {
                    var artcls:Atricles? =  response.body()

                 /*   Toast.makeText(this@MainActivity,
                        artcls?.articles?.size.toString(),
                        Toast.LENGTH_LONG).show()
                    var templist = mutableListOf<String>()
                    for(article in artcls?.articles!!)
                    {
                        templist.add(article.title+"\n"+
                                article.description)
                    }
                    var adapter = ArrayAdapter<String>(
                        this@MainActivity,
                        android.R.layout.simple_list_item_single_choice,
                        templist)
                    lview.adapter = adapter */

                    lview.adapter = object : BaseAdapter() {
                        override fun getCount(): Int  = artcls?.articles!!.size

                        override fun getItem(p0: Int): Any = 0

                        override fun getItemId(p0: Int): Long = 0

                        override fun getView(pos: Int, p1: View?, p2: ViewGroup?): View {
                            var inflater = LayoutInflater.from(this@MainActivity)
                            var v = inflater.inflate(R.layout.indiview,null)

                            v.title.text = artcls?.articles!!.get(pos).title
                            v.desc.text = artcls?.articles!!.get(pos).description

                            Glide.with(this@MainActivity).
                                load(artcls?.articles!!.get(pos).urlToImage).
                                into(v.iview)

                            return v
                        }
                    }
                
                    lview.setOnItemClickListener { adapterView, view, i, l ->

                        var it = Intent(this@MainActivity,
                                                            BrowserActivity::class.java)
                        it.putExtra("url",artcls?.articles!!.get(i).url)
                        startActivity(it)

                    }
                }

                override fun onFailure(call: Call<Atricles>, t: Throwable) {
                    Toast.makeText(this@MainActivity,
                        "Failed to get news info...",
                        Toast.LENGTH_LONG).show()
               }
            })

        }

    }
}
