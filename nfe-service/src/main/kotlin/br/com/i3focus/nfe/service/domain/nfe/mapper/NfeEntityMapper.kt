package br.com.i3focus.nfe.service.domain.nfe.mapper

import br.com.i3focus.nfe.service.domain.common.Mapper
import br.com.i3focus.nfe.service.domain.nfe.entity.Nfe
import br.com.i3focus.nfe.service.domain.nfe.entity.dto.NfeDTO

class NfeEntityMapper : Mapper<NfeDTO, Nfe> {
    override fun mapFrom(input: NfeDTO, auxiliary: Nfe?): Nfe {
        val nfe = auxiliary?.apply {
            this.id = input.id
            this.serie = input.serie
            this.codeKey = input.codeKey
            this.number = input.number
            this.issuedOn = input.issuedOn
            this.type = input.type
            this.company = input.company
            this.products = input.products
        } ?: Nfe().apply {
            id = input.id
            serie = input.serie
            codeKey = input.codeKey
            number = input.number
            issuedOn = input.issuedOn
            type = input.type
            company = input.company
            products = input.products
        }

        return nfe
    }
}
