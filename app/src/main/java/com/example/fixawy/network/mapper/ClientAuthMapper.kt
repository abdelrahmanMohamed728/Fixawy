package com.example.fixawy.network.mapper

import com.example.fixawy.model.City
import com.example.fixawy.model.Client
import com.example.fixawy.network.EntityMapper
import com.example.fixawy.network.model.ClientAuthDTO

class ClientAuthMapper : EntityMapper<Client, ClientAuthDTO> {
    override fun fromEntityToDomainModel(entity: Client): ClientAuthDTO {
        var clientDTO = ClientAuthDTO()
        clientDTO.id = entity.id
        clientDTO.cityId = entity.city?.id
        clientDTO.email = entity.email
        clientDTO.mobile = entity.mobile
        clientDTO.nameEn = entity.name
        clientDTO.nameAr = entity.name
        clientDTO.password = entity.password
        return clientDTO
    }

    override fun fromDomainModelToEntity(model: ClientAuthDTO): Client {
        var client = Client(model.id!!, model.nameAr!!)
        client.city = City()
        client.city?.id = model.cityId
        client.email = model.email
        client.password = model.password
        client.mobile = model.mobile
        return client
    }
}