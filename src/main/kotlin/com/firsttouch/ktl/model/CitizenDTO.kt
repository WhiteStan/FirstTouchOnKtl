package com.firsttouch.ktl.model

import com.firsttouch.ktl.tables.records.CitizenRecord
import com.firsttouch.ktl.tables.references.CITIZEN
import org.jooq.Record

data class CitizenDTO(val id: Int?, val firstName: String?,
                      val lastName: String?, val phone: String?)

fun Record.toCitizenDTO() =
        CitizenDTO(this[CITIZEN.ID], this[CITIZEN.FIRSTNAME],
                this[CITIZEN.LASTNAME], this[CITIZEN.PHONE])

fun CitizenDTO.toCitizenRecord() =
        CitizenRecord(this.id, this.firstName, this.lastName, this.phone)