package br.com.i3focus.nfe.service.domain.exception

import java.lang.RuntimeException


class NotFoundException : RuntimeException {

    constructor() : super()

    constructor(message: String) : super(message)

}
