package com.firsttouch.ktl.repository

import com.firsttouch.ktl.model.RecordDTO
import com.firsttouch.ktl.model.toRecordDTO
import com.firsttouch.ktl.tables.Record
import com.firsttouch.ktl.tables.Record.RECORD
import com.firsttouch.ktl.tables.records.RecordRecord
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class RecordsRepositoryImpl(private val dsl: DSLContext) : RecordsRepository {
    override fun findAll(): List<RecordDTO> = dsl.select().from(RECORD)
            .fetch(){
                it.toRecordDTO()
            }

    override fun findById(id: Int): RecordDTO? = dsl.select()
            .from(RECORD)
            .where(RECORD.ID.eq(id))
            .limit(1)
            .fetch()
            .map {
                it.toRecordDTO()
            }
            .firstOrNull()

    override fun insert(record: RecordRecord) =
            dsl.insertInto(RECORD)
                    .set(record)
                    .execute()
                    .let { findById(record.id) }

    override fun update(recordDto: RecordDTO) =
            dsl.update(RECORD)
                    .set(RECORD.FIRSTNAME, recordDto.firstName)
                    .set(RECORD.LASTNAME, recordDto.lastName)
                    .set(RECORD.PHONE, recordDto.phone)
                    .where(RECORD.ID.eq(recordDto.id))
                    .execute()
                    .let { findById(recordDto.id)  }
}
