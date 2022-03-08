package com.example.androidsliderpage

import com.example.androidsliderpage.databinding.PageFragmentBinding
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources.getDrawable

class PageFragment(private val page: Page, private val imageValue: Int) : Fragment() {

    private var _binding: PageFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater : LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PageFragmentBinding.inflate(inflater, container, false)
        binding.page = page
        setPageOption(binding.root)
        return binding.root

        //val textView: TextView? = view?.findViewById(R.id.pagecontent)
        //textView?.setText(this.text)
        //return view
    }

    private fun setPageOption(view: View) {
        val pageImageView: ImageView? = view.findViewById(R.id.pageimage)
        val drawable = context?.let { getDrawable(it, imageValue) }
        pageImageView?.setImageDrawable(drawable)
    }

}