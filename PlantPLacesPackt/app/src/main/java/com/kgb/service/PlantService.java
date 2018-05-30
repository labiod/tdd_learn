package com.kgb.service;

import com.kgb.dao.IPlantDAO;
import com.kgb.dao.PlantDAOStub;
import com.kgb.dao.PlantJsonDao;
import com.kgb.dto.PlantDTO;

import java.util.List;

/**
 * @author Krzysztof Betlej <labiod@wp.pl> on 5/30/18
 */
public class PlantService implements IPlantService {

    private IPlantDAO plantDAO;

    public PlantService() {
        plantDAO = new PlantDAOStub();
    }

    @Override
    public List<PlantDTO> fetchPlants(String filter) {
        return plantDAO.fetchPlants(filter);
    }
}
