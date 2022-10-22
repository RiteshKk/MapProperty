package com.ycspl.mapproperty

import android.util.Log
import androidx.lifecycle.LiveData
import com.ycspl.mapproperty.database.LocationRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ycspl.mapproperty.database.LocationEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(private val repository: LocationRepository) : ViewModel() {
    fun insertLocation(propertyName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val model = LocationEntity(propertyName = propertyName, latitude = latitude, longitude = longitude)
            repository.insert(model)
        }
    }

    /* For testing purpose only */
    fun getSavedLocation() {
        viewModelScope.launch {
            repository.getLocations().collectLatest { value ->
                Log.d("SavedLocation", "$value")
            }
        }
    }

    /* observe list to show in Recycler view */
    val getSavedLocation: LiveData<List<LocationEntity>> =
        repository.getLocations().flowOn(Dispatchers.Main).asLiveData(viewModelScope.coroutineContext)

    var latitude: Double = 0.0
    var longitude: Double = 0.0
}