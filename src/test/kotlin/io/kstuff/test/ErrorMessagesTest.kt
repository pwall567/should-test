/*
 * @(#) ErrorMessagesTest.kt
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

import kotlin.test.Test
import kotlin.test.assertEquals

import io.kstuff.test.ErrorMessages.appendValue
import io.kstuff.test.ErrorMessages.errorShouldBe
import io.kstuff.test.ErrorMessages.errorShouldBePredicate
import io.kstuff.test.ErrorMessages.errorShouldNotBe
import io.kstuff.test.ErrorMessages.errorShouldNotBePredicate

class ErrorMessagesTest {

    @Test fun `should create should-be error message`() {
        assertEquals("Value should be 123, was 100", errorShouldBe(123, 100))
    }

    @Test fun `should create should-not-be error message`() {
        assertEquals("Value should not be 123", errorShouldNotBe(123))
    }

    @Test fun `should create should-be-predicate error message`() {
        assertEquals("Value should match predicate, was 100", errorShouldBePredicate<Int>(100) { false })
    }

    @Test fun `should create should-not-be-predicate error message`() {
        assertEquals("Value should not match predicate, was 100", errorShouldNotBePredicate<Int>(100) { true })
    }

    @Test fun `should append value for null`() {
        assertEquals("null", buildString { appendValue(null) })
    }

    @Test fun `should append value for Int`() {
        assertEquals("0", buildString { appendValue(0) })
        assertEquals("123", buildString { appendValue(123) })
        assertEquals("-100", buildString { appendValue(-100) })
    }

    @Test fun `should append value for Long`() {
        assertEquals("0L", buildString { appendValue(0L) })
        assertEquals("123456L", buildString { appendValue(123456L) })
        assertEquals("-123456789123456789L", buildString { appendValue(-123456789123456789) })
    }

    @Test fun `should append value for Double`() {
        assertEquals("1.0", buildString { appendValue(1.0) })
        assertEquals("10.5", buildString { appendValue(10.5) })
    }

    @Test fun `should append value for Float`() {
        assertEquals("5.0F", buildString { appendValue(5.0F) })
        assertEquals("1.5F", buildString { appendValue(1.5F) })
    }

    @Test fun `should append value for Char`() {
        assertEquals("'A'", buildString { appendValue('A') })
        assertEquals("'\"'", buildString { appendValue('"') })
        assertEquals("'\\''", buildString { appendValue('\'') })
        assertEquals("'\\\\'", buildString { appendValue('\\') })
        assertEquals("'\\n'", buildString { appendValue('\n') })
        assertEquals("'\\u0000'", buildString { appendValue('\u0000') })
    }

    @Test fun `should append value for String`() {
        assertEquals("\"\"", buildString { appendValue("") })
        assertEquals("\"alpha\"", buildString { appendValue("alpha") })
        assertEquals("\"the quick brown f ... over the lazy dog\"",
                buildString { appendValue("the quick brown fox jumps over the lazy dog") })
        assertEquals("\"the q ... y dog\"",
                buildString { appendValue("the quick brown fox jumps over the lazy dog", maxString = 15) })
        assertEquals("\"\\u00a1Hola!\\n\"", buildString { appendValue("\u00A1Hola!\n") })
    }

    @Test fun `should append value for Boolean`() {
        assertEquals("true", buildString { appendValue(true) })
        assertEquals("false", buildString { appendValue(false) })
    }

    @Test fun `should append value for Array`() {
        assertEquals("[ ]", buildString { appendValue(emptyArray<Int>()) })
        assertEquals("[ 1, 2, 3, 4 ]", buildString { appendValue(arrayOf(1, 2, 3, 4)) })
        assertEquals("[ 1, 2, 3, 4 ]", buildString { appendValue(intArrayOf(1, 2, 3, 4)) })
        assertEquals("[ 1, 2, 3, ... ]", buildString { appendValue(arrayOf(1, 2, 3, 4), maxItems = 3) })
    }

    @Test fun `should append value for List`() {
        assertEquals("[ ]", buildString { appendValue(emptyList<Int>()) })
        assertEquals("[ 1, 2, 3, 4 ]", buildString { appendValue(listOf(1, 2, 3, 4)) })
        assertEquals("[ 1, 2, 3, ... ]", buildString { appendValue(listOf(1, 2, 3, 4), maxItems = 3) })
    }

    @Test fun `should append value for Map`() {
        assertEquals("{ }", buildString { appendValue(emptyMap<String, String>()) })
        assertEquals("""{ "alpha": 1, "beta": 2 }""", buildString { appendValue(mapOf("alpha" to 1, "beta" to 2)) })
        assertEquals("""{ "alpha": 1, ... }""",
                buildString { appendValue(mapOf("alpha" to 1, "beta" to 2), maxEntries = 1) })
    }

}
