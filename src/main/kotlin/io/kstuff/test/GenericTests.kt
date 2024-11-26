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

import kotlin.internal.OnlyInputTypes
import kotlin.reflect.KClass
import kotlin.test.assertFailsWith
import kotlin.test.asserter

/**
 * Test that value is equal to expected.
 */
infix fun <@OnlyInputTypes T> T.shouldBe(expected: T) {
    if (this != expected)
        asserter.fail("Value should be ${out(expected)}, was ${out(this)}")
}

/**
 * Test that lambda applied to the value returns `true`.]
 */
infix fun <@OnlyInputTypes T> T.shouldBe(test: T.() -> Boolean) {
    if (!test())
        asserter.fail("Value should be valid for test, was ${out(this)}")
}

/**
 * Test that value is not equal to expected.
 */
infix fun <@OnlyInputTypes T> T.shouldNotBe(expected: T) {
    if (this == expected)
        asserter.fail("Value should not be ${out(expected)}")
}

/**
 * Test that lambda applied to the value returns `false`.]
 */
infix fun <@OnlyInputTypes T> T.shouldNotBe(test: T.() -> Boolean) {
    if (test())
        asserter.fail("Value should not be valid for test, was ${out(this)}")
}

/**
 * Test that value is one of a collection.
 */
infix fun <@OnlyInputTypes T> T.shouldBeOneOf(collection: Collection<T>) {
    if (this !in collection)
        asserter.fail("Value should be one of collection, was ${out(this)}")
}

/**
 * Test that value is not one of a collection.
 */
infix fun <@OnlyInputTypes T> T.shouldNotBeOneOf(collection: Collection<T>) {
    if (this in collection)
        asserter.fail("Value should not be one of collection, was ${out(this)}")
}

/**
 * Test that a value is the same instance as the expected.
 */
infix fun <@OnlyInputTypes T> T.shouldBeSameInstance(expected: T) {
    if (this !== expected)
        asserter.fail("Value should be same instance as ${out(expected)}, was ${out(this)}")
}

/**
 * Test that a value is not the same instance as the expected.
 */
infix fun <@OnlyInputTypes T> T.shouldNotBeSameInstance(expected: T) {
    if (this === expected)
        asserter.fail("Value should not be same instance as ${out(expected)}")
}

/**
 * Test that a [Throwable] of the specified type is thrown in a given block of code.
 */
inline fun <reified T : Throwable> shouldThrow(block: () -> Unit): T = assertFailsWith(T::class, null, block)

/**
 * Test that a [Throwable] of the specified type and message is thrown in a given block of code.
 */
inline fun <reified T : Throwable> shouldThrow(message: String, block: () -> Unit): T =
        assertFailsWith(T::class, null, block).also {
            if (it.message != message)
                asserter.fail("Message incorrect, was ${out(it.message)}")
        }

/**
 * Test that a value is non-null, and return the value for further testing.
 */
fun <T : Any> T?.shouldBeNonNull(): T = this ?: asserter.fail("Value should not be null")

/**
 * Test that a value is of a specified type, and return the value for further testing.
 */
inline fun <reified T> Any?.shouldBeType(): T = if (this is T) this else
    asserter.fail("Value should be ${T::class.simplifyName()}, was " +
            if (this == null) "null" else this::class.simplifyName())

// shared functions

@PublishedApi
internal fun out(value: Any?): String = when (value) {
    null -> "null"
    is CharSequence -> str(value)
    else -> value.toString()
}

@PublishedApi
internal fun str(value: CharSequence): String = buildString {
    append('"')
    append(value)
    append('"')
}

@PublishedApi
internal fun KClass<*>.simplifyName(): String {
    val name = qualifiedName
    return when {
        name == null -> "<unknown type>"
        name.startsWith("kotlin.") && name.indexOf('.', 7) < 0 -> name.substring(7)
        else -> name
    }
}
