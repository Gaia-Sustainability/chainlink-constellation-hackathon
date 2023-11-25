package br.com.i3focus.nfe.service.domain.nfe.entity.dto

import br.com.i3focus.nfe.service.domain.nfe.entity.type.NfeType
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.OffsetDateTime
import java.util.UUID

class NfeDTO {

    var id: UUID? = null

    @NotNull
    var serie: Int? = null

    @NotNull
    @Size(max = 16)
    var codeKey: String? = null

    @NotNull
    var number: Long? = null

    @NotNull
    var type: NfeType? = null

    @NotNull
    var issuedOn: OffsetDateTime? = null

    var dateCreated: OffsetDateTime? = null

    @NotNull
    var company: Company? = null

    @NotNull
    @NotEmpty
    var products: List<Product>? = null
}
