package com.developer.sergey.calculator.common


private val OPERATORS = arrayOf("+", "-", "/", "*")

fun String.isOperator(): Boolean {
    for (op in OPERATORS)
        if (op == this)
            return true
    return false
}

fun String.isLeftBracket(): Boolean {
    return this == "("
}

fun String.isRightBracket(): Boolean {
    return this == ")"
}

fun String.isPoint(): Boolean {
    return this == "."
}

fun String.definePriority(): Int {
    return if (this == "+" || this == "-")
        1
    else
        2
}

fun String.isNumeric(): Boolean {
    try {
        java.lang.Double.parseDouble(this)
    } catch (nfe: NumberFormatException) {
        return false
    }
    return true
}
