package com.safakkurt.eczaneotomasyonuv2.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.safakkurt.eczaneotomasyonuv2.databinding.FragmentViewPagerBinding
import com.safakkurt.eczaneotomasyonuv2.onboarding.screens.FirstScreen
import com.safakkurt.eczaneotomasyonuv2.onboarding.screens.SecondScreen
import com.safakkurt.eczaneotomasyonuv2.onboarding.screens.ThirdScreen


class ViewPagerFragment : Fragment() {

    private var _binding: FragmentViewPagerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentViewPagerBinding.inflate(inflater,container,false)
        val view= binding.root

        val fragmentList = arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen()
        )

        val adapter= ViewPagerAdapter(fragmentList,requireActivity().supportFragmentManager,lifecycle)

        binding.viewPager.adapter= adapter


        return view
    }


}