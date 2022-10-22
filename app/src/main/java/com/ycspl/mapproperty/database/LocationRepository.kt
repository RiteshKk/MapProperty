package com.ycspl.mapproperty.database

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocationRepository @Inject constructor(private val locationDao: LocationDao) {

    fun getLocations(): Flow<List<LocationEntity>> = locationDao.getAllLocations()

    suspend fun insert(location: LocationEntity) = locationDao.insert(location)

    suspend fun deleteLocation(location: LocationEntity) = locationDao.delete(location)
}