package com.firsttouch.ktl.controller

import com.firsttouch.ktl.model.RecordDTO
import com.firsttouch.ktl.service.RecordsService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/records")
class ApplicationRestController (private val recordsService: RecordsService) {

    @GetMapping
    fun findAll() = recordsService.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int) = recordsService.findById(id)

    @PutMapping
    fun insert(@RequestBody req: RecordDTO) = recordsService.insert(req)

    @PostMapping
    fun update(@RequestBody req: RecordDTO) = recordsService.update(req)
}