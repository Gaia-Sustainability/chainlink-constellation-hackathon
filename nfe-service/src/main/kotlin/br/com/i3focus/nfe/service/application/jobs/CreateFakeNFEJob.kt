package br.com.i3focus.nfe.service.application.jobs

import br.com.i3focus.nfe.service.domain.nfe.entity.Nfe
import br.com.i3focus.nfe.service.domain.nfe.entity.dto.Company
import br.com.i3focus.nfe.service.domain.nfe.entity.dto.Product
import br.com.i3focus.nfe.service.domain.nfe.entity.type.NfeType
import br.com.i3focus.nfe.service.resources.nfe.NfeRepository
import jakarta.annotation.PostConstruct
import net.datafaker.Faker
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.time.OffsetDateTime
import kotlin.random.Random

@Component
@ConditionalOnProperty(name = ["app.jobs.enabled"], havingValue = "true")
class CreateFakeNFEJob {
    private val logger: Logger = LoggerFactory.getLogger(CreateFakeNFEJob::class.java)
    private val faker = Faker()

    @Autowired
    private lateinit var nfeRepository: NfeRepository

    @PostConstruct
    fun init() {
        logger.info("CreateFakeNFEJob initialized!")
    }

    @Scheduled(cron = "\${app.jobs.cron}", zone = "\${app.jobs.zone}")
    fun execute() {
        logger.info("Starting to create fake NFE...")

        val nfe = createFakeNFE()
        nfeRepository.save(nfe)

        logger.info("Finished to create fake NFE [id = {}] successfully!", nfe.id)
    }

    private fun createFakeNFE() =
        Nfe().apply {
            number = Random.nextInt(100000000, 999999999).toLong()
            codeKey = Random.nextInt(100000000, 999999999).toString()
            serie = NFE_SERIE
            type = NfeType.ONE
            issuedOn = OffsetDateTime.now()
            company = Company().apply {
                name = faker.company().name()
                taxIdentifier = faker.cnpj().valid()
                address = Company.Address().apply {
                    street = faker.address().streetName()
                    number = Random.nextInt(1, 9999)
                    neighborhood = faker.address().streetName()
                    city = faker.address().city()
                    state = faker.address().state()
                    zipCode = faker.address().zipCode()
                    country = faker.address().country()
                }
            }
            products = listOf(
                Product().apply {
                    name = faker.commerce().productName()
                    code = "B17025056_${faker.number().digits(8)}"
                    ean = "7891117059356"
                    ncm = 44011000 // https://cosmos.bluesoft.com.br/produtos/7891117059356-lenha-ecologica
                    unity = "Kg"
                    quantity = Random.nextInt(1, 9999)
                    price = BigDecimal("30.00")
                    amount = price?.multiply(BigDecimal(quantity!!))
                    itemNumber = "1"
                }
            )
        }

    companion object {
        private const val NFE_SERIE = 1
    }
}
