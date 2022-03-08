package com.example.androidsliderpage

class PageService {

    private val pages = arrayOf(
        Page("Up", "#FFFFFF", "UP"),
        Page("Down", "#FFFFFF", "Down")
    )

    fun getPage(id:Int): Page {
        return pages[id]
    }

    companion object {
        const val PAGE_UP = 0
        const val PAGE_DOWN = 1
    }
}