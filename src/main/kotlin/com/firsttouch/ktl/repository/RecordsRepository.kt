package com.firsttouch.ktl.repository

import com.firsttouch.ktl.model.RecordDTO
import com.firsttouch.ktl.model.toRecordDTO
import com.firsttouch.ktl.tables.Record
import com.firsttouch.ktl.tables.records.RecordRecord
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
class RecordsRepository(private val dsl: DSLContext) {
    fun findAll(): List<RecordDTO> = dsl.select().from(RECORD)
            .fetch(){
                it.toRecordDTO()
            }

    fun getById(id: Int): RecordDTO? = dsl.select()
            .from(RECORD)
            .where(RECORD.ID.eq(id))
            .limit(1)
            .fetch()
            .map {
                it.toRecordDTO()
            }
            .firstOrNull()

    fun insert(record: RecordRecord) =
            dsl.insertInto(RECORD)
                    .set(record)
                    .execute()
                    .let { getById(record.id) }

    fun update(record: RecordDTO) =
            dsl.update(RECORD)
                    .set(RECORD.FIRSTNAME, record.firstName)
                    .set(RECORD.LASTNAME, record.lastName)
                    .set(RECORD.PHONE, record.phone)
                    .where(RECORD.ID.eq(record.id))
                    .execute()
                    .let { getById(record.id)  }

    companion object {
        private val RECORD = Record.RECORD
    }
}
