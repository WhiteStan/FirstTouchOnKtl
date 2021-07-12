package com.firsttouch.ktl.service.impl

import com.firsttouch.ktl.model.RecordDTO
import com.firsttouch.ktl.model.toRecordRecord
import com.firsttouch.ktl.repository.RecordsRepository
import com.firsttouch.ktl.service.RecordsService
import org.springframework.stereotype.Service

@Service
class RecordsServiceImpl(private val repository: RecordsRepository) : RecordsService {
    override fun findAll(): List<RecordDTO> = repository.findAll()

    override fun findById(id: Int): RecordDTO? = repository.findById(id)

    override fun insert(recordDto: RecordDTO): RecordDTO? = repository.insert(recordDto.toRecordRecord())

    override fun update(recordDto: RecordDTO): RecordDTO? = repository.update(recordDto)
}
