package com.firsttouch.ktl.controller

import com.firsttouch.ktl.model.CitizenDTO
import com.firsttouch.ktl.service.CitizenService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/citizens")
class ApplicationRestController (private val citizenService: CitizenService) {

    @GetMapping
    fun findAll() = citizenService.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int) = citizenService.findById(id)

    @PostMapping
    fun insert(@RequestBody req: CitizenDTO) :CitizenDTO? = citizenService.insert(req)

    @PutMapping
    fun update(@RequestBody req: CitizenDTO) = citizenService.update(req)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int) = citizenService.delete(id)
}