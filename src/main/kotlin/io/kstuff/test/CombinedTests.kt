/*
 * @(#) CombinedTests.kt
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

import io.kstuff.test.ErrorMessages.errorShouldBe
import io.kstuff.test.ErrorMessages.errorShouldBeOneOf
import io.kstuff.test.ErrorMessages.errorShouldBePredicate
import io.kstuff.test.ErrorMessages.errorShouldBeSameInstance
import io.kstuff.test.ErrorMessages.errorShouldNotBe
import io.kstuff.test.ErrorMessages.errorShouldNotBeOneOf
import io.kstuff.test.ErrorMessages.errorShouldNotBePredicate
import io.kstuff.test.ErrorMessages.errorShouldNotBeSameInstance

class CombinedTests {

    val errors = mutableListOf<Pair<String, StackTraceElement>>()

    /**
     * Test that value is equal to expected.
     */
    @InlineOnly infix fun <@OnlyInputTypes T> T.shouldBe(expected: T) {
        if (this != expected)
            fail(errorShouldBe(expected, this))
    }

    /**
     * Test that predicate applied to the value returns `true`.
     */
    @OptIn(ExperimentalContracts::class)
    @InlineOnly infix fun <@OnlyInputTypes T> T.shouldBe(predicate: (T) -> Boolean) {
        contract { callsInPlace(predicate, InvocationKind.AT_MOST_ONCE) }
        if (!predicate(this))
            fail(errorShouldBePredicate(this))
    }

    /**
     * Test that [NamedPredicate] applied to the value returns `true`.
     */
    @InlineOnly infix fun <@OnlyInputTypes T> T.shouldBe(predicate: NamedPredicate<T>) {
        if (!predicate(this))
            fail(errorShouldBePredicate(this, predicate))
    }

    /**
     * Test that value is not equal to expected.
     */
    infix fun <@OnlyInputTypes T> T.shouldNotBe(expected: T) {
        if (this == expected)
            fail(errorShouldNotBe(this))
    }

    /**
     * Test that predicate applied to the value returns `false`.
     */
    @OptIn(ExperimentalContracts::class)
    @InlineOnly infix fun <@OnlyInputTypes T> T.shouldNotBe(predicate: (T) -> Boolean) {
        contract { callsInPlace(predicate, InvocationKind.AT_MOST_ONCE) }
        if (predicate(this))
            fail(errorShouldNotBePredicate(this))
    }

    /**
     * Test that predicate applied to the value returns `false`.
     */
    @InlineOnly infix fun <@OnlyInputTypes T> T.shouldNotBe(predicate: NamedPredicate<T>) {
        if (predicate(this))
            fail(errorShouldNotBePredicate(this, predicate))
    }

    /**
     * Test that value is one of a collection.
     */
    @InlineOnly infix fun <@OnlyInputTypes T> T.shouldBeOneOf(collection: Collection<T>) {
        if (this !in collection)
            fail(errorShouldBeOneOf(collection, this))
    }

    /**
     * Test that value is not one of a collection.
     */
    @InlineOnly infix fun <@OnlyInputTypes T> T.shouldNotBeOneOf(collection: Collection<T>) {
        if (this in collection)
            fail(errorShouldNotBeOneOf(collection, this))
    }

    /**
     * Test that a value is the same instance as the expected.
     */
    @InlineOnly infix fun <@OnlyInputTypes T> T.shouldBeSameInstance(expected: T) {
        if (this !== expected)
            fail(errorShouldBeSameInstance(expected, this))
    }

    /**
     * Test that a value is not the same instance as the expected.
     */
    @InlineOnly infix fun <@OnlyInputTypes T> T.shouldNotBeSameInstance(expected: T) {
        if (this === expected)
            fail(errorShouldNotBeSameInstance(expected))
    }

    /**
     * Test that an [Iterable] (_e.g._ [List], [Set]) contains the given element.
     */
    @InlineOnly infix fun <@OnlyInputTypes T> Iterable<T>.shouldContain(element: T) {
        if (element !in this)
            fail(errorCollectionShouldContain(this, element))
    }

    /**
     * Test that an [Iterable] (_e.g._ [List], [Set]) does not contain the given element.
     */
    @InlineOnly infix fun <@OnlyInputTypes T> Iterable<T>.shouldNotContain(element: T) {
        if (element in this)
            fail(errorCollectionShouldNotContain(this, element))
    }

    /**
     * Test that a [Map] contains the given key.
     */
    @InlineOnly infix fun <@OnlyInputTypes K, V> Map<K, V>.shouldContainKey(key: K) {
        if (!containsKey(key))
            fail(errorMapShouldContainKey(this, key))
    }

    /**
     * Test that a [Map] does not contain the given key.
     */
    @InlineOnly infix fun <@OnlyInputTypes K, V> Map<K, V>.shouldNotContainKey(key: K) {
        if (containsKey(key))
            fail(errorMapShouldNotContainKey(this, key))
    }

    /**
     * Nested group of combined tests
     */
    fun shouldCombineTests(block: CombinedTests.() -> Unit) { // just in case someone nests calls
        block()
    }

    private fun fail(message: String) {
        val packageName = this::class.java.`package`.name
        val stackTraceElement = Throwable().stackTrace.firstOrNull {
            val className = it.className
            !(className.startsWith(packageName) && className.lastIndexOf('.') == packageName.length)
        } ?: StackTraceElement("unknown", "unknown", null, -1)
        errors.add(message to stackTraceElement)
    }

}

/**
 * Start a group of combined tests, to be reported together at the conclusion of the block.
 */
fun shouldCombineTests(block: CombinedTests.() -> Unit) {
    CombinedTests().apply {
        block()
        if (errors.isNotEmpty())
            throw CombinedAssertionError(errors)
    }
}
