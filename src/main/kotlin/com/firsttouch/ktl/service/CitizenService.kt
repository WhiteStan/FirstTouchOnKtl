package com.firsttouch.ktl.service

import com.firsttouch.ktl.model.CitizenDTO

interface CitizenService {
    fun findAll(): List<CitizenDTO>
    fun findById(id: Int): CitizenDTO?
    fun insert(citizenDto: CitizenDTO): CitizenDTO?
    fun update(citizenDto: CitizenDTO): CitizenDTO?
    fun delete(id: Int)
}