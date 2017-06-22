package com.janta.myplayer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.janta.myplayer.model.MediaItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        val ID="DetailActivity:id"
        val DATA_TYPE="DetailActivity:dataType"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val id:Int=intent.getIntExtra(ID,-1)
        val dataType:String=intent.getStringExtra(DATA_TYPE)
        if(id!=-1) {
            MediaProvider.dataAsync(dataType) { media->
                media.find{it.id==id && it.dataType==dataType}?.let{ item->
                    progressBar.visibility=View.GONE
                    supportActionBar?.title=item.title
                    Picasso.with(this).load(item.thumbUrl).into(detail_thumb)
                    detail_video_indicator.visibility=when(item.type){
                        MediaItem.Type.VIDEO-> View.VISIBLE
                        MediaItem.Type.PHOTO->View.GONE
                    }
                }



            }

        }

    }
}
