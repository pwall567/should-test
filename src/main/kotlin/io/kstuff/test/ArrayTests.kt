/*
 * @(#) ArrayTests.kt
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

import kotlin.internal.InlineOnly
import kotlin.internal.OnlyInputTypes
import kotlin.test.asserter

import io.kstuff.test.ErrorMessages.appendValue
import io.kstuff.test.ErrorMessages.errorShouldBe
import io.kstuff.test.ErrorMessages.errorShouldNotBe

/**
 * Test that [Array] is equal to expected.
 */
@InlineOnly infix fun <@OnlyInputTypes T> Array<T>.shouldBe(expected: Array<T>) {
    if (!this.contentEquals(expected))
        asserter.fail(errorShouldBe(expected, this))
}

/**
 * Test that [Array] is not equal to expected.
 */
@InlineOnly infix fun <@OnlyInputTypes T> Array<T>.shouldNotBe(expected: Array<T>) {
    if (this.contentEquals(expected))
        asserter.fail(errorShouldNotBe(this))
}

/**
 * Test that an [Array] contains the given element.
 */
@InlineOnly infix fun <@OnlyInputTypes T> Array<T>.shouldContain(element: T) {
    if (element !in this)
        asserter.fail(errorArrayShouldContain(this, element))
}

/**
 * Test that an [Array] does not contain the given element.
 */
@InlineOnly infix fun <@OnlyInputTypes T> Array<T>.shouldNotContain(element: T) {
    if (element in this)
        asserter.fail(errorArrayShouldNotContain(this, element))
}

/**
 * Test that [IntArray] is equal to expected.
 */
@InlineOnly infix fun IntArray.shouldBe(expected: IntArray) {
    if (!this.contentEquals(expected))
        asserter.fail(errorShouldBe(expected, this))
}

/**
 * Test that [IntArray] is not equal to expected.
 */
@InlineOnly infix fun IntArray.shouldNotBe(expected: IntArray) {
    if (this.contentEquals(expected))
        asserter.fail(errorShouldNotBe(this))
}

/**
 * Test that an [IntArray] contains the given element.
 */
@InlineOnly infix fun IntArray.shouldContain(element: Int) {
    if (element !in this)
        asserter.fail(errorArrayShouldContain(this, element, "Int"))
}

/**
 * Test that an [IntArray] does not contain the given element.
 */
@InlineOnly infix fun IntArray.shouldNotContain(element: Int) {
    if (element in this)
        asserter.fail(errorArrayShouldNotContain(this, element, "Int"))
}

/**
 * Test that [LongArray] is equal to expected.
 */
@InlineOnly infix fun LongArray.shouldBe(expected: LongArray) {
    if (!this.contentEquals(expected))
        asserter.fail(errorShouldBe(expected, this))
}

/**
 * Test that [LongArray] is not equal to expected.
 */
@InlineOnly infix fun LongArray.shouldNotBe(expected: LongArray) {
    if (this.contentEquals(expected))
        asserter.fail(errorShouldNotBe(this))
}

/**
 * Test that an [LongArray] contains the given element.
 */
@InlineOnly infix fun LongArray.shouldContain(element: Long) {
    if (element !in this)
        asserter.fail(errorArrayShouldContain(this, element, "Long"))
}

/**
 * Test that an [LongArray] does not contain the given element.
 */
@InlineOnly infix fun LongArray.shouldNotContain(element: Long) {
    if (element in this)
        asserter.fail(errorArrayShouldNotContain(this, element, "Long"))
}

/**
 * Test that [ShortArray] is equal to expected.
 */
@InlineOnly infix fun ShortArray.shouldBe(expected: ShortArray) {
    if (!this.contentEquals(expected))
        asserter.fail(errorShouldBe(expected, this))
}

/**
 * Test that [ShortArray] is not equal to expected.
 */
@InlineOnly infix fun ShortArray.shouldNotBe(expected: ShortArray) {
    if (this.contentEquals(expected))
        asserter.fail(errorShouldNotBe(this))
}

/**
 * Test that an [ShortArray] contains the given element.
 */
@InlineOnly infix fun ShortArray.shouldContain(element: Short) {
    if (element !in this)
        asserter.fail(errorArrayShouldContain(this, element, "Short"))
}

/**
 * Test that an [ShortArray] does not contain the given element.
 */
@InlineOnly infix fun ShortArray.shouldNotContain(element: Short) {
    if (element in this)
        asserter.fail(errorArrayShouldNotContain(this, element, "Short"))
}

/**
 * Test that [ByteArray] is equal to expected.
 */
@InlineOnly infix fun ByteArray.shouldBe(expected: ByteArray) {
    if (!this.contentEquals(expected))
        asserter.fail(errorShouldBe(expected, this))
}

/**
 * Test that [ByteArray] is not equal to expected.
 */
@InlineOnly infix fun ByteArray.shouldNotBe(expected: ByteArray) {
    if (this.contentEquals(expected))
        asserter.fail(errorShouldNotBe(this))
}

/**
 * Test that an [ByteArray] contains the given element.
 */
