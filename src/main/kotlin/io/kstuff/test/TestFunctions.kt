/*
 * @(#) TestFunctions.kt
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
 * Test that [String] is equal to expected.
 */
infix fun String?.shouldBe(expected: String?) {
    if (this != expected) {
        if (isComplex() || expected.isComplex())
            asserter.assertEquals(null, expected, this) // this function causes IDE to suggest comparison window
        else
            asserter.fail("Value should be ${out(expected)}, was ${out(this)}")
    }
}

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
 * Test that an [Iterable] (_e.g._ [List], [Set]) contains the given element.
 */
infix fun <@OnlyInputTypes T> Iterable<T>.shouldContain(element: T) {
    if (element !in this)
        asserter.fail("Collection should contain ${out(element)}")
}

/**
 * Test that an [Iterable] (_e.g._ [List], [Set]) does not contain the given element.
 */
infix fun <@OnlyInputTypes T> Iterable<T>.shouldNotContain(element: T) {
    if (element in this)
        asserter.fail("Collection should not contain ${out(element)}")
}

/**
 * Test that a [Map] contains the given key.
 */
infix fun <@OnlyInputTypes K, V> Map<K, V>.shouldContainKey(key: K) {
    if (!containsKey(key))
        asserter.fail("Map should contain key ${out(key)}")
}

/**
 * Test that a [Map] does not contain the given key.
 */
infix fun <@OnlyInputTypes K, V> Map<K, V>.shouldNotContainKey(key: K) {
    if (containsKey(key))
        asserter.fail("Map should not contain key ${out(key)}")
}

/**
 * Test that a [String] contains a given substring.
 */
infix fun String.shouldContain(subString: String) {
    if (!contains(subString))
        asserter.fail("String should contain ${str(subString)}, was ${str(this)}")
}

/**
 * Test that a [String] does not contain a given substring.
 */
infix fun String.shouldNotContain(subString: String) {
    if (contains(subString))
        asserter.fail("String should not contain ${str(subString)}, was ${str(this)}")
}

/**
 * Test that a [String] starts with a given prefix.
 */
infix fun String.shouldStartWith(prefix: String) {
    if (!startsWith(prefix))
        asserter.fail("String should start with ${str(prefix)}, was ${str(this)}")
}

/**
 * Test that a [String] does not start with a given prefix.
 */
infix fun String.shouldNotStartWith(prefix: String) {
    if (startsWith(prefix))
        asserter.fail("String should not start with ${str(prefix)}, was ${str(this)}")
}

/**
 * Test that a [String] ends with a given suffix.
 */
infix fun String.shouldEndWith(suffix: String) {
    if (!endsWith(suffix))
        asserter.fail("String should end with ${str(suffix)}, was ${str(this)}")
}

/**
 * Test that a [String] does not end with a given suffix.
 */
infix fun String.shouldNotEndWith(suffix: String) {
    if (endsWith(suffix))
        asserter.fail("String should not end with ${str(suffix)}, was ${str(this)}")
}

/**
 * Test that a [String] matches a given [Regex].
 */
infix fun String.shouldMatch(regex: Regex) {
    if (!regex.containsMatchIn(this))
        asserter.fail("String should match regex $regex, was ${str(this)}")
}

/**
 * Test that a [String] does not match a given [Regex].
 */
infix fun String.shouldNotMatch(regex: Regex) {
    if (regex.containsMatchIn(this))
        asserter.fail("String should not match regex $regex, was ${str(this)}")
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
 * Test that a value is non-null, and return the value for further testing.
 */
fun <T : Any> T?.shouldBeNonNull(): T = this ?: asserter.fail("Value should not be null")

/**
 * Test that a value is of a specified type, and return the value for further testing.
 */
inline fun <reified T> Any?.shouldBeType(): T = if (this is T) this else
    asserter.fail("Value should be ${T::class.simplifyName()}, was " +
            if (this == null) "null" else this::class.simplifyName())

internal fun out(value: Any?): String = when (value) {
    null -> "null"
    is CharSequence -> str(value)
    else -> value.toString()
}

internal fun str(value: CharSequence): String = buildString {
    append('"')
    append(value)
    append('"')
}

internal fun String?.isComplex(): Boolean = this != null && (length > 20  || any { it !in ' '..'~' })

fun KClass<*>.simplifyName(): String {
    val name = qualifiedName
    return when {
        name == null -> "<unknown type>"
        name.startsWith("kotlin.") && name.indexOf('.', 7) < 0 -> name.substring(7)
        else -> name
    }
}
