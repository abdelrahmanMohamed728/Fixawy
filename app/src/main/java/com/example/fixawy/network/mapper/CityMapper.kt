package com.example.fixawy.network.mapper

import com.example.fixawy.model.City
import com.example.fixawy.network.EntityMapper
import com.example.fixawy.network.model.CityDTO

class CityMapper : EntityMapper<City, CityDTO> {
    override fun fromEntityToDomainModel(entity: City): CityDTO {
        var cityDTO = CityDTO()
        cityDTO.id = entity.id
        cityDTO.nameAr = entity.name
        return cityDTO
    }

    override fun fromDomainModelToEntity(model: CityDTO): City {
        var city = City()
        city.id = model.id
        city.name = model.nameAr
        return city
    }
}