package com.firsttouch.ktl.model

import com.firsttouch.ktl.tables.Record.RECORD
import com.firsttouch.ktl.tables.records.RecordRecord
import org.jooq.Record

data class RecordDTO(val id: Int, val firstName: String,
                     val lastName: String, val phone: String)

fun Record.toRecordDTO() =
        RecordDTO(this[RECORD.ID], this[RECORD.FIRSTNAME],
                this[RECORD.LASTNAME], this[RECORD.PHONE])

fun RecordDTO.toRecordRecord() =
        RecordRecord(this.id, this.firstName, this.lastName, this.phone)