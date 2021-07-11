package com.firsttouch.ktl.repository

import com.firsttouch.ktl.Tables
import com.firsttouch.ktl.tables.Record
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
class RecordsRepository(private val dsl: DSLContext) {
    fun findAll(): List<Record> = dsl.select().from(Tables.RECORD)
            .fetch().into(Record::class.java)
}
