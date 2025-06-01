package com.ivyapps.composehammer.domain.quickcode.compiler.parser

import com.ivyapps.composehammer.domain.quickcode.compiler.data.QuickCodeToken

class QCParserScope<T>(
    val tokens: List<QuickCodeToken>,
    initialPosition: Int,
) {
    var position = initialPosition
        private set

    fun or(
        a: QCParserScope<T>.() -> T?,
        b: QCParserScope<T>.() -> T?,
    ): T? {
        val aScope = QCParserScope<T>(tokens, position)
        val resA = aScope.a()
        if (resA != null) {
            position = aScope.position
            return resA
        }

        val bScope = QCParserScope<T>(tokens, position)
        val resB = bScope.b()
        if (resB != null) {
            position = bScope.position
            return resB
        }

        return null
    }

    fun consumeToken(): QuickCodeToken? =
        tokens.getOrNull(position++).run {
            if (this is QuickCodeToken.Then) null else this
        }

    fun locationDescription(): String =
        buildString {
            prevToken()?.let {
                append(it.toString())
                append(" ")
            }
            currentToken()?.let {
                append(it.toString())
                append(" ")
            }
            nextToken()?.let {
                append(it.toString())
                append(" ")
            }
        }

    fun prevToken(): QuickCodeToken? = tokens.getOrNull(position - 1)

    fun currentToken(): QuickCodeToken? = tokens.getOrNull(position)

    fun nextToken(): QuickCodeToken? = tokens.getOrNull(position + 1)

    fun changePosition(position: Int) {
        this.position = position
    }
}
