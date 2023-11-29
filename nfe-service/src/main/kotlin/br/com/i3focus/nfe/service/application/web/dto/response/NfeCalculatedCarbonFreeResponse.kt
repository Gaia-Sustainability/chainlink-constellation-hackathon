package br.com.i3focus.nfe.service.application.web.dto.response

import br.com.i3focus.nfe.service.domain.nfe.entity.dto.NfeDTO
import br.com.i3focus.nfe.service.domain.nfe.entity.dto.Product
import java.math.BigDecimal
import java.time.OffsetDateTime

data class NfeCalculatedCarbonFreeResponse(
    val nfeId: String,
    val nfeNumber: Number,
    val nfeIssuedOn: OffsetDateTime,
    val nfeTotalValue: BigDecimal,
    val companyName: String,
    val companyTaxIdentifier: String,
    val products: List<ProductCalculatedCarbonFreeResponse>
) {
    companion object {
        fun from(nfe: NfeDTO): NfeCalculatedCarbonFreeResponse =
            NfeCalculatedCarbonFreeResponse(
                nfeId = nfe.id.toString(),
                nfeNumber = nfe.number ?: 0,
                nfeIssuedOn = nfe.issuedOn ?: OffsetDateTime.now(),
                nfeTotalValue = nfe.products?.sumOf { it.amount ?: BigDecimal.ZERO } ?: BigDecimal.ZERO,
                companyName = nfe.company?.name ?: "",
                companyTaxIdentifier = nfe.company?.taxIdentifier ?: "",
                products = nfe.products?.map { ProductCalculatedCarbonFreeResponse.from(it) } ?: emptyList()
            )
    }
}

data class ProductCalculatedCarbonFreeResponse(
    val name: String,
    val ncm: Long,
    val quantity: Int,
    val unitaryPrice: BigDecimal,
    val amount: BigDecimal,
    val carbonCalculation: CarbonData
) {
    companion object {
        fun from(product: Product): ProductCalculatedCarbonFreeResponse =
            ProductCalculatedCarbonFreeResponse(
                name = product.name ?: "",
                ncm = product.ncm ?: 0,
                quantity = product.quantity ?: 0,
                unitaryPrice = product.price ?: BigDecimal.ZERO,
                amount = product.amount ?: BigDecimal.ZERO,
                carbonCalculation = CarbonData(
                    emissionSaving = getSavingCarbonEmission(product),
                    emissionFootprint = getCarbonFootprint(product)
                )
            )

        /**
         * 1 ton of wood = 1400 kg of CO2
         * carbon footprint = quantity (kg) / 1000 (TON) * 40%
         */
        private fun getCarbonFootprint(product: Product) =
            (product.quantity?.toBigDecimal()?.divide(TON))?.multiply(FORTY_PERCENT) ?: BigDecimal.ZERO

        /**
         * 60% of the wood is carbon saving by the method used to burning wood
         * saving = quantity (kg) / 1000 (TON) * 60%
         */
        private fun getSavingCarbonEmission(product: Product) =
            (product.quantity?.toBigDecimal()?.divide(TON))?.multiply(SIXTY_PERCENT) ?: BigDecimal.ZERO

        /**
         * Constants
         */
        private val TON = "1000".toBigDecimal()

        private val SIXTY_PERCENT = "0.6".toBigDecimal()

        private val FORTY_PERCENT = "0.4".toBigDecimal()

        private val CO2_EMISSION_FACTOR_BY_TON = "1.4".toBigDecimal()
    }

    data class CarbonData(
        val emissionFactorByTonOfWood: BigDecimal = CO2_EMISSION_FACTOR_BY_TON,
        val emissionFootprint: BigDecimal,
        val emissionSaving: BigDecimal
    ) {
        val finalEmissionFootprint: BigDecimal = emissionFootprint.multiply(emissionFactorByTonOfWood)
    }
}
