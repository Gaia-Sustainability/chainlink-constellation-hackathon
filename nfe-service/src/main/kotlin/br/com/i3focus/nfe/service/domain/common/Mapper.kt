package br.com.i3focus.nfe.service.domain.common

fun interface Mapper<I,O> {

    fun mapFrom(input: I, auxiliary: O?): O
}
