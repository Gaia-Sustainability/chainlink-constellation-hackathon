package br.com.i3focus.nfe.service.application.config.database

import org.springframework.data.mongodb.core.FindAndModifyOptions
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Service

@Service
class PrimarySequenceService(
    private val mongoOperations: MongoOperations
) {

    fun getNextValue(): Long {
        return mongoOperations.findAndModify(
            Query.query(Criteria.where("_id").`is`(PRIMARY_SEQUENCE)),
            Update().inc("seq", 1),
            FindAndModifyOptions.options().returnNew(true),
            PrimarySequence::class.java
        )?.seq ?: PrimarySequence().apply {
            this.id = PRIMARY_SEQUENCE
            this.seq = PRIMARY_SEQUENCE_VALUE
        }.let {
            mongoOperations.insert(it)
        }.seq!!
    }


    companion object {

        const val PRIMARY_SEQUENCE = "primarySequence"
        const val PRIMARY_SEQUENCE_VALUE = 10_000L

    }

}
