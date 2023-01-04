package com.safakkurt.eczaneotomasyonuv2.view

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.safakkurt.eczaneotomasyonuv2.R
import com.safakkurt.eczaneotomasyonuv2.databinding.FragmentLoginBinding
import com.safakkurt.eczaneotomasyonuv2.functions.textinputedittext.inputLengthFilter
import com.safakkurt.eczaneotomasyonuv2.functions.textinputedittext.textChanged
import com.safakkurt.eczaneotomasyonuv2.debtor.model.DebtorModel
import com.safakkurt.eczaneotomasyonuv2.debtor.service.DebtorAPI
import com.safakkurt.eczaneotomasyonuv2.functions.extensionfunction.showDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val BASE_URL="https://api.npoint.io/"
    private var debtorModels: ArrayList<DebtorModel>?= null
    private var tcArrayList=ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root

        try {
            inputLengthFilter(binding)
            textChanged(binding,this)
            if (container != null) {
                loadData(container)
            }
        }catch (e: Exception){
            e.printStackTrace()
        }


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun loadData(container: ViewGroup){
        val retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service= retrofit.create(DebtorAPI::class.java)

        val call= service.getData()

        call.enqueue(object: Callback<List<DebtorModel>>{
            override fun onResponse(
                call: Call<List<DebtorModel>>,
                response: Response<List<DebtorModel>>
            ) {
                if (response.isSuccessful){
                    response.body()?.let {
                        debtorModels= ArrayList(it)
                        println(debtorModels.toString())
                        debtorModels?.let {
                            binding.buttonSorgula.setOnClickListener {

                                try {

                                    val textInputTcNo= binding.textInputTcNo.text.toString()
                                    println(textInputTcNo)

                                    val lengthOfList= debtorModels!!.size-1
                                    for(i in 0..lengthOfList){
                                        tcArrayList.add(debtorModels!![i].tc)
                                    }

                                    val searchedIndex= tcArrayList.indexOf(textInputTcNo).toString()
                                    val tcNo= tcArrayList[searchedIndex.toInt()]

                                    println(tcNo)

                                    if(textInputTcNo==tcNo){

                                        val dialogBinding= layoutInflater.inflate(R.layout.custom_login_dialog,null)
                                        val myDialog= Dialog(requireContext())
                                        myDialog.setContentView(dialogBinding)

                                        myDialog.setCancelable(true)
                                        myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                                        myDialog.show()

                                        val buttonConfirm= dialogBinding.findViewById<Button>(R.id.buttonConfirm)
                                        val buttonCancel= dialogBinding.findViewById<Button>(R.id.buttonCancel)
                                        val nameSurname= dialogBinding.findViewById<TextInputEditText>(R.id.textInputNameSurname)
                                        val nameSurnameTextInputLayout= dialogBinding.findViewById<TextInputLayout>(R.id.textInputLayoutNameSurname)
                                        val nameSurnameTextView= dialogBinding.findViewById<TextView>(R.id.textViewConfirmName)

                                        val parts= debtorModels!![searchedIndex.toInt()].name.split(" ").toMutableList()
                                        val firstName= parts.firstOrNull()
                                        parts.removeAt(0)
                                        val lastName= parts.joinToString(" ")

                                        nameSurnameTextView.text= "${firstName!![0]}****  ${lastName!![0]}****"


                                        buttonConfirm.setOnClickListener {

                                            if(debtorModels!![searchedIndex.toInt()].name.lowercase(Locale.getDefault()) == nameSurname.text.toString().lowercase(Locale.getDefault()).trim()){

                                                setFragmentResult("DebtorPerson", bundleOf(
                                                    "tc" to textInputTcNo
                                                    ,"name" to debtorModels!![searchedIndex.toInt()].name
                                                    ,"status" to debtorModels!![searchedIndex.toInt()].status
                                                    ,"total" to "${debtorModels!![searchedIndex.toInt()].total.numberDecimal} ₺"
                                                    ,"medicineSize" to debtorModels!![searchedIndex.toInt()].medicine.size.toString()
                                                    ,"medicine" to debtorModels!![searchedIndex.toInt()].medicine)
                                                )

                                                val action= LoginFragmentDirections.actionLoginFragmentToDetailsFragment()
                                                view?.findNavController()?.navigate(action)

                                                myDialog.dismiss()

                                            }else{
                                                nameSurnameTextInputLayout.error= "Ad soyad eşleşmedi"
                                            }

                                        }

                                        buttonCancel.setOnClickListener {
                                            myDialog.dismiss()
                                        }

                                    }

                                }catch (e: Exception){
                                    e.printStackTrace()
                                }

                            }
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<DebtorModel>>, t: Throwable) {
                t.printStackTrace()
                binding.buttonSorgula.setOnClickListener {

                    container.showDialog(R.layout.custom_connection_dialog,R.id.buttonTryAgainConnection)

                }

            }

        })

    }

}