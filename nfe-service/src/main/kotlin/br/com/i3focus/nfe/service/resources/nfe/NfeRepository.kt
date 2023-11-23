package br.com.i3focus.nfe.service.resources.nfe

import br.com.i3focus.nfe.service.domain.nfe.entity.Nfe
import java.util.UUID
import org.springframework.data.mongodb.repository.MongoRepository


interface NfeRepository : MongoRepository<Nfe, UUID> {

    fun existsByNumber(number: Long?): Boolean

}
