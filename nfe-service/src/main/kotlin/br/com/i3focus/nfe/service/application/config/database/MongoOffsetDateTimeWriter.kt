package br.com.i3focus.nfe.service.application.config.database

import java.time.OffsetDateTime
import java.util.Date
import org.bson.Document
import org.springframework.core.convert.converter.Converter

class MongoOffsetDateTimeWriter : Converter<OffsetDateTime, Document> {

    override fun convert(offsetDateTime: OffsetDateTime): Document {
        return Document().apply {
            put(DATE_FIELD, Date.from(offsetDateTime.toInstant()))
            put(OFFSET_FIELD, offsetDateTime.offset.toString())
        }
    }

    companion object {

        const val DATE_FIELD = "dateTime"
        const val OFFSET_FIELD = "offset"

    }

}
