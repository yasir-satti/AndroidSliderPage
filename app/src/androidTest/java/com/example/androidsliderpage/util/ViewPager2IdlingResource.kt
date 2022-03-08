package com.example.androidsliderpage.util

import androidx.test.espresso.IdlingResource
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2

class ViewPager2IdlingResource(viewPager: ViewPager2, name: String) : IdlingResource {

    private val name: String
    private var isIdle = true // Default to idle since we can't query the scroll state.
    private var resourceCallback: IdlingResource.ResourceCallback? = null

    init {
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                isIdle = (state == ViewPager.SCROLL_STATE_IDLE // Treat dragging as idle, or Espresso will block itself when swiping.
                        || state == ViewPager.SCROLL_STATE_DRAGGING)
                if (isIdle && resourceCallback != null) {
                    resourceCallback!!.onTransitionToIdle()
                }
            }
        })
        this.name = name
    }

    override fun getName(): String {
        return name
    }

    override fun isIdleNow(): Boolean {
        return isIdle
    }

    override fun registerIdleTransitionCallback(resourceCallback: IdlingResource.ResourceCallback) {
        this.resourceCallback = resourceCallback
    }
}