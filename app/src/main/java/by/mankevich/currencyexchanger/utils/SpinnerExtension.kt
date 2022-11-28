package by.mankevich.currencyexchanger.utils

import android.widget.Spinner

@Suppress("UNCHECKED_CAST")
fun <T>Spinner.getList(): List<T> {
    val list: List<T>
    if (adapter!=null) {
        val n: Int = adapter.count
        list = ArrayList(n)
        for (i in 0 until n) {
            val currencyType = adapter.getItem(i) as T
            list.add(currencyType)
        }
    } else {
        list = listOf()
    }
    return list
}