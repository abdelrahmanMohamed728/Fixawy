package com.example.fixawy.network.mapper

import com.example.fixawy.model.Request
import com.example.fixawy.network.EntityMapper
import com.example.fixawy.network.model.RequestDTO

class RequestMapper : EntityMapper<Request,RequestDTO> {

    var fixerMapper = FixerAuthMapper()
    var clientMapper = ClientAuthMapper()
    override fun fromEntityToDomainModel(entity: Request): RequestDTO {
        var requestDTO = RequestDTO()
        requestDTO.customer = clientMapper.fromEntityToDomainModel(entity.client!!)
        requestDTO.fixer = fixerMapper.fromEntityToDomainModel(entity.fixer!!)
        requestDTO.orderDate = entity.date
        requestDTO.id = entity.id
        requestDTO.status = entity.status!! - 2
        requestDTO.price = entity.price?.toInt()
        requestDTO.subDepartmentId = entity.subDepartmentId
        return requestDTO
    }

    override fun fromDomainModelToEntity(model: RequestDTO): Request {
        var request = Request()
        request.client = clientMapper.fromDomainModelToEntity(model.customer!!)
        request.fixer = fixerMapper.fromDomainModelToEntity(model.fixer!!)
        request.id = model.id
        request.date = model.orderDate
        request.status = model.status!! + 2
        request.price = model.price.toString()
        request.subDepartmentId = model.subDepartmentId
        return request
    }
}