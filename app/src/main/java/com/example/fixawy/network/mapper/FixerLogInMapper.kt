package com.example.fixawy.network.mapper

import com.example.fixawy.model.City
import com.example.fixawy.model.Fixer
import com.example.fixawy.model.Job
import com.example.fixawy.network.EntityMapper
import com.example.fixawy.network.model.FixerLogInDTO

class FixerLogInMapper : EntityMapper<Fixer,FixerLogInDTO> {
    override fun fromEntityToDomainModel(entity: Fixer): FixerLogInDTO {
        var fixerLogInDTO = FixerLogInDTO()
        fixerLogInDTO.id = entity.id
        fixerLogInDTO.name = entity.name
        fixerLogInDTO.city?.id = entity.city?.id
        fixerLogInDTO.city?.nameAr = entity.city?.name
        fixerLogInDTO.department?.id = entity.job?.id
        fixerLogInDTO.department?.nameAr = entity.job?.name
        fixerLogInDTO.mobile = entity.mobile
        fixerLogInDTO.password = entity.password
        fixerLogInDTO.email = entity.email
        return fixerLogInDTO
    }

    override fun fromDomainModelToEntity(model: FixerLogInDTO): Fixer {
        var fixer = Fixer(model.id,model.name)
        fixer.mobile = model.mobile
        fixer.city = City()
        fixer.city?.id = model.city?.id
        fixer.city?.name = model.city?.nameAr
        fixer.job = Job()
        fixer.job?.id = model.department?.id
        fixer.job?.name = model.department?.nameAr
        fixer.password = model.password
        fixer.email = model.email
        return fixer
    }
}