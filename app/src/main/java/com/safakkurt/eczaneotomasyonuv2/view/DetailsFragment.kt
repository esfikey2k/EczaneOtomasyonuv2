package com.safakkurt.eczaneotomasyonuv2.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.safakkurt.eczaneotomasyonuv2.R
import com.safakkurt.eczaneotomasyonuv2.adapter.RecyclerViewAdapter
import com.safakkurt.eczaneotomasyonuv2.databinding.FragmentDetailsBinding
import com.safakkurt.eczaneotomasyonuv2.debtor.model.Medicine
import com.safakkurt.eczaneotomasyonuv2.functions.extensionfunction.showDialog
import com.safakkurt.eczaneotomasyonuv2.medicine.model.MedicineModel
import com.safakkurt.eczaneotomasyonuv2.medicine.service.MedicineAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailsFragment : Fragment() {


    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val BASE_URL="https://api.npoint.io/"
    private var medicineModelList= ArrayList<MedicineModel>()
    private var medicineList= ArrayList<Medicine>()
    private var recyclerMedicineList= ArrayList<String>()
    private var recyclerMedicineUrlList= ArrayList<String>()
    private var recyclerMedicineTotalList= ArrayList<String>()
    private var recyclerMedicineTypeList= ArrayList<String>()
    private var recyclerMedicineDescriptionList= ArrayList<String>()
    private var recyclerMedicinePriceList= ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val view = binding.root


        binding.imageViewGraph.setOnClickListener {
            container?.showDialog(R.layout.custom_medicine_graph_dialog,R.id.buttonClose)
        }

        val progressBar= binding.progressBar

        setFragmentResultListener("DebtorPerson"){requestKey, bundle ->

            binding.textViewTc.text= bundle.getString("tc")
            binding.textViewName.text= bundle.getString("name")
            binding.textViewStatus.text= bundle.getString("status")
            binding.textViewTotal.text= bundle.getString("total")
            binding.textViewTotalMedicine.text= bundle.getString("medicineSize")
            medicineList= bundle.getStringArrayList("medicine") as ArrayList<Medicine>

            if(bundle.getString("status")== "Ödendi"){
                binding.textViewStatus.setBackgroundColor(resources.getColor(R.color.green))
            }

            val retrofit= Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service= retrofit.create(MedicineAPI::class.java)

            val call= service.getData()

            call.enqueue(object: Callback<List<MedicineModel>>{
                override fun onResponse(
                    call: Call<List<MedicineModel>>,
                    response: Response<List<MedicineModel>>
                ) {
                    if(response.isSuccessful){
                        response.body().let {
                            progressBar.visibility= View.GONE
                            medicineModelList= ArrayList(it)
                            println(medicineModelList)
                            medicineModelList.let {

                                val lengthOfList= medicineList.size-1
                                val lengthOfModelList= medicineModelList.size-1
                                for(i in 0..lengthOfModelList){
                                    for (j in 0..lengthOfList){
                                        if(medicineModelList[i]._id== medicineList[j].ilac){
                                            recyclerMedicineList.add(medicineModelList[i].name)
                                            recyclerMedicineTotalList.add(medicineList[j].quantity.toString())
                                            recyclerMedicineUrlList.add(medicineModelList[i].image)
                                            recyclerMedicineTypeList.add(medicineModelList[i].medicineType)
                                            recyclerMedicinePriceList.add(medicineModelList[i].price.numberDecimal)
                                            recyclerMedicineDescriptionList.add(medicineModelList[i].description)
                                        }
                                    }
                                }
                                println(recyclerMedicineList)

                                binding.recyclerViewFragmentDetails.layoutManager=GridLayoutManager(requireContext(),2)
                                val medicineAdapter = RecyclerViewAdapter(recyclerMedicineList,recyclerMedicineUrlList,recyclerMedicineTotalList,recyclerMedicineTypeList,recyclerMedicinePriceList,recyclerMedicineDescriptionList)
                                binding.recyclerViewFragmentDetails.adapter= medicineAdapter

                            }
                        }
                    }
                }

                override fun onFailure(call: Call<List<MedicineModel>>, t: Throwable) {
                    t.printStackTrace()
                }

            })

        }

        return view
    }


}