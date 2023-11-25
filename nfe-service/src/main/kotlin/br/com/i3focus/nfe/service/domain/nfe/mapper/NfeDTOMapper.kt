package br.com.i3focus.nfe.service.domain.nfe.mapper

import br.com.i3focus.nfe.service.domain.common.Mapper
import br.com.i3focus.nfe.service.domain.nfe.entity.Nfe
import br.com.i3focus.nfe.service.domain.nfe.entity.dto.NfeDTO

class NfeDTOMapper : Mapper<Nfe, NfeDTO> {

    override fun mapFrom(input: Nfe, auxiliary: NfeDTO?): NfeDTO {
        return NfeDTO().apply {
            id = input.id
            serie = input.serie
            codeKey = input.codeKey
            number = input.number
            issuedOn = input.issuedOn
            type = input.type
            company = input.company
            products = input.products
        }
    }
}
