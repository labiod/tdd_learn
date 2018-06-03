package com.kgb.plantplacespackt;

import com.kgb.dao.IPlantDAO;
import com.kgb.dao.PlantDAO;
import com.kgb.dto.PlantDTO;

import org.json.JSONException;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class BDDTestPlantDAO {

    IPlantDAO plantDAO;
    private List<PlantDTO> plants;

    @Test
    public void testPlantDAO_fetchShouldReturnResultsForRedbud() throws IOException, JSONException {
        givenPlantDAOIsInitialized();
        whenSearchFor("Redbud");
        thenVerifyAtLeastOneCercisCanadensis();

    }

    private void givenPlantDAOIsInitialized() {
        plantDAO = new PlantDAO();
    }

    private void thenVerifyAtLeastOneCercisCanadensis() {
        boolean redbudFound = false;

        for (PlantDTO plant : plants) {
            if (plant.getGenus().contains("Cercis")
                    && plant.getSpecies().contains("canadensis")) {
                redbudFound = true;
            }
        }

        assertTrue(redbudFound);
    }

    @Test
    public void testPlantDAo_fetchShouldReturnAtLeastTwoOaksForQuerces() throws IOException, JSONException {
        givenPlantDAOIsInitialized();
        whenSearchFor("Quercus");
        thenVerifyTwoOaks();
    }

    @Test
    public void testPlantDAo_fetchShouldReturnNothingForGibberish() throws IOException, JSONException {
        givenPlantDAOIsInitialized();
        whenSearchFor("sklujapouetllkjsda;u");
        thenVerifyNoResult();
    }

    private void whenSearchFor(String filter) throws IOException, JSONException {
        plants = plantDAO.fetchPlants(filter);
    }

    private void thenVerifyNoResult() {
          assertEquals(0, plants.size());
    }

    private void thenVerifyTwoOaks() {
        boolean quercusRoburFound = false;

        for (PlantDTO plant : plants) {
            if (plant.getGenus().contains("Quercus")
                    && plant.getSpecies().contains("robur")
                    && plant.getCommon().contains("Oak")) {
                quercusRoburFound = true;
            }
        }

        assertTrue(quercusRoburFound);

        boolean quercusAlbaFound = false;

        for (PlantDTO plant : plants) {
            if (plant.getGenus().contains("Quercus")
                    && plant.getSpecies().contains("alba")
                    && plant.getCommon().contains("Oak")) {
                quercusAlbaFound = true;
            }
        }

        assertTrue(quercusAlbaFound);
    }
}
