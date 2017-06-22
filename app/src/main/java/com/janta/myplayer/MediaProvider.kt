package com.janta.myplayer

import com.janta.myplayer.model.MediaItem
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by jorgeanta on 4/6/17.
 */



object MediaProvider{
    private val thumbBase = "http://lorempixel.com/400/400"
    private var data= emptyList<MediaItem>()

    //asynchronized
    fun dataAsync(dataType:String,callback:(List<MediaItem>)->Unit){
        doAsync {
            if(data.isEmpty() || (data.isNotEmpty() && data[0].dataType!=dataType )){
                data=dataSync(dataType)
            }

            uiThread {
                callback(data)
            }
        }

    }
    //synchronized
    fun dataSync(dataType:String):List<MediaItem>{
        Thread.sleep(2000)
        return (1..10).map {
            MediaItem(it,"Title $it", "$thumbBase/$dataType/$it", if (it % 3 != 0) MediaItem.Type.PHOTO else MediaItem.Type.VIDEO,dataType)
        }


    }

}

