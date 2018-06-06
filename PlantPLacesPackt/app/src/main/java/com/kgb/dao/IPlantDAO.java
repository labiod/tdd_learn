package com.kgb.dao;

import com.kgb.dto.PlantDTO;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * A collection of methods to acces plant data.
 * @author Krzysztof Betlej <labiod@wp.pl> on 5/30/18
 */
public interface IPlantDAO {

    NetworkDAO getNetworkDAO();

    void setNetworkDAO(NetworkDAO networkDAO);

    /**
     * Accept filter text, and return a collection of plants that contain that filter text.
     * @param filter the text we want to match in our returned list of plants.
     * @return a list of plants that contain the given filter text in either genus, species, cultivar, or common name.
     */
    List<PlantDTO> fetchPlants(String filter) throws IOException, JSONException;
}
