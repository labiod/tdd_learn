package com.kgb.dao

import junit.framework.Assert.*
import org.junit.*

class TestPlantDAO {

    lateinit var plantDAO: IPlantDAO

    companion object {
        @BeforeClass
        @JvmStatic
        fun setupAllTest() {
            println("BeforeClass: running init after all tests")
        }

        @AfterClass
        @JvmStatic
        fun teardownAllTests() {
            println("AfterClass: tearing down after All tests")
        }
    }

    @Before
    fun setup() {
        println("Before: running init before each tests")
        plantDAO = PlantDAOStub()
    }

    @Test
    fun testPlantDAO_searchForRedbudShouldReturnAtLeastOneResult() {
        var redbudFound = false
        val plants = plantDAO.fetchPlants("Redbud")
        for (plant in plants) {
            if (plant.common.contains("Redbud")) {
                redbudFound = true
            }
        }

        assertTrue(redbudFound)
        println("TEST: Running redbud test")
    }

    @Test
    fun testPlantDAO_searchForOakShouldReturnAtLeastOneWhiteOak() {
        var redbudFound = false
        val plants = plantDAO.fetchPlants("Oak")
        for (plant in plants) {
            if (plant.genus.equals("White", true) &&
                    plant.species.contains("alba")) {
                redbudFound = true
            }
        }

        assertTrue(redbudFound)
        println("TEST: Running white oak test")
    }

    fun testPlantDAO_searchForEShouldReturnAtLeastTwoResults() {
        var redbudFound = false
        val plants = plantDAO.fetchPlants("e")
        val size = plants.size

        assertTrue(size > 2)
//        assertThat(size, is(greaterThan(2))
        println("TEST: this should not run, because it is not annotatated")
    }

    @After
    fun teardown() {
        println("After: tear down for each tests")
    }
}