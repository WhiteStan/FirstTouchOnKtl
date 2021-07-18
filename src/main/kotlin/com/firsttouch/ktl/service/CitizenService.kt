package com.firsttouch.ktl.service

import com.firsttouch.ktl.model.CitizenDTO

interface CitizenService {
    suspend fun findAll(): List<CitizenDTO>
    suspend fun findById(id: Int): CitizenDTO?
    suspend fun insert(citizenDto: CitizenDTO): CitizenDTO?
    suspend fun update(citizenDto: CitizenDTO): CitizenDTO?
    suspend fun delete(id: Int)
}