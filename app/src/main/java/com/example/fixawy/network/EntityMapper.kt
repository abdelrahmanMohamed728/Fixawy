package com.example.fixawy.network

interface EntityMapper <Entity,DomainModel>{

    fun fromEntityToDomainModel(entity : Entity) : DomainModel

    fun fromDomainModelToEntity(model : DomainModel) : Entity
}