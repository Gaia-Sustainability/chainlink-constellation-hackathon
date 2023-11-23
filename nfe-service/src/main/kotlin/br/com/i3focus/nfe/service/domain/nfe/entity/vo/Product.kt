package br.com.i3focus.nfe.service.domain.nfe.entity.vo

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

class Product {

    @NotNull
    var code: String? = null

    @NotNull
    @Size(max = 50)
    var name: String? = null

}
