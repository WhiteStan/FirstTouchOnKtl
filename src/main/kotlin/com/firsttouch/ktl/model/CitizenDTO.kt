package com.firsttouch.ktl.model

import com.firsttouch.ktl.tables.references.CITIZEN
import org.jooq.Record

data class CitizenDTO(val id: Int? = null, val firstName: String?,
                      val lastName: String?, val phone: String?)

fun Record.toCitizenDTO() =
        CitizenDTO(this[CITIZEN.ID], this[CITIZEN.FIRSTNAME],
                this[CITIZEN.LASTNAME], this[CITIZEN.PHONE])