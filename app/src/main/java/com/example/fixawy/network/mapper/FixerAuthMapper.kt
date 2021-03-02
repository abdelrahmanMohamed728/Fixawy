package com.example.fixawy.network.mapper

import com.example.fixawy.model.City
import com.example.fixawy.model.Fixer
import com.example.fixawy.model.Job
import com.example.fixawy.network.EntityMapper
import com.example.fixawy.network.model.FixerDTO

class FixerAuthMapper : EntityMapper<Fixer,FixerDTO> {
    override fun fromEntityToDomainModel(entity: Fixer): FixerDTO {
        var fixerDTO = FixerDTO()
        fixerDTO.id = entity.id
        fixerDTO.email = entity.email
        fixerDTO.name = entity.name
        fixerDTO.mobile = entity.mobile
        fixerDTO.password = entity.password
        fixerDTO.cityId = entity.city?.id
        fixerDTO.departmentId = entity.job?.id
        return fixerDTO
    }

    override fun fromDomainModelToEntity(model: FixerDTO): Fixer {
        var fixer = Fixer(model.id,model.name)
        fixer.mobile = model.mobile
        fixer.email = model.email
        fixer.job = Job()
        fixer.job!!.id = model.id
        fixer.password = model.password
        fixer.city = City()
        fixer.city!!.id = model.cityId
        return fixer
    }
}