package com.safakkurt.eczaneotomasyonuv2.debtor.model

data class DebtorModel(
    val tc: String,
    val name: String,
    val total: NumberDecimal,
    val status: String,
    val medicine: List<Medicine>
)
