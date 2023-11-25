package br.com.i3focus.nfe.service.domain.nfe.entity

import br.com.i3focus.nfe.service.domain.nfe.entity.type.NfeType
import br.com.i3focus.nfe.service.domain.nfe.entity.dto.Company
import br.com.i3focus.nfe.service.domain.nfe.entity.dto.Product
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.OffsetDateTime
import java.util.UUID
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.annotation.Version
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document("nfes")
class Nfe {

    @Id
    var id: UUID? = null

    @NotNull
    var serie: Int? = null

    @NotNull
    @Size(max = 16)
    var codeKey: String? = null

    @Indexed(unique = true)
    @NotNull
    var number: Long? = null

    @NotNull
    var type: NfeType? = null

    @NotNull
    var issuedOn: OffsetDateTime? = null

    @NotNull
    var company: Company? = null

    @NotNull
    @NotEmpty
    var products: List<Product>? = null

    @CreatedDate
    var dateCreated: OffsetDateTime? = null

    @LastModifiedDate
    var lastUpdated: OffsetDateTime? = null

    @Version
    var version: Int? = null
}
