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
import kotlin.test.asserter

import io.kstuff.test.ErrorMessages.errorShouldBe
import io.kstuff.test.ErrorMessages.errorShouldBeOneOf
import io.kstuff.test.ErrorMessages.errorShouldBePredicate
import io.kstuff.test.ErrorMessages.errorShouldBeSameInstance
import io.kstuff.test.ErrorMessages.errorShouldBeType
import io.kstuff.test.ErrorMessages.errorShouldNotBe
import io.kstuff.test.ErrorMessages.errorShouldNotBeOneOf
import io.kstuff.test.ErrorMessages.errorShouldNotBePredicate
import io.kstuff.test.ErrorMessages.errorShouldNotBeSameInstance
import io.kstuff.test.ErrorMessages.errorShouldThrowButCompleted
import io.kstuff.test.ErrorMessages.errorShouldThrowButThrew
import io.kstuff.test.ErrorMessages.errorShouldThrowWithMessage

/**
 * Test that value is equal to expected.
 */
@InlineOnly infix fun <@OnlyInputTypes T> T.shouldBe(expected: T) {
    if (this != expected)
        asserter.fail(errorShouldBe(expected, this))
}

/**
 * Test that predicate applied to the value returns `true`.
 */
@OptIn(ExperimentalContracts::class)
@InlineOnly infix fun <@OnlyInputTypes T> T.shouldBe(predicate: (T) -> Boolean) {
    contract { callsInPlace(predicate, InvocationKind.EXACTLY_ONCE) }
    if (!predicate(this))
        asserter.fail(errorShouldBePredicate(this, predicate))
}

/**
 * Test that value is not equal to expected.
 */
@InlineOnly infix fun <@OnlyInputTypes T> T.shouldNotBe(expected: T) {
    if (this == expected)
        asserter.fail(errorShouldNotBe(this))
}

/**
 * Test that predicate applied to the value returns `false`.
 */
@OptIn(ExperimentalContracts::class)
@InlineOnly infix fun <@OnlyInputTypes T> T.shouldNotBe(predicate: (T) -> Boolean) {
    contract { callsInPlace(predicate, InvocationKind.EXACTLY_ONCE) }
    if (predicate(this))
        asserter.fail(errorShouldNotBePredicate(this, predicate))
}

/**
 * Test that value is one of a collection.
 */
@InlineOnly infix fun <@OnlyInputTypes T> T.shouldBeOneOf(collection: Collection<T>) {
    if (this !in collection)
        asserter.fail(errorShouldBeOneOf(collection, this))
}

/**
 * Test that value is not one of a collection.
 */
@InlineOnly infix fun <@OnlyInputTypes T> T.shouldNotBeOneOf(collection: Collection<T>) {
    if (this in collection)
        asserter.fail(errorShouldNotBeOneOf(collection, this))
}

/**
 * Test that a value is the same instance as the expected.
 */
@InlineOnly infix fun <@OnlyInputTypes T> T.shouldBeSameInstance(expected: T) {
    if (this !== expected)
        asserter.fail(errorShouldBeSameInstance(expected, this))
}

/**
 * Test that a value is not the same instance as the expected.
 */
@InlineOnly infix fun <@OnlyInputTypes T> T.shouldNotBeSameInstance(expected: T) {
    if (this === expected)
        asserter.fail(errorShouldNotBeSameInstance(expected))
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
        asserter.fail(errorShouldThrowButThrew(T::class, t::class))
    }
    asserter.fail(errorShouldThrowButCompleted(T::class))
}

/**
 * Test that a [Throwable] of the specified type and with the specified message is thrown in a given block of code.
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
                asserter.fail(errorShouldThrowWithMessage(message, t.message))
            return t
        }
        asserter.fail(errorShouldThrowButThrew(T::class, t::class))
    }
    asserter.fail(errorShouldThrowButCompleted(T::class))
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
    return if (this is T) this else asserter.fail(errorShouldBeType(T::class, this))
}

/**
 * Test that value is equal to expected, allowing use of type inference.
 */
@InlineOnly fun <@OnlyInputTypes T> shouldBeEqual(expected: T, actual: T) {
    if (actual != expected)
        asserter.fail(errorShouldBe(expected, actual))
}

/**
 * Test that value is not equal to expected, allowing use of type inference.
 */
@InlineOnly fun <@OnlyInputTypes T> shouldNotBeEqual(expected: T, actual: T) {
    if (actual == expected)
        asserter.fail(errorShouldNotBe(expected))
}
