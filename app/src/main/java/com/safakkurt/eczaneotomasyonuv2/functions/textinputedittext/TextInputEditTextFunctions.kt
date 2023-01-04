package com.safakkurt.eczaneotomasyonuv2.functions.textinputedittext


import android.text.InputFilter
import androidx.core.widget.doOnTextChanged
import com.safakkurt.eczaneotomasyonuv2.view.LoginFragment
import com.safakkurt.eczaneotomasyonuv2.R
import com.safakkurt.eczaneotomasyonuv2.databinding.FragmentLoginBinding

fun inputLengthFilter(binding: FragmentLoginBinding){
    binding.textInputTcNo.filters += InputFilter.LengthFilter(11)
}

fun textChanged(binding: FragmentLoginBinding, fragment: LoginFragment){
    binding.textInputTcNo.doOnTextChanged { text, start, before, count ->
        if(text!!.length < 11){
            binding.textInputLayout.error = fragment.getString(R.string.turkish_republic_identity_number_must_be_11_digits)
        }else if(text.length == 11){
            binding.textInputLayout.error= null
        }

        if(count == 0){
            binding.textInputLayout.error= null

        }

    }
}



