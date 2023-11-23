package br.com.i3focus.nfe.service.domain.nfe

import br.com.i3focus.nfe.service.application.web.dto.request.NfeDTO
import br.com.i3focus.nfe.service.domain.exception.NotFoundException
import br.com.i3focus.nfe.service.domain.nfe.entity.Nfe
import br.com.i3focus.nfe.service.resources.nfe.NfeRepository
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.util.*

@Service
class NfeService(private val nfeRepository: NfeRepository) {

    fun findAll(): List<NfeDTO> {
        val nfes = nfeRepository.findAll(Sort.by("id"))
        return nfes.stream()
            .map { nfe -> mapToDTO(nfe, NfeDTO()) }
            .toList()
    }

    fun `get`(id: UUID): NfeDTO = nfeRepository.findById(id)
        .map { nfe -> mapToDTO(nfe, NfeDTO()) }
        .orElseThrow { NotFoundException() }

    fun create(nfeDTO: NfeDTO): UUID {
        val nfe = Nfe()
        mapToEntity(nfeDTO, nfe)
        return nfeRepository.save(nfe).id!!
    }

    fun update(id: UUID, nfeDTO: NfeDTO) {
        val nfe = nfeRepository.findById(id)
            .orElseThrow { NotFoundException() }
        mapToEntity(nfeDTO, nfe)
        nfeRepository.save(nfe)
    }

    fun delete(id: UUID) {
        nfeRepository.deleteById(id)
    }

    private fun mapToDTO(nfe: Nfe, nfeDTO: NfeDTO): NfeDTO {
        nfeDTO.id = nfe.id
        nfeDTO.serie = nfe.serie
        nfeDTO.codeKey = nfe.codeKey
        nfeDTO.number = nfe.number
        nfeDTO.issuedOn = nfe.issuedOn
        nfeDTO.type = nfe.type
        return nfeDTO
    }

    private fun mapToEntity(nfeDTO: NfeDTO, nfe: Nfe): Nfe {
        nfe.serie = nfeDTO.serie
        nfe.codeKey = nfeDTO.codeKey
        nfe.number = nfeDTO.number
        nfe.issuedOn = nfeDTO.issuedOn
        nfe.type = nfeDTO.type
        return nfe
    }

    fun numberExists(number: Long?): Boolean = nfeRepository.existsByNumber(number)

}
