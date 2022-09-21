package com.example.im.extentions

fun String.isValidUserName(): Boolean = this.matches(Regex("^[a-zA-Z]\\w{3,10}"))
fun String.isValidPassword(): Boolean = this.matches(Regex("^[1-9]\\w{3,10}$"))


fun <K, V> MutableMap<K, V>.toChangeArray(): Array<Pair<K, V>> {
    return map {
        Pair(it.key, it.value)
    }.toTypedArray()
}
