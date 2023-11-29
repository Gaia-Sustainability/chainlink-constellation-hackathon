package br.com.i3focus.nfe.service.domain.nfe

import br.com.i3focus.nfe.service.domain.exception.NotFoundException
import br.com.i3focus.nfe.service.domain.nfe.entity.dto.NfeDTO
import br.com.i3focus.nfe.service.domain.nfe.mapper.NfeDTOMapper
import br.com.i3focus.nfe.service.domain.nfe.mapper.NfeEntityMapper
import br.com.i3focus.nfe.service.resources.nfe.NfeRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class NfeService(private val nfeRepository: NfeRepository) {
    private val entityMapper = NfeEntityMapper()
    private val dtoMapper = NfeDTOMapper()

    fun findAll(pageable: Pageable): List<NfeDTO> {
        val nfes = nfeRepository.findAll(pageable)
        return nfes.stream()
            .map { nfe -> dtoMapper.mapFrom(nfe, NFE_NULL) }
            .toList()
    }

    fun `get`(id: UUID): NfeDTO = nfeRepository.findById(id)
        .map { nfe -> dtoMapper.mapFrom(nfe, NFE_NULL) }
        .orElseThrow { NotFoundException() }

    fun create(nfeDTO: NfeDTO): UUID {
        val nfe = entityMapper.mapFrom(nfeDTO, NFE_NULL)
        return nfeRepository.save(nfe).id!!
    }

    fun update(id: UUID, nfeDTO: NfeDTO) {
        val nfe = nfeRepository.findById(id)
            .orElseThrow { NotFoundException() }

        entityMapper.mapFrom(nfeDTO, nfe).run {
            nfeRepository.save(this)
        }
    }

    fun delete(id: UUID) {
        nfeRepository.deleteById(id)
    }

    fun numberExists(number: Long): Boolean = nfeRepository.existsByNumber(number)

    fun findByProductNcm(ncm: Long, pageable: Pageable): List<NfeDTO> =
        nfeRepository.findByProducts_Ncm(ncm, pageable).map {
            dtoMapper.mapFrom(it, NFE_NULL)
        }

    companion object {
        private val NFE_NULL = null
    }
}
