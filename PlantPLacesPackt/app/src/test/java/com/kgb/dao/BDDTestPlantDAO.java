package com.kgb.dao;

import com.kgb.dao.IPlantDAO;
import com.kgb.dao.NetworkDAO;
import com.kgb.dao.PlantDAO;
import com.kgb.dto.PlantDTO;

import org.hamcrest.beans.HasPropertyWithValue;
import org.json.JSONException;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.mockito.Mockito.*;

public class BDDTestPlantDAO {
    String redbudJSON = "{\"plants\":[ {\"id\":\"83\",\"genus\":\"Cercis\",\"species\":\"canadensis\",\"cultivar\":\"\",\"common\":\"Eastern Redbud\"}, {\"id\":\"1020\",\"genus\":\"Cercis\",\"species\":\"canadensis\",\"cultivar\":\"'Appalachian Red’\",\"common\":\"Appalachian Red Redbud\"}]}";
    String quercusJSON = "{\"plants\":[ {\"id\":\"286\",\"genus\":\"Quercus\",\"species\":\"acutissima\",\"cultivar\":\"\",\"common\":\"Sawtooth Oak\"}, {\"id\":\"182\",\"genus\":\"Quercus\",\"species\":\"alba\",\"cultivar\":\"\",\"common\":\"White Oak\"},{\"id\":\"480\",\"genus\":\"Quercus\",\"species\":\"robur\",\"cultivar\":\"\",\"common\":\"English Oak\"}, {\"id\":\"5293\",\"genus\":\"Quercus\",\"species\":\"robur\",\"cultivar\":\"Atropurpurea\",\"common\":\"\"}]}";
    String gibberishJSON = "{\"plants\":[ ] }-1";

    IPlantDAO plantDAO;
    private List<PlantDTO> plants;

    @Test
    public void testPlantDAO_fetchShouldReturnResultsForRedbud() throws IOException, JSONException {
        givenPlantDAOIsInitialized();
        whenSearchFor("Redbud");
        thenVerifyAtLeastOneCercisCanadensis();

    }

    @Test
    public void testPlantDAo_fetchShouldReturnAtLeastTwoOaksForQuerces() throws IOException, JSONException {
        givenPlantDAOIsInitialized();
        whenSearchFor("Quercus");
        thenVerifyTwoOaks();
    }

    @Test
    public void testPlantDAO_fetchShouldReturnGenusQuercusForQuercus() throws IOException, JSONException {
        givenPlantDAOIsInitialized();
        whenSearchFor("Quercus");
        thenVerifyAllGenusAreQuercus();
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
        assertThat(plants, empty());
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

    private void givenPlantDAOIsInitialized() throws IOException {
        plantDAO = new PlantDAO();
        // Here's where we mock
        NetworkDAO networkDAO = mock(NetworkDAO.class);
        when(networkDAO.fetch("http://plantplaces.com/perl/mobile/viewplantsjson.pl?Combined_Name=sklujapouetllkjsda;u"))
                .thenReturn(gibberishJSON);
        when(networkDAO.fetch("http://plantplaces.com/perl/mobile/viewplantsjson.pl?Combined_Name=Quercus"))
                .thenReturn(quercusJSON);
        when(networkDAO.fetch("http://plantplaces.com/perl/mobile/viewplantsjson.pl?Combined_Name=Redbud"))
                .thenReturn(redbudJSON);
        plantDAO.setNetworkDAO(networkDAO);

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


    private void thenVerifyAllGenusAreQuercus() {
        for (PlantDTO plant : plants) {
            assertThat(plant, hasProperty("genus",containsString("Quercus")));
        }
    }
}
