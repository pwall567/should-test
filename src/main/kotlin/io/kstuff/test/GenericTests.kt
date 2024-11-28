/*
 * @(#) GenericTests.kt
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

@file:Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package io.kstuff.test

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract
import kotlin.internal.InlineOnly
import kotlin.internal.OnlyInputTypes
import kotlin.reflect.KClass
import kotlin.test.asserter

/**
 * Test that value is equal to expected.
 */
@InlineOnly infix fun <@OnlyInputTypes T> T.shouldBe(expected: T) {
    if (this != expected)
        asserter.fail("Value should be ${out(expected)}, was ${out(this)}")
}

/**
 * Test that lambda applied to the value returns `true`.
 */
@OptIn(ExperimentalContracts::class)
@InlineOnly infix fun <@OnlyInputTypes T> T.shouldBe(test: T.() -> Boolean) {
    contract { callsInPlace(test, InvocationKind.EXACTLY_ONCE) }
    if (!test())
        asserter.fail("Value should be valid for test, was ${out(this)}")
}

/**
 * Test that value is in the state described by a [StateCheck].
 */
@InlineOnly infix fun <@OnlyInputTypes T> T.shouldBe(stateCheck: StateCheck<T>) {
    if (!stateCheck.inState(this))
        asserter.fail("Value should be ${stateCheck.name}, was ${out(this)}")
}

/**
 * Test that value is not equal to expected.
 */
@InlineOnly infix fun <@OnlyInputTypes T> T.shouldNotBe(expected: T) {
    if (this == expected)
        asserter.fail("Value should not be ${out(expected)}")
}

/**
 * Test that lambda applied to the value returns `false`.
 */
@OptIn(ExperimentalContracts::class)
@InlineOnly infix fun <@OnlyInputTypes T> T.shouldNotBe(test: T.() -> Boolean) {
    contract { callsInPlace(test, InvocationKind.EXACTLY_ONCE) }
    if (test())
        asserter.fail("Value should not be valid for test, was ${out(this)}")
}

/**
 * Test that value is not in the state described by a [StateCheck].
 */
@InlineOnly infix fun <@OnlyInputTypes T> T.shouldNotBe(stateCheck: StateCheck<T>) {
    if (stateCheck.inState(this))
        asserter.fail("Value should not be ${stateCheck.name}, was ${out(this)}")
}

/**
 * Test that value is one of a collection.
 */
@InlineOnly infix fun <@OnlyInputTypes T> T.shouldBeOneOf(collection: Collection<T>) {
    if (this !in collection)
        asserter.fail("Value should be one of collection, was ${out(this)}")
}

/**
 * Test that value is not one of a collection.
 */
@InlineOnly infix fun <@OnlyInputTypes T> T.shouldNotBeOneOf(collection: Collection<T>) {
    if (this in collection)
        asserter.fail("Value should not be one of collection, was ${out(this)}")
}

/**
 * Test that a value is the same instance as the expected.
 */
@InlineOnly infix fun <@OnlyInputTypes T> T.shouldBeSameInstance(expected: T) {
    if (this !== expected)
        asserter.fail("Value should be same instance as ${out(expected)}, was ${out(this)}")
}

/**
 * Test that a value is not the same instance as the expected.
 */
@InlineOnly infix fun <@OnlyInputTypes T> T.shouldNotBeSameInstance(expected: T) {
    if (this === expected)
        asserter.fail("Value should not be same instance as ${out(expected)}")
}

/**
 * Test that a [Throwable] of the specified type is thrown in a given block of code.
 */
@OptIn(ExperimentalContracts::class)
@InlineOnly inline fun <reified T : Throwable> shouldThrow(block: () -> Unit): T {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    try {
        block()
    }
    catch (t: Throwable) {
        if (t is T)
            return t
        asserter.fail("Should throw ${T::class.simplifyName()} but threw ${t::class.simplifyName()}")
    }
    asserter.fail("Should throw ${T::class.simplifyName()} but completed without exception")
}

/**
 * Test that a [Throwable] of the specified type and message is thrown in a given block of code.
 */
@OptIn(ExperimentalContracts::class)
@InlineOnly inline fun <reified T : Throwable> shouldThrow(message: String, block: () -> Unit): T {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    try {
        block()
    }
    catch (t: Throwable) {
        if (t is T) {
            if (t.message != message)
                asserter.fail("Should throw with message ${str(message)}, was ${out(t.message)}")
            return t
        }
        asserter.fail("Should throw ${T::class.simplifyName()} but threw ${t::class.simplifyName()}")
    }
    asserter.fail("Should throw ${T::class.simplifyName()} but completed without exception")
}

/**
 * Test that a value is non-null, and return the value for further testing.
 */
@OptIn(ExperimentalContracts::class)
@InlineOnly fun <T : Any> T?.shouldBeNonNull(): T {
    contract { returns() implies (this@shouldBeNonNull != null) }
    return this ?: asserter.fail("Value should not be null")
}

/**
 * Test that a value is of a specified type, and return the value for further testing.
 */
@OptIn(ExperimentalContracts::class)
@InlineOnly inline fun <reified T> Any?.shouldBeType(): T {
    contract { returns() implies (this@shouldBeType is T) }
    return if (this is T) this else
        asserter.fail(
            "Value should be ${T::class.simplifyName()}, was " +
                    if (this == null) "null" else this::class.simplifyName()
        )
}

// shared functions

@PublishedApi internal fun out(value: Any?): String = when (value) {
    null -> "null"
    is CharSequence -> str(value)
    else -> value.toString()
}

@PublishedApi internal fun str(value: CharSequence, maxLength: Int = 39): String = buildString {
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

internal fun Appendable.appendSanitized(chars: CharSequence, start: Int, end: Int) {
    for (i in start until end) {
        when (val ch = chars[i]) {
            // there are library functions for this, but those functions may need to use this test library
            // repeating the functionality here avoids a circular dependency
            in ' '..'~' -> append(ch)
            '"' -> append("\\\"")
            '\\' -> append("\\\\")
            '\n' -> append("\\n")
            '\r' -> append("\\r")
            '\t' -> append("\\t")
            '\b' -> append("\\b")
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
