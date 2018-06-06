package com.kgb.dao

import com.kgb.dto.PlantDTO

class PlantJsonDao : IPlantDAO {
    override fun getNetworkDAO(): NetworkDAO {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setNetworkDAO(networkDAO: NetworkDAO?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fetchPlants(filter: String): List<PlantDTO> {
        return listOf()
    }
}