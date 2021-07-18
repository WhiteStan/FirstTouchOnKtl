package com.firsttouch.ktl.repository

import com.firsttouch.ktl.model.CitizenDTO

interface CitizenRepository {
    suspend fun findAll(): List<CitizenDTO>
    suspend fun findById(id: Int): CitizenDTO?
    suspend fun findByMajorFields(citizen: CitizenDTO): List<CitizenDTO>?
    suspend fun insert(citizen: CitizenDTO): CitizenDTO?
    suspend fun update(citizenDto: CitizenDTO): CitizenDTO?
    suspend fun remove(id: Int)
    suspend fun removeAll()
}