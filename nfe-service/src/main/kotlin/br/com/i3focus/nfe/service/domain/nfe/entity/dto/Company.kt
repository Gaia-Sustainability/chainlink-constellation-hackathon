package br.com.i3focus.nfe.service.domain.nfe.entity.dto

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

class Company {

    @NotNull
    var taxIdentifier: String? = null

    @NotNull
    @Size(max = 255)
    var name: String? = null

    var address: Address? = null

    class Address {
        var street: String? = null
        var number: Int? = null
        var neighborhood: String? = null
        var city: String? = null
        var state: String? = null
        var zipCode: String? = null
        var country: String? = null
    }
}
