package com.safakkurt.eczaneotomasyonuv2.medicine.service

import com.safakkurt.eczaneotomasyonuv2.medicine.model.MedicineModel
import retrofit2.Call
import retrofit2.http.GET

interface MedicineAPI {
    @GET("954fa8022e73ca930cd5")
    fun getData(): Call<List<MedicineModel>>
}