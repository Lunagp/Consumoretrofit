package com.example.consumoretrofit.data.models

import java.util.*

data class BasePersonas (
    val page:Int,
    val per_page:Int,
    val total:Int,
    val total_pages:Int,
    val data:List<Personas>,
    val support:Support
    )