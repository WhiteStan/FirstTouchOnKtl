package com.firsttouch.ktl.service.impl

import com.firsttouch.ktl.model.CitizenDTO
import com.firsttouch.ktl.repository.CitizenRepository
import com.firsttouch.ktl.service.CitizenService
import org.springframework.stereotype.Service

@Service
class CitizenServiceImpl(private val repository: CitizenRepository) : CitizenService {
    override fun findAll(): List<CitizenDTO> = repository.findAll()

    override fun findById(id: Int): CitizenDTO? = repository.findById(id)

    override fun insert(citizenDto: CitizenDTO): CitizenDTO? = repository.insert(citizenDto)

    override fun update(citizenDto: CitizenDTO): CitizenDTO? = repository.update(citizenDto)

    override fun delete(id: Int) = repository.remove(id)
}
