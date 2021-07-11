package com.firsttouch.ktl.service

import com.firsttouch.ktl.model.RecordDTO
import com.firsttouch.ktl.repository.RecordsRepository
import org.springframework.stereotype.Service

@Service
class RecordsService(private val repository: RecordsRepository) {
    fun findAll(): List<RecordDTO> = repository.findAll()
}
