package br.com.i3focus.nfe.service.resources.nfe

import br.com.i3focus.nfe.service.domain.nfe.entity.Nfe
import java.util.UUID
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent
import org.springframework.stereotype.Component

@Component
class NfeListener : AbstractMongoEventListener<Nfe>() {

    override fun onBeforeConvert(event: BeforeConvertEvent<Nfe>) {
        if (event.source.id == null) {
            event.source.id = UUID.randomUUID()
        }
    }

}
