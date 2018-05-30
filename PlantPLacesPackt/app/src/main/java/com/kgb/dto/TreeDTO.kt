package com.kgb.dto

class TreeDTO(
        guid: Int?,
        genus: String,
        species: String,
        cultivar: String,
        common: String,
        type: String,
        val size: Int,
        val fallColor: String) : PlantDTO(guid, genus, species, cultivar, common, type) {

    override fun toString(): String {
        return "Tree : " + super.toString() + " $size $fallColor"
    }
}