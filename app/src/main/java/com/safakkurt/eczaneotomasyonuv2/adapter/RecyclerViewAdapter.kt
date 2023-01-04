package com.safakkurt.eczaneotomasyonuv2.adapter

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.safakkurt.eczaneotomasyonuv2.R
import com.safakkurt.eczaneotomasyonuv2.databinding.RecyclerRowBinding
import com.safakkurt.eczaneotomasyonuv2.debtor.model.NumberDecimal
import com.squareup.picasso.Picasso

class RecyclerViewAdapter(val medicineNameArrayList: ArrayList<String>,val medicineUrlArrayList: ArrayList<String>,val medicineTotalArrayList: ArrayList<String>,val medicineTypeArrayList: ArrayList<String>,val medicinePriceArrayList: ArrayList<String>,val medicineDescriptionArrayList: ArrayList<String>): RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding= RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.textViewMedicineName.text= medicineNameArrayList[position]
        holder.binding.textViewMedicineNumber.text= medicineTotalArrayList[position]
        Picasso.get().load(medicineUrlArrayList[position]).into(holder.binding.itemImage)

        holder.itemView.setOnClickListener {
            val dialog= BottomSheetDialog(holder.itemView.context)

            val bottomSheet= LayoutInflater.from(holder.itemView.context).inflate(R.layout.bottom_sheet_dialog,null,false)
            dialog.setContentView(bottomSheet)

            dialog.setOnShowListener {
                (bottomSheet.parent as ViewGroup).background= ColorDrawable(Color.TRANSPARENT)
            }

            val textViewMedicineName= dialog.findViewById<TextView>(R.id.bottomTextViewMedicineName)
            val textViewMedicineType= dialog.findViewById<TextView>(R.id.bottomTextViewMedicineType)
            val textViewMedicinePrice= dialog.findViewById<TextView>(R.id.bottomTextViewMedicinePrice)
            val textViewMedicineDescription= dialog.findViewById<TextView>(R.id.bottomTextViewMedicineDescription)
            val imageViewMedicine = dialog.findViewById<ImageView>(R.id.bottomImageViewMedicine)

            textViewMedicineName?.text= medicineNameArrayList[position]
            textViewMedicineType?.text= medicineTypeArrayList[position]
            textViewMedicinePrice?.text= "${medicinePriceArrayList[position]} ₺"
            textViewMedicineDescription?.text= medicineDescriptionArrayList[position]
            Picasso.get().load(medicineUrlArrayList[position]).into(imageViewMedicine)

            dialog.show()
        }



    }

    override fun getItemCount(): Int {
        return medicineNameArrayList.size
    }
}