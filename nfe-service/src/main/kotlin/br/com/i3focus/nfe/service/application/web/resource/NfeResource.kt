package br.com.i3focus.nfe.service.application.web.resource

import br.com.i3focus.nfe.service.application.web.dto.request.NfeDTO
import br.com.i3focus.nfe.service.domain.nfe.NfeService
import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.validation.Valid
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
    fun getAllNfes(): ResponseEntity<List<NfeDTO>> = ResponseEntity.ok(nfeService.findAll())

    @GetMapping("/{id}")
    fun getNfe(@PathVariable(name = "id") id: UUID): ResponseEntity<NfeDTO> =
        ResponseEntity.ok(nfeService.get(id))

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createNfe(@RequestBody @Valid nfeDTO: NfeDTO): ResponseEntity<UUID> {
        val createdId = nfeService.create(nfeDTO)
        return ResponseEntity(createdId, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateNfe(@PathVariable(name = "id") id: UUID, @RequestBody @Valid nfeDTO: NfeDTO):
            ResponseEntity<UUID> {
        nfeService.update(id, nfeDTO)
        return ResponseEntity.ok(id)
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteNfe(@PathVariable(name = "id") id: UUID): ResponseEntity<Unit> {
        nfeService.delete(id)
        return ResponseEntity.noContent().build()
    }

}
