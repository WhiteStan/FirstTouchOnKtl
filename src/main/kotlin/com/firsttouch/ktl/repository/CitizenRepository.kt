package com.firsttouch.ktl.repository

import com.firsttouch.ktl.model.CitizenDTO
import com.firsttouch.ktl.tables.records.CitizenRecord

interface CitizenRepository {
    fun findAll(): List<CitizenDTO>
    fun findById(id: Int): CitizenDTO?
    fun findByMajorFields(citizen: CitizenDTO): List<CitizenDTO>?
    fun insert(citizen: CitizenDTO): CitizenDTO?
    fun update(citizenDto: CitizenDTO): CitizenDTO?
    fun remove(id: Int)
    fun removeAll()
}