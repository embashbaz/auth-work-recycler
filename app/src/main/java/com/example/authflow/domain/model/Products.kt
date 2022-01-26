package com.example.authflow.domain.model

data class Products(
    var category: String = "",
    var productList: MutableList<String> = mutableListOf()
)