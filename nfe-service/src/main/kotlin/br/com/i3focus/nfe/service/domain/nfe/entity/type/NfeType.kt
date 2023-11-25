package br.com.i3focus.nfe.service.domain.nfe.entity.type


enum class NfeType(value: Int) {

    ONE(1);

    companion object {
        fun fromValue(value: Int): NfeType? {
            return when (value) {
                1 -> ONE
                else -> null
            }
        }
    }
}
