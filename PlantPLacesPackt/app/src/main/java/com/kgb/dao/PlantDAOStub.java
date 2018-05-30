package com.kgb.dao;

import com.kgb.dto.FlowerDTO;
import com.kgb.dto.PlantDTO;
import com.kgb.dto.TreeDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Krzysztof Betlej <labiod@wp.pl> on 5/30/18
 */
public class PlantDAOStub implements IPlantDAO {
    @Override
    public List<PlantDTO> fetchPlants(String filter) {
        List<PlantDTO> allPlants = new ArrayList<>();
        PlantDTO plant = new TreeDTO(
                null,
                "Cercis",
                "canadensis",
                "",
                "Eastern Redbud",
                "tree",
                30,
                "Yellowish"
                );
        allPlants.add(plant);

        PlantDTO flower = new FlowerDTO(
                null,
                "Tropoleum",
                "spp",
                "",
                "Nasturtium",
                "flower"
        );
        allPlants.add(flower);
        return allPlants;
    }
}
