package com.firsttouch.ktl.service

import com.firsttouch.ktl.model.RecordDTO

interface RecordsService {
    fun findAll(): List<RecordDTO>
    fun findById(id: Int): RecordDTO?
    fun insert(recordDto: RecordDTO): RecordDTO?
    fun update(recordDto: RecordDTO): RecordDTO?
}