@InlineOnly infix fun ByteArray.shouldContain(element: Byte) {
    if (element !in this)
        asserter.fail(errorArrayShouldContain(this, element, "Byte"))
}

/**
 * Test that an [ByteArray] does not contain the given element.
 */
@InlineOnly infix fun ByteArray.shouldNotContain(element: Byte) {
    if (element in this)
        asserter.fail(errorArrayShouldNotContain(this, element, "Byte"))
}

/**
 * Test that [BooleanArray] is equal to expected.
 */
@InlineOnly infix fun BooleanArray.shouldBe(expected: BooleanArray) {
    if (!this.contentEquals(expected))
        asserter.fail(errorShouldBe(expected, this))
}

/**
 * Test that [BooleanArray] is not equal to expected.
 */
@InlineOnly infix fun BooleanArray.shouldNotBe(expected: BooleanArray) {
    if (this.contentEquals(expected))
        asserter.fail(errorShouldNotBe(this))
}

/**
 * Test that an [BooleanArray] contains the given element.
 */
@InlineOnly infix fun BooleanArray.shouldContain(element: Boolean) {
    if (element !in this)
        asserter.fail(errorArrayShouldContain(this, element, "Boolean"))
}

/**
 * Test that an [BooleanArray] does not contain the given element.
 */
@InlineOnly infix fun BooleanArray.shouldNotContain(element: Boolean) {
    if (element in this)
        asserter.fail(errorArrayShouldNotContain(this, element, "Boolean"))
}

/**
 * Test that [DoubleArray] is equal to expected.
 */
@InlineOnly infix fun DoubleArray.shouldBe(expected: DoubleArray) {
    if (!this.contentEquals(expected))
        asserter.fail(errorShouldBe(expected, this))
}

/**
 * Test that [DoubleArray] is not equal to expected.
 */
@InlineOnly infix fun DoubleArray.shouldNotBe(expected: DoubleArray) {
    if (this.contentEquals(expected))
        asserter.fail(errorShouldNotBe(this))
}

/**
 * Test that an [DoubleArray] contains the given element.
 */
@InlineOnly infix fun DoubleArray.shouldContain(element: Double) {
    if (indexOfFirst { it == element } < 0)
        asserter.fail(errorArrayShouldContain(this, element, "Double"))
}

/**
 * Test that an [DoubleArray] does not contain the given element.
 */
@InlineOnly infix fun DoubleArray.shouldNotContain(element: Double) {
    if (indexOfFirst { it == element } >= 0)
        asserter.fail(errorArrayShouldNotContain(this, element, "Double"))
}

/**
 * Test that [FloatArray] is equal to expected.
 */
@InlineOnly infix fun FloatArray.shouldBe(expected: FloatArray) {
    if (!this.contentEquals(expected))
        asserter.fail(errorShouldBe(expected, this))
}

/**
 * Test that [FloatArray] is not equal to expected.
 */
@InlineOnly infix fun FloatArray.shouldNotBe(expected: FloatArray) {
    if (this.contentEquals(expected))
        asserter.fail(errorShouldNotBe(this))
}

/**
 * Test that an [FloatArray] contains the given element.
 */
@InlineOnly infix fun FloatArray.shouldContain(element: Float) {
    if (indexOfFirst { it == element } < 0)
        asserter.fail(errorArrayShouldContain(this, element, "Float"))
}

/**
 * Test that an [FloatArray] does not contain the given element.
 */
@InlineOnly infix fun FloatArray.shouldNotContain(element: Float) {
    if (indexOfFirst { it == element } >= 0)
        asserter.fail(errorArrayShouldNotContain(this, element, "Float"))
}

/**
 * Test that [CharArray] is equal to expected.
 */
@InlineOnly infix fun CharArray.shouldBe(expected: CharArray) {
    if (!this.contentEquals(expected))
        asserter.fail(errorShouldBe(expected, this))
}

/**
 * Test that [CharArray] is not equal to expected.
 */
@InlineOnly infix fun CharArray.shouldNotBe(expected: CharArray) {
    if (this.contentEquals(expected))
        asserter.fail(errorShouldNotBe(this))
}

/**
 * Test that an [CharArray] contains the given element.
 */
@InlineOnly infix fun CharArray.shouldContain(element: Char) {
    if (element !in this)
        asserter.fail(errorArrayShouldContain(this, element, "Char"))
}

/**
 * Test that an [CharArray] does not contain the given element.
 */
@InlineOnly infix fun CharArray.shouldNotContain(element: Char) {
    if (element in this)
        asserter.fail(errorArrayShouldNotContain(this, element, "Char"))
}

/**
 * Test that [Array] is equal to expected, allowing use of type inference.
 */
@InlineOnly fun <@OnlyInputTypes T> shouldBeEqual(expected: Array<T>, actual: Array<T>) {
    if (!actual.contentEquals(expected))
        asserter.fail(errorShouldBe(expected, actual))
}

/**
 * Test that [Array] is not equal to expected, allowing use of type inference.
 */
@InlineOnly fun <@OnlyInputTypes T> shouldNotBeEqual(expected: Array<T>, actual: Array<T>) {
    if (actual.contentEquals(expected))
        asserter.fail(errorShouldNotBe(actual))
}

