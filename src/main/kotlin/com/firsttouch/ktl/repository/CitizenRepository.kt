package com.firsttouch.ktl.repository

import com.firsttouch.ktl.model.CitizenDTO
import com.firsttouch.ktl.tables.records.CitizenRecord

interface CitizenRepository {
    fun findAll(): List<CitizenDTO>
    fun findById(id: Int): CitizenDTO?
    fun insert(citizen: CitizenRecord): CitizenDTO?
    fun update(citizenDto: CitizenDTO): CitizenDTO?
}