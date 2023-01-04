package com.safakkurt.eczaneotomasyonuv2.debtor.service

import com.safakkurt.eczaneotomasyonuv2.debtor.model.DebtorModel
import retrofit2.Call
import retrofit2.http.GET

interface DebtorAPI {
    @GET("b9ab36c883215e19cd1e")
    fun getData(): Call<List<DebtorModel>>
}