package com.example.skyapartmentscleaning.data.checklist.points

data class CheckListCheckPoint(
    val textPoint: String,
    val textChipYes: String? = null,
    val textChipNo: String? = null,
    var chipSelection: Int? = null
)
