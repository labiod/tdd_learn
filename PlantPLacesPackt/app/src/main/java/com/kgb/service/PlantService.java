package com.kgb.service;

import com.kgb.dao.IPlantDAO;
import com.kgb.dao.PlantDAO;
import com.kgb.dao.PlantDAOStub;
import com.kgb.dto.PlantDTO;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * @author Krzysztof Betlej <labiod@wp.pl> on 5/30/18
 */
public class PlantService implements IPlantService {

    private IPlantDAO plantDAO;

    public PlantService() {
        plantDAO = new PlantDAO();
    }

    @Override
    public List<PlantDTO> fetchPlants(String filter) throws IOException, JSONException {
        return plantDAO.fetchPlants(filter);
    }
}
