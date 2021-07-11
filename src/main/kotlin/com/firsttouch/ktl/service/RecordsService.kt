package com.firsttouch.ktl.service

import com.firsttouch.ktl.repository.RecordsRepository
import com.firsttouch.ktl.tables.Record
import org.springframework.stereotype.Service

@Service
class RecordsService(private val repository: RecordsRepository) {
    fun findAll(): List<Record> = repository.findAll()
}
