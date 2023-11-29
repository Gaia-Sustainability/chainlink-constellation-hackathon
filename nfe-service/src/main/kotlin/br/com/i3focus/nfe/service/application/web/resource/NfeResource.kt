package br.com.i3focus.nfe.service.application.web.resource

import br.com.i3focus.nfe.service.application.web.dto.response.NfeCalculatedCarbonFreeResponse
import br.com.i3focus.nfe.service.application.web.dto.response.NfeCreatedResponse
import br.com.i3focus.nfe.service.domain.nfe.NfeService
import br.com.i3focus.nfe.service.domain.nfe.entity.dto.NfeDTO
import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.validation.Valid
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(
    value = ["/v1/api/nfes"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class NfeResource(
    private val nfeService: NfeService
) {

    @GetMapping
    fun getAllNfes(
        @PageableDefault(
            size = 10,
            page = 0,
            sort = ["dateCreated"],
            direction = Sort.Direction.DESC
        )
        pageable: Pageable
    ): ResponseEntity<List<NfeDTO>> = ResponseEntity.ok(nfeService.findAll(pageable))

    @GetMapping("/{id}")
    fun getNfe(@PathVariable(name = "id") id: UUID): ResponseEntity<NfeDTO> =
        ResponseEntity.ok(nfeService.get(id))

    @GetMapping("/ncm/{ncm}")
    fun getNfesWithProductNcm(
        @PageableDefault(
            size = 10,
            page = 0,
            sort = ["dateCreated"],
            direction = Sort.Direction.DESC
        )
        pageable: Pageable,
        @PathVariable(name = "ncm") ncm: Long
    ): ResponseEntity<List<NfeDTO>> =
        ResponseEntity.ok(nfeService.findByProductNcm(ncm, pageable))

    @GetMapping("/ncm/{ncm}/carbon-free-calculation")
    fun getNfesWithProductNcmAndCalculateCarbonFree(
        @PageableDefault(
            size = 1,
            page = 0,
            sort = ["dateCreated"],
            direction = Sort.Direction.DESC
        )
        pageable: Pageable,
        @PathVariable(name = "ncm") ncm: Long
    ): ResponseEntity<List<NfeCalculatedCarbonFreeResponse>> =
        nfeService.findByProductNcm(ncm, pageable)
            .map { NfeCalculatedCarbonFreeResponse.from(it) }
            .let {
                return ResponseEntity.ok(it)
            }

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createNfe(@RequestBody @Valid nfeDTO: NfeDTO): ResponseEntity<NfeCreatedResponse> =
        nfeService.create(nfeDTO).let {
            return ResponseEntity(NfeCreatedResponse(it.toString()), HttpStatus.CREATED)
        }

    @PutMapping("/{id}")
    fun updateNfe(
        @PathVariable(name = "id") id: UUID,
        @RequestBody @Valid nfeDTO: NfeDTO
    ): ResponseEntity<UUID> =
        nfeService.update(id, nfeDTO).let {
            return ResponseEntity.ok(id)
        }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteNfe(@PathVariable(name = "id") id: UUID): ResponseEntity<Unit> =
        nfeService.delete(id).let {
            return ResponseEntity.noContent().build()
        }
}
