package com.example.fixawy.network.mapper

import com.example.fixawy.model.City
import com.example.fixawy.model.Client
import com.example.fixawy.network.EntityMapper
import com.example.fixawy.network.model.ClientLogInDTO

class ClientLogInMapper : EntityMapper<Client, ClientLogInDTO> {
    override fun fromEntityToDomainModel(entity: Client): ClientLogInDTO {
        var clientDTO = ClientLogInDTO()
        clientDTO.email = entity.email
        clientDTO.mobile = entity.mobile
        clientDTO.nameEn = entity.name
        clientDTO.nameAr = entity.name
        clientDTO.password = entity.password
        return clientDTO
    }

    override fun fromDomainModelToEntity(model: ClientLogInDTO): Client {
        var client = Client(model.id!!, model.nameAr!!)
        client.city = City()
        client.city?.id = model.city?.id
        client.city?.name = model.city?.nameAr
        client.email = model.email
        client.password = model.password
        client.mobile = model.mobile
        return client
    }
}