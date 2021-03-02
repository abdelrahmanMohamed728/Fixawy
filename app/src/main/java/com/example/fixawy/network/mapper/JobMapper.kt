package com.example.fixawy.network.mapper

import com.example.fixawy.model.Job
import com.example.fixawy.network.EntityMapper
import com.example.fixawy.network.model.JobDTO

class JobMapper : EntityMapper<Job,JobDTO> {
    override fun fromEntityToDomainModel(entity: Job): JobDTO {
        var jobDTO = JobDTO()
        jobDTO.id = entity.id
        jobDTO.nameAr = entity.name
        return jobDTO
    }

    override fun fromDomainModelToEntity(model: JobDTO): Job {
       var job = Job()
        job.id = model.id
        job.name = model.nameAr
        return job
    }
}