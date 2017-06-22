package com.janta.myplayer

import android.content.Context
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso

/**
 * Created by jorgeanta on 3/6/17.
 */


fun Context.toast(message:String,length:Int=Toast.LENGTH_SHORT){
    Toast.makeText(this,message,length).show()
}
fun RecyclerView.ViewHolder.toast(message:String){
    itemView.context.toast(message)
}

fun ViewGroup.inflate(@LayoutRes layoutRes:Int,attachToRoot:Boolean=false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun ImageView.loadUrl(url:String){
    Picasso.with(context).load(url).into(this)
}

inline fun <reified T:View> View.find(@IdRes resId:Int):T{
    return findViewById(resId) as T

}
inline fun <reified T : View> RecyclerView.ViewHolder.find(@IdRes idRes: Int): T = itemView.findViewById(idRes) as T