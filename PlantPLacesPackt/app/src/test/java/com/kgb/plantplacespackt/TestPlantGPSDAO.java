package com.kgb.plantplacespackt;

import com.kgb.dao.IPlantDAO;
import com.kgb.dao.ISpecimenDAO;
import com.kgb.dao.SpecimenDAO;
import com.kgb.dto.SpecimenDTO;

import org.junit.Test;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * @author Krzysztof Betlej <labiod@wp.pl> on 6/4/18
 */
public class TestPlantGPSDAO {
    private ISpecimenDAO mSpecimenDAO;
    private List<SpecimenDTO> mSpeciments;

    @Test
    public void testGPSDAO_fetchGPSReturnsResultWithinRange() {
        givenGPSDAOInitialized();
        whenSearchForSpecimentsNearCZBG();
        thenReturnSpecimensWithinRange();
    }

    private void givenGPSDAOInitialized() {
        mSpecimenDAO = new SpecimenDAO();
    }

    private void whenSearchForSpecimentsNearCZBG() {
        mSpecimenDAO.fetchSpecimens(39.1461, -84.50826);
    }

    private void thenReturnSpecimensWithinRange() {
        for (SpecimenDTO specimen : mSpeciments) {
            assertEquals(39.1461, specimen.getLatitude(), 0.002);
            assertEquals(-84.50826, specimen.getLongitude(), 0.002);
        }
    }
}
