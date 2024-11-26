/*
 * @(#) StringTests.kt
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

internal fun String?.isComplex(): Boolean = this != null && (length > 20  || any { it !in ' '..'~' })
