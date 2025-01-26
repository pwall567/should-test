/*
 * @(#) ErrorMessages.kt
 *
 * should-test  Kotlin testing functions
 * Copyright (c) 2024 Peter Wall
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package io.kstuff.test

import kotlin.reflect.KClass

@Suppress("ConstPropertyName", "MemberVisibilityCanBePrivate")
object ErrorMessages {

    internal const val valueShouldBe = "Value should be "
    internal const val valueShouldNotBe = "Value should not be "

    internal fun errorShouldBe(expected: Any?, actual: Any?): String = buildString {
        append(valueShouldBe)
        appendValue(expected)
        appendWas(actual)
    }

    internal fun errorShouldNotBe(actual: Any?): String = buildString {
        append(valueShouldNotBe)
        appendValue(actual)
    }

    internal fun errorShouldBePredicate(actual: Any?) = buildString {
        append("Value should match predicate")
        appendWas(actual)
    }

    internal fun <T> errorShouldBePredicate(actual: Any?, predicate: NamedPredicate<T>) = buildString {
        append(valueShouldBe)
        append(predicate.name)
        appendWas(actual)
    }

    internal fun errorShouldNotBePredicate(actual: Any?) = buildString {
        append("Value should not match predicate")
        appendWas(actual)
    }

    internal fun <T> errorShouldNotBePredicate(actual: Any?, predicate: NamedPredicate<T>) = buildString {
        append(valueShouldNotBe)
        append(predicate.name)
        appendWas(actual)
    }

    internal fun <T> errorShouldBeOneOf(collection: Iterable<T>, actual: Any?) = buildString {
        append("Value should be one of ")
        appendValue(collection)
        appendWas(actual)
    }

    internal fun <T> errorShouldNotBeOneOf(collection: Iterable<T>, actual: Any?) = buildString {
        append("Value should not be one of ")
        appendValue(collection)
        appendWas(actual)
    }

    internal fun errorShouldBeSameInstance(expected: Any?, actual: Any?) = buildString {
        append("Value should be same instance as ")
        appendValue(expected)
        appendWas(actual)
    }

    internal fun errorShouldNotBeSameInstance(expected: Any?) = buildString {
        append("Value should not be same instance as ")
        appendValue(expected)
    }

    @PublishedApi
    internal fun errorShouldThrowButThrew(expectedClass: KClass<*>, actualClass: KClass<*>) = buildString {
        append("Should throw ")
        append(expectedClass.simplifyName())
        append(" but threw ")
        append(actualClass.simplifyName())
    }

    @PublishedApi
    internal fun errorShouldThrowButCompleted(expectedClass: KClass<*>) = buildString {
        append("Should throw ")
        append(expectedClass.simplifyName())
        append(" but completed without exception")
    }

    @PublishedApi
    internal fun errorShouldThrowWithMessage(expected: String, actual: Any?) = buildString {
        append("Should throw with message ")
        appendValue(expected)
        appendWas(actual)
    }

    @PublishedApi
    internal fun errorShouldBeType(expectedClass: KClass<*>, actual: Any?) = buildString {
        append("Value should be ")
        append(expectedClass.simplifyName())
        append(", was ")
        if (actual == null)
            append("null")
        else
            append(actual::class.simplifyName())
    }

    internal fun Appendable.appendWas(actual: Any?) {
        append(", was ")
        appendValue(actual)
    }

    internal fun Appendable.appendValue(value: Any?, maxString: Int = 39, maxItems: Int = 8, maxEntries: Int = 5) {
        when {
            value == null -> append("null")
            value is CharArray -> appendCharArray(value, maxLength = maxString)
            value is IntArray -> appendItems(value.iterator(), maxItems = maxItems)
            value is LongArray -> appendItems(value.iterator(), maxItems = maxItems)
            value is ShortArray -> appendItems(value.iterator(), maxItems = maxItems)
            value is ByteArray -> appendItems(value.iterator(), maxItems = maxItems)
            value is BooleanArray -> appendItems(value.iterator(), maxItems = maxItems)
            value is DoubleArray -> appendItems(value.iterator(), maxItems = maxItems)
            value is FloatArray -> appendItems(value.iterator(), maxItems = maxItems)
            value::class.java.isArray -> appendItems((value as Array<*>).iterator(), maxItems = maxItems)
            value is String -> appendStringValue(value, maxLength = maxString)
            value is Char -> {
                append('\'')
                appendSanitized(value, '\'')
                append('\'')
            }
            value is Long -> {
                append(value.toString())
                append('L')
            }
            value is Float -> {
                append(value.toString())
                append('F')
            }
            value is Collection<*> -> appendItems(value.iterator(), maxItems = maxItems)
            value is Map<*, *> -> {
                append('{')
                if (value.isNotEmpty()) {
                    var count = 0
                    val iterator = value.entries.iterator()
                    while (true) {
                        append(' ')
                        if (++count > maxEntries) {
                            append("...")
                            break
                        }
                        val entry = iterator.next()
                        appendValue(entry.key, maxString = 19, maxItems = 5, maxEntries = 3)
                        append(": ")
                        appendValue(entry.value, maxString = 19, maxItems = 5, maxEntries = 3)
                        if (!iterator.hasNext())
                            break
                        append(',')
                    }
                }
                append(" }")
            }
            else -> append(value.toString())
        }
    }

    private fun Appendable.appendItems(iterator: Iterator<*>, maxItems: Int) {
        append('[')
        if (iterator.hasNext()) {
            var count = 0
            while (true) {
                append(' ')
                if (++count > maxItems) {
                    append("...")
                    break
                }
                appendValue(iterator.next(), maxString = 19, maxItems = 5, maxEntries = 3)
                if (!iterator.hasNext())
                    break
                append(',')
            }
        }
        append(" ]")
    }

    private fun Appendable.appendStringValue(value: String, maxLength: Int = 39) {
        append('"')
        if (value.length <= maxLength)
            appendSanitized(value, 0, value.length)
        else {
            val elisionString = " ... " // this could be parameterized (alongside maxLength) if that would be useful
            val left = (maxLength - elisionString.length) / 2
            val right = maxLength - elisionString.length - left
            appendSanitized(value, 0, left)
            append(elisionString)
            appendSanitized(value, value.length - right, value.length)
        }
        append('"')
    }

    private fun Appendable.appendSanitized(chars: CharSequence, start: Int, end: Int) {
        for (i in start until end)
            appendSanitized(chars[i], '"')
    }

    private fun Appendable.appendCharArray(value: CharArray, maxLength: Int = 39) {
        append('"')
        if (value.size <= maxLength)
            appendSanitized(value, 0, value.size)
        else {
            val elisionString = " ... " // this could be parameterized (alongside maxLength) if that would be useful
            val left = (maxLength - elisionString.length) / 2
            val right = maxLength - elisionString.length - left
            appendSanitized(value, 0, left)
            append(elisionString)
            appendSanitized(value, value.size - right, value.size)
        }
        append('"')
    }

    private fun Appendable.appendSanitized(chars: CharArray, start: Int, end: Int) {
        for (i in start until end)
            appendSanitized(chars[i], '"')
    }

    private fun Appendable.appendSanitized(ch: Char, quote: Char) {
        when (ch) {
            // there are library functions for this, but those functions may need to use this test library,
            // so repeating the functionality here avoids a circular dependency
            // (a good test library should have very few dependencies)
            quote -> {
                append('\\')
                append(quote)
            }
            '\\' -> append("\\\\")
            '\n' -> append("\\n")
            '\r' -> append("\\r")
            '\t' -> append("\\t")
            '\b' -> append("\\b")
            in ' '..'~' -> append(ch)
            else -> {
                append('\\')
                append('u')
                val code = ch.code
                val hexChars = "0123456789abcdef"
                append(hexChars[code ushr 12])
                append(hexChars[(code shr 8) and 15])
                append(hexChars[(code shr 4) and 15])
                append(hexChars[code and 15])
            }
        }
    }

    @PublishedApi internal fun KClass<*>.simplifyName(): String {
        val name = qualifiedName
        return when {
            name == null -> "<unknown type>"
            name.startsWith("kotlin.") && name.indexOf('.', 7) < 0 -> name.substring(7)
            name.startsWith("java.lang.") && name.indexOf('.', 10) < 0 -> name.substring(10)
            else -> name
        }
    }

}
