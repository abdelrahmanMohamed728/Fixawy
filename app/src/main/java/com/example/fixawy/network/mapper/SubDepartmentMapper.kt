package com.example.fixawy.network.mapper

import com.example.fixawy.model.SubDepartment
import com.example.fixawy.network.EntityMapper
import com.example.fixawy.network.model.SubDepartmentDTO

class SubDepartmentMapper : EntityMapper<SubDepartment,SubDepartmentDTO> {
    override fun fromEntityToDomainModel(entity: SubDepartment): SubDepartmentDTO {
        var subDepartmentDTO = SubDepartmentDTO()
        subDepartmentDTO.id = entity.id
        subDepartmentDTO.nameAr = entity.name
        return subDepartmentDTO
    }

    override fun fromDomainModelToEntity(model: SubDepartmentDTO): SubDepartment {
        var subDepartment = SubDepartment()
        subDepartment.id = model.id
        subDepartment.name = model.nameAr
        return subDepartment
    }
}