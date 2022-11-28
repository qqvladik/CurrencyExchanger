package by.mankevich.currencyexchanger.utils

infix fun <T> Collection<T>.deepEqualTo(other: Collection<T>): Boolean {
    if (this !== other) {
        if (this.size != other.size) return false
        val areNotEqual = this.asSequence()
            .zip(other.asSequence())
            .map { (fromThis, fromOther) -> fromThis == fromOther }
            .contains(false)
        if (areNotEqual) return false
    }
    return true
}
