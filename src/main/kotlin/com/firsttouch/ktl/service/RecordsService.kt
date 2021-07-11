package com.firsttouch.ktl.service

import com.firsttouch.ktl.model.RecordDTO
import com.firsttouch.ktl.model.toRecordRecord
import com.firsttouch.ktl.repository.RecordsRepository
import org.springframework.stereotype.Service

@Service
class RecordsService(private val repository: RecordsRepository) {
    fun findAll(): List<RecordDTO> = repository.findAll()

    fun findById(id: Int): RecordDTO? = repository.getById(id)

    fun insert(recordDto: RecordDTO): RecordDTO? = repository.insert(recordDto.toRecordRecord())

    fun update(recordDto: RecordDTO): RecordDTO? = repository.update(recordDto)
}
