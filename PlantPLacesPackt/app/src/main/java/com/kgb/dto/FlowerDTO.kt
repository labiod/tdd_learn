package com.kgb.dto

class FlowerDTO(
        guid: Int?,
        genus: String,
        species: String,
        cultivar: String,
        common: String,
        type: String) : PlantDTO(guid, genus, species, cultivar, common, type) {
    override fun toString(): String {
        return "Flower : " + super.toString()
    }
}