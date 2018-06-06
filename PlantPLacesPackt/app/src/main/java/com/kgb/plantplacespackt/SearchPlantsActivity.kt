package com.kgb.plantplacespackt

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.kgb.dto.PlantDTO
import com.kgb.service.IPlantService
import com.kgb.service.PlantService
import kotlinx.android.synthetic.main.activity_search_plants.*
import org.json.JSONException
import java.io.IOException

class SearchPlantsActivity : AppCompatActivity() {

    lateinit var plantService: IPlantService
    var plants: List<PlantDTO>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_plants)
        plantService = PlantService()
    }

    @Throws(IOException::class, JSONException::class)
    fun searchPlants(v: View) {
        val search = act_plant_name.text.toString()
        val pst = PlantSearchTask()
        pst.execute(search)
    }

    internal inner class PlantSearchTask : AsyncTask<String, Integer, List<PlantDTO>>() {

        override fun onPostExecute(plants: List<PlantDTO>?) {
            val adapter : ArrayAdapter<PlantDTO> = ArrayAdapter(this@SearchPlantsActivity, android.R.layout.simple_list_item_1, plants)
            plant_list.adapter = adapter
            this@SearchPlantsActivity.plants = plants
        }

        override fun doInBackground(vararg params: String): List<PlantDTO>? {
            var plants: List<PlantDTO>? = null
            try {
                plants = plantService.fetchPlants(params[0])
            } catch (e: Exception) {
                Log.e(SearchPlantsActivity::javaClass.name, "exception: ", e)
            }
            return plants
        }

    }
}
