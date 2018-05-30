package com.kgb.dao

import com.kgb.dto.PlantDTO

class PlantJsonDao : IPlantDAO {
    override fun fetchPlants(filter: String): List<PlantDTO> {
        return listOf()
    }
}