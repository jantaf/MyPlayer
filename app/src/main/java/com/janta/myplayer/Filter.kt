package com.janta.myplayer

import com.janta.myplayer.model.MediaItem

/**
 * Created by jorgeanta on 4/6/17.
 */


sealed class Filter{
    object None : Filter()
    class ByMediaType(val type: MediaItem.Type) : Filter()
}