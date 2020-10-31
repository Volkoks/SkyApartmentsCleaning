package com.example.skyapartmentscleaning.ui.allCheckHistory

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.skyapartmentscleaning.data.ViewState
import com.example.skyapartmentscleaning.data.entites.apart.ApartSource
import com.example.skyapartmentscleaning.MyApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class AllCheckHistoryViewModel : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext by lazy {
        Dispatchers.Default
    }
    private val apartDao by lazy {
        MyApp.apartDB.getApartDao()
    }
    private val apartSource: ApartSource? by lazy {
        ApartSource(apartDao)
    }
    var verifiedApartments: MutableLiveData<ViewState> = MutableLiveData()

    init {
        launch {
            verifiedApartments.postValue(apartSource?.loadListAllApart()?.let { ViewState(it) })

        }
    }
}