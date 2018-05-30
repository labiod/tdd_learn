package com.kgb.plantplacespackt

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.kgb.dto.PlantDTO
import com.kgb.service.IPlantService
import com.kgb.service.PlantService
import kotlinx.android.synthetic.main.activity_search_plants.*

class SearchPlantsActivity : AppCompatActivity() {

    lateinit var plantService: IPlantService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_plants)
        plantService = PlantService()
    }

    fun searchPlants(v: View) {
        val plants: List<PlantDTO> = plantService.fetchPlants(act_plant_name.text.toString())

        for (plant: PlantDTO in plants) {
            Toast.makeText(this, plant.toString(), Toast.LENGTH_LONG).show()
        }
    }
}