/**
 * Test that [IntArray] is equal to expected, allowing use of type inference.
 */
@InlineOnly fun shouldBeEqual(expected: IntArray, actual: IntArray) {
    if (!actual.contentEquals(expected))
        asserter.fail(errorShouldBe(expected, actual))
}

/**
 * Test that [IntArray] is not equal to expected, allowing use of type inference.
 */
@InlineOnly fun shouldNotBeEqual(expected: IntArray, actual: IntArray) {
    if (actual.contentEquals(expected))
        asserter.fail(errorShouldNotBe(actual))
}

/**
 * Test that [LongArray] is equal to expected, allowing use of type inference.
 */
@InlineOnly fun shouldBeEqual(expected: LongArray, actual: LongArray) {
    if (!actual.contentEquals(expected))
        asserter.fail(errorShouldBe(expected, actual))
}

/**
 * Test that [LongArray] is not equal to expected, allowing use of type inference.
 */
@InlineOnly fun shouldNotBeEqual(expected: LongArray, actual: LongArray) {
    if (actual.contentEquals(expected))
        asserter.fail(errorShouldNotBe(actual))
}

/**
 * Test that [ShortArray] is equal to expected, allowing use of type inference.
 */
@InlineOnly fun shouldBeEqual(expected: ShortArray, actual: ShortArray) {
    if (!actual.contentEquals(expected))
        asserter.fail(errorShouldBe(expected, actual))
}

/**
 * Test that [ShortArray] is not equal to expected, allowing use of type inference.
 */
@InlineOnly fun shouldNotBeEqual(expected: ShortArray, actual: ShortArray) {
    if (actual.contentEquals(expected))
        asserter.fail(errorShouldNotBe(actual))
}

/**
 * Test that [ByteArray] is equal to expected, allowing use of type inference.
 */
@InlineOnly fun shouldBeEqual(expected: ByteArray, actual: ByteArray) {
    if (!actual.contentEquals(expected))
        asserter.fail(errorShouldBe(expected, actual))
}

/**
 * Test that [ByteArray] is not equal to expected, allowing use of type inference.
 */
@InlineOnly fun shouldNotBeEqual(expected: ByteArray, actual: ByteArray) {
    if (actual.contentEquals(expected))
        asserter.fail(errorShouldNotBe(actual))
}

/**
 * Test that [BooleanArray] is equal to expected, allowing use of type inference.
 */
@InlineOnly fun shouldBeEqual(expected: BooleanArray, actual: BooleanArray) {
    if (!actual.contentEquals(expected))
        asserter.fail(errorShouldBe(expected, actual))
}

/**
 * Test that [BooleanArray] is not equal to expected, allowing use of type inference.
 */
@InlineOnly fun shouldNotBeEqual(expected: BooleanArray, actual: BooleanArray) {
    if (actual.contentEquals(expected))
        asserter.fail(errorShouldNotBe(actual))
}

/**
 * Test that [DoubleArray] is equal to expected, allowing use of type inference.
 */
@InlineOnly fun shouldBeEqual(expected: DoubleArray, actual: DoubleArray) {
    if (!actual.contentEquals(expected))
        asserter.fail(errorShouldBe(expected, actual))
}

/**
 * Test that [DoubleArray] is not equal to expected, allowing use of type inference.
 */
@InlineOnly fun shouldNotBeEqual(expected: DoubleArray, actual: DoubleArray) {
    if (actual.contentEquals(expected))
        asserter.fail(errorShouldNotBe(actual))
}

/**
 * Test that [FloatArray] is equal to expected, allowing use of type inference.
 */
@InlineOnly fun shouldBeEqual(expected: FloatArray, actual: FloatArray) {
    if (!actual.contentEquals(expected))
        asserter.fail(errorShouldBe(expected, actual))
}

/**
 * Test that [FloatArray] is not equal to expected, allowing use of type inference.
 */
@InlineOnly fun shouldNotBeEqual(expected: FloatArray, actual: FloatArray) {
    if (actual.contentEquals(expected))
        asserter.fail(errorShouldNotBe(actual))
}

/**
 * Test that [CharArray] is equal to expected, allowing use of type inference.
 */
@InlineOnly fun shouldBeEqual(expected: CharArray, actual: CharArray) {
    if (!actual.contentEquals(expected))
        asserter.fail(errorShouldBe(expected, actual))
}

/**
 * Test that [CharArray] is not equal to expected, allowing use of type inference.
 */
@InlineOnly fun shouldNotBeEqual(expected: CharArray, actual: CharArray) {
    if (actual.contentEquals(expected))
        asserter.fail(errorShouldNotBe(actual))
}

internal fun <T> errorArrayShouldContain(array: Any, element: T, type: String? = null) = buildString {
    if (type != null)
        append(type)
    append("Array ")
    appendValue(array)
    append(" should contain ")
    appendValue(element)
}

internal fun <T> errorArrayShouldNotContain(array: Any, element: T, type: String? = null) = buildString {
    if (type != null)
        append(type)
    append("Array ")
    appendValue(array)
    append(" should not contain ")
    appendValue(element)
}
