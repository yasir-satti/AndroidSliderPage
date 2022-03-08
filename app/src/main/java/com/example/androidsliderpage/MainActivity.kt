package com.example.androidsliderpage;

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2



class MainActivity : AppCompatActivity() {

    private val pageService = PageService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        renderPageOptionsPager()

    }

    private fun  renderPageOptionsPager(){
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        val Pagefragments: ArrayList<Fragment> = arrayListOf(
            PageFragment(pageService.getPage(PageService.PAGE_UP), R.drawable.launch_up),
            PageFragment(pageService.getPage(PageService.PAGE_DOWN), R.drawable.launch_down)
        )

        val adapter = ViewPagerAdaptor(Pagefragments, this)
        viewPager.adapter = adapter
    }

}