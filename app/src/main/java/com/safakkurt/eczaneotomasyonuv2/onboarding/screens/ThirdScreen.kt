package com.safakkurt.eczaneotomasyonuv2.onboarding.screens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.safakkurt.eczaneotomasyonuv2.R
import com.safakkurt.eczaneotomasyonuv2.databinding.FragmentThirdScreenBinding


class ThirdScreen : Fragment() {

    private var _binding: FragmentThirdScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding= FragmentThirdScreenBinding.inflate(layoutInflater,container,false)
        val view= binding.root


        binding.textViewFinishThirdScreen.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment_to_loginFragment)
            onBoardingFinished()
        }

        return view
    }

    private fun onBoardingFinished(){
        val sharedPref= requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor= sharedPref.edit()
        editor.putBoolean("finished",true)
        editor.apply()
    }
}