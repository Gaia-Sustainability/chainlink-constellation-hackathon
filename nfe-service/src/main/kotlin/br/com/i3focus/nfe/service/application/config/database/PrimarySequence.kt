package br.com.i3focus.nfe.service.application.config.database

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("primarySequences")
class PrimarySequence {

    @Id
    var id: String? = null

    var seq: Long? = null

}
