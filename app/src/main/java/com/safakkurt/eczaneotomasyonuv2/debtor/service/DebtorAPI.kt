package com.safakkurt.eczaneotomasyonuv2.debtor.service

import com.safakkurt.eczaneotomasyonuv2.debtor.model.DebtorModel
import retrofit2.Call
import retrofit2.http.GET

interface DebtorAPI {
    @GET("c1ed9ffa2f7cb4c93670")
    fun getData(): Call<List<DebtorModel>>
    //b9ab36c883215e19cd1e
}