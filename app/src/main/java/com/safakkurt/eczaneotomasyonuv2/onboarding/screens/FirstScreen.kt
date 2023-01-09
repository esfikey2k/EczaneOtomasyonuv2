package com.safakkurt.eczaneotomasyonuv2.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.safakkurt.eczaneotomasyonuv2.R
import com.safakkurt.eczaneotomasyonuv2.databinding.FragmentFirstScreenBinding


class FirstScreen : Fragment() {

    private var _binding: FragmentFirstScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentFirstScreenBinding.inflate(layoutInflater,container,false)
        val view= binding.root

        val viewPager= activity?.findViewById<ViewPager2>(R.id.viewPager)

        binding.textViewNextFirstScreen.setOnClickListener {
            viewPager?.currentItem = 1
        }

        return view
    }


}