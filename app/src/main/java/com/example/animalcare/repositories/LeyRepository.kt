package com.example.animalcare.repositories

import androidx.lifecycle.LiveData
import com.example.animalcare.database.daos.ley_dao
import com.example.animalcare.database.entities.ley_entity
import com.example.animalcare.service.retrofit.ApiService
import kotlinx.coroutines.Deferred
import retrofit2.Response

class LeyRepository(private val leyDao: ley_dao) {

    /**fun getAllLeyes(): LiveData<List<ley_entity>>{
        return leyDao.getAllLeyes()
    }**/

    val allLeyes: LiveData<List<ley_entity>> = leyDao.getAllLeyes()



    fun getLeyByArticulo(articulo: String): LiveData<List<ley_entity>> {
        return leyDao.getLeyByArticulo(articulo)
    }

    fun getLeyByApartado(apartado: String): LiveData<List<ley_entity>> {
        return leyDao.getLeyByApartado(apartado)
    }


    fun retrieveLeyByApartado(ley:String): Deferred<Response<LiveData<List<ley_entity>>>> {
        return ApiService.getAnimalService().getleye(ley)
    }

    fun retrieveLeyes(): Deferred<Response<List<ley_entity>>> {
        return ApiService.getAnimalService().getleyes()
    }
}