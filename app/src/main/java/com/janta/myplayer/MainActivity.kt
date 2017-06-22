package com.janta.myplayer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.janta.myplayer.model.MediaItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.startActivity


class MainActivity : AppCompatActivity() {


    val recyclerView by lazy{findViewById(R.id.recycler)as RecyclerView}
    val adapter=MediaAdapter{navigateToDetail(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.adapter=adapter
        loadFilteredData(Filter.None)


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        progressBar.visibility= View.VISIBLE
        val filter:Filter = when(item.itemId){
            R.id.filter_all-> Filter.None
            R.id.filter_photos->Filter.ByMediaType(MediaItem.Type.PHOTO)
            R.id.filter_videos->Filter.ByMediaType(MediaItem.Type.VIDEO)
            else-> Filter.None
        }
        loadFilteredData(filter)


        return super.onOptionsItemSelected(item)
    }

    private fun loadFilteredData(filter: Filter) {
        //implements corutine
        async(UI){
            val cats=bg{MediaProvider.dataSync("cats")}
            val nature=bg{MediaProvider.dataSync("nature")}
            adapter.items=filterData(cats.await()+nature.await(),filter)
            progressBar.visibility=View.GONE
        }

    }

    fun navigateToDetail(item:MediaItem){
        startActivity<DetailActivity>(Pair(DetailActivity.ID,item.id),Pair(DetailActivity.DATA_TYPE,item.dataType))
    }
    fun filterData(media:List<MediaItem>,filter:Filter):List<MediaItem>{
        return when(filter){

            Filter.None -> media
            is Filter.ByMediaType -> media.filter{it.type==filter.type}
        }

    }

}
