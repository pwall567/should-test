/*
 * @(#) StringTestsTest.kt
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
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse

class StringTestsTest {

    @Test fun `should handle errors involving long strings differently`() {
        assertFailsWith<AssertionError> { longString shouldBe anotherLongString }.let {
            val message = it.message
            assertFalse(message != null && message.startsWith("Value should be "))
        }
    }

    @Test fun `should perform shouldContain on string`() {
        stringValue shouldContain "st"
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldContain on string fails`() {
        assertFailsWith<AssertionError> { stringValue shouldContain "abc" }.let {
            assertEquals("String should contain \"abc\", was \"Test string\"", it.message)
        }
    }

    @Test fun `should perform shouldNotContain on string`() {
        stringValue shouldNotContain "stop"
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldNotContain on string fails`() {
        assertFailsWith<AssertionError> { stringValue shouldNotContain "st" }.let {
            assertEquals("String should not contain \"st\", was \"Test string\"", it.message)
        }
    }

    @Test fun `should perform shouldStartWith`() {
        stringValue shouldStartWith "Test"
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldStartWith fails`() {
        assertFailsWith<AssertionError> { stringValue shouldStartWith "X" }.let {
            assertEquals("String should start with \"X\", was \"Test string\"", it.message)
        }
    }

    @Test fun `should perform shouldNotStartWith`() {
        stringValue shouldNotStartWith "X"
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldNotStartWith fails`() {
        assertFailsWith<AssertionError> { stringValue shouldNotStartWith "T" }.let {
            assertEquals("String should not start with \"T\", was \"Test string\"", it.message)
        }
    }

    @Test fun `should perform shouldEndWith`() {
        stringValue shouldEndWith "string"
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldEndWith fails`() {
        assertFailsWith<AssertionError> { stringValue shouldEndWith "X" }.let {
            assertEquals("String should end with \"X\", was \"Test string\"", it.message)
        }
    }

    @Test fun `should perform shouldNotEndWith`() {
        stringValue shouldNotEndWith "X"
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldNotEndWith fails`() {
        assertFailsWith<AssertionError> { stringValue shouldNotEndWith "string" }.let {
            assertEquals("String should not end with \"string\", was \"Test string\"", it.message)
        }
    }

    @Test fun `should perform shouldMatch`() {
        stringValue shouldMatch Regex("^T")
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldMatch fails`() {
        assertFailsWith<AssertionError> { stringValue shouldMatch Regex("^X") }.let {
            assertEquals("String should match regex ^X, was \"Test string\"", it.message)
        }
    }

    @Test fun `should perform shouldNotMatch`() {
        stringValue shouldNotMatch Regex("^X")
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldNotMatch fails`() {
        assertFailsWith<AssertionError> { stringValue shouldNotMatch Regex("^T") }.let {
            assertEquals("String should not match regex ^T, was \"Test string\"", it.message)
        }
    }

    @Suppress("ConstPropertyName")
    companion object {

        const val stringValue = "Test string"

        const val longString =
"""Our revels now are ended. These our actors,
As I foretold you, were all spirits and
Are melted into air, into thin air:
"""
        const val anotherLongString =
"""We are such stuff
As dreams are made on, and our little life
Is rounded with a sleep.
"""

    }

}
