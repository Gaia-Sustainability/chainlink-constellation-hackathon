package br.com.i3focus.nfe.service.domain.nfe.entity.vo

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

class Company {

    @NotNull
    var taxIdentifier: String? = null

    @NotNull
    @Size(max = 255)
    var name: String? = null

    var address: Address? = null

    sealed class Address {

        @NotNull
        @Size(max = 255)
        var street: String? = null

    }
}
