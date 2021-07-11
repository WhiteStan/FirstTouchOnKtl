package com.firsttouch.ktl.repository

import com.firsttouch.ktl.model.RecordDTO
import com.firsttouch.ktl.tables.Record
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
class RecordsRepository(private val dsl: DSLContext) {
    fun findAll(): List<RecordDTO> = dsl.select().from(RECORD)
            .fetch(){
                RecordDTO(it[RECORD.ID], it[RECORD.FIRSTNAME],
                        it[RECORD.LASTNAME], it[RECORD.PHONE])
            }

    companion object {
        private val RECORD = Record.RECORD
    }
}
