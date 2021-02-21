package com.dixitpatel.biirr.extension

import android.app.Activity
import android.content.Context
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.recyclerview.widget.RecyclerView
import com.dixitpatel.biirr.R

/** Animate layout when LayoutManager Change.
 *  This is Extension method we can use anywhere in app.
 *   with recyclerview.recyclerViewAnimate()
 */
fun RecyclerView.recyclerViewAnimate()
{
    val context: Context = this.context
    val controller: LayoutAnimationController =
            AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation)
    this.layoutAnimation = controller
    this.adapter!!.notifyDataSetChanged()
    this.scheduleLayoutAnimation()
}

/**
 * Get Bundle data as Lazy when you call this method at that time it will initialized.
 */
inline fun <reified T: Any> Activity.extraNotNull(key: String, default: T? = null) = lazy {
    val value = intent?.extras?.get(key)
    requireNotNull(if (value is T) value else default) { key }
}