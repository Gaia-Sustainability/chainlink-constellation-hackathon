package br.com.i3focus.nfe.service.resources.nfe

import br.com.i3focus.nfe.service.domain.nfe.entity.Nfe
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface NfeRepository : MongoRepository<Nfe, UUID> {

    fun existsByNumber(number: Long): Boolean

    fun findByProducts_Ncm(ncm: Long): List<Nfe>

}
