package com.safakkurt.eczaneotomasyonuv2.medicine.model

import com.safakkurt.eczaneotomasyonuv2.debtor.model.NumberDecimal

data class MedicineModel(
    val _id: String,
    val name: String,
    val image: String,
    val price: NumberDecimal,
    val description: String,
    val medicineType: String
)
