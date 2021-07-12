package com.firsttouch.ktl.repository

import com.firsttouch.ktl.model.CitizenDTO
import com.firsttouch.ktl.model.toCitizenDTO
import com.firsttouch.ktl.tables.records.CitizenRecord
import com.firsttouch.ktl.tables.references.CITIZEN
import org.jooq.DSLContext
import org.springframework.stereotype.Repository

@Repository
class RecordsRepositoryImpl(private val dsl: DSLContext) : CitizenRepository {
    override fun findAll(): List<CitizenDTO> = dsl.select().from(CITIZEN)
            .fetch(){
                it.toCitizenDTO()
            }

    override fun findById(id: Int): CitizenDTO? = dsl.select()
            .from(CITIZEN)
            .where(CITIZEN.ID.eq(id))
            .limit(1)
            .fetch()
            .map {
                it.toCitizenDTO()
            }
            .firstOrNull()

    override fun insert(citizen: CitizenRecord) =
            dsl.insertInto(CITIZEN)
                    .set(citizen)
                    .execute()
                    .let { citizen.id?.let { it1 -> findById(it1) } }

    override fun update(citizenDto: CitizenDTO) =
            dsl.update(CITIZEN)
                    .set(CITIZEN.FIRSTNAME, citizenDto.firstName)
                    .set(CITIZEN.LASTNAME, citizenDto.lastName)
                    .set(CITIZEN.PHONE, citizenDto.phone)
                    .where(CITIZEN.ID.eq(citizenDto.id))
                    .execute()
                    .let { citizenDto.id?.let { it1 -> findById(it1) } }
}
