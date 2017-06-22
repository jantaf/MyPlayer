package com.janta.myplayer.model

/**
 * Created by jorgeanta on 3/6/17.
 */

data class MediaItem(val id:Int,val title:String,val thumbUrl:String,val type:Type,val dataType:String){
    enum class Type{PHOTO,VIDEO}
}