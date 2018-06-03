package com.kgb.dao;

import com.kgb.dto.PlantDTO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlantDAO implements IPlantDAO {
    private final NetworkDAO networkDAO;

    public PlantDAO() {
        networkDAO = new NetworkDAO();
    }

    @Override
    public List<PlantDTO> fetchPlants(String filter) throws IOException, JSONException {
        String uri = "http://plantplaces.com/perl/mobile/viewplantsjson.pl?Combined_Name="+filter;
        String request = networkDAO.fetch(uri);

        // Create a variable to hold our return data.
        List<PlantDTO> allPlants = new ArrayList<PlantDTO>();

        // Parse the entire JSON String
        JSONObject root = new JSONObject(request);
        // get the array of plants from JSON
        JSONArray plants = root.getJSONArray("plants");

        for (int i = 0; i < plants.length(); i++) {
            // parse the JSON object into its fields and values.
            JSONObject jsonPlant = plants.getJSONObject(i);
            int guid = jsonPlant.getInt("id");
            String genus = jsonPlant.getString("genus");
            String species = jsonPlant.getString("species");
            String cultivar = jsonPlant.getString("cultivar");
            String common = jsonPlant.getString("common");

            // create a PLantDTO object that we will populate with JSON Data.
            PlantDTO plant = new PlantDTO(guid, genus, species, cultivar, common, "");

            // add our newly created Plant DTO to our collection.
            allPlants.add(plant);
        }
        // return our collection of planst.
        return allPlants;
    }
}
