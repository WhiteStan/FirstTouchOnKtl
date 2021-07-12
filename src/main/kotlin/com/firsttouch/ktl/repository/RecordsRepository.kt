package com.firsttouch.ktl.repository

import com.firsttouch.ktl.model.RecordDTO
import com.firsttouch.ktl.tables.records.RecordRecord

interface RecordsRepository {
    fun findAll(): List<RecordDTO>
    fun findById(id: Int): RecordDTO?
    fun insert(record: RecordRecord): RecordDTO?
    fun update(recordDto: RecordDTO): RecordDTO?
}