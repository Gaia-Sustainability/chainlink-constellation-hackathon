package br.com.i3focus.nfe.service.domain.nfe.entity.dto

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.math.BigDecimal

class Product {

    @NotNull
    var code: String? = null

    @NotNull
    @Size(max = 50)
    var name: String? = null

    var ean: String? = null

    @NotNull
    var ncm: Long? = null

    @NotNull
    var unity: String? = null

    @NotNull
    var quantity: Int? = null

    @NotNull
    var price: BigDecimal? = null

    @NotNull
    var amount: BigDecimal? = null

    @NotNull
    var itemNumber: String? = null
}
