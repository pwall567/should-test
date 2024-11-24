/*
 * @(#) TestFunctionsTest.kt
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

class TestFunctionsTest {

    @Test fun `should perform shouldBe`() {
        value1 shouldBe 12345
        value2 shouldBe "Test string"
        value3 shouldBe true
        value4 shouldBe false
        value5 shouldBe null
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldBe fails`() {
        assertFailsWith<AssertionError> { value1 shouldBe 12346 }.let {
            assertEquals("Value should be 12346, was 12345", it.message)
        }
        assertFailsWith<AssertionError> { value2 shouldBe "Something else" }.let {
            assertEquals("Value should be \"Something else\", was \"Test string\"", it.message)
        }
        assertFailsWith<AssertionError> { value3 shouldBe false }.let {
            assertEquals("Value should be false, was true", it.message)
        }
        assertFailsWith<AssertionError> { value4 shouldBe true }.let {
            assertEquals("Value should be true, was false", it.message)
        }
        assertFailsWith<AssertionError> { value1 shouldBe null }.let {
            assertEquals("Value should be null, was 12345", it.message)
        }
    }

    @Test fun `should handle errors involving long strings differently`() {
        assertFailsWith<AssertionError> { longString shouldBe anotherLongString }.let {
            val message = it.message
            assertFalse(message != null && message.startsWith("Value should be "))
        }
    }

    @Test fun `should perform shouldBe with lambda`() {
        value2 shouldBe { isNotEmpty() }
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldBe with lambda fails`() {
        assertFailsWith<AssertionError> { value2 shouldBe { isEmpty() } }.let {
            assertEquals("Value should be valid for test, was \"Test string\"", it.message)
        }
    }

    @Test fun `should perform shouldNotBe`() {
        value1 shouldNotBe 12346
        value2 shouldNotBe "Wrong"
        value1 shouldNotBe null
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldNotBe fails`() {
        assertFailsWith<AssertionError> { value1 shouldNotBe 12345 }.let {
            assertEquals("Value should not be 12345", it.message)
        }
        assertFailsWith<AssertionError> { value2 shouldNotBe "Test string" }.let {
            assertEquals("Value should not be \"Test string\"", it.message)
        }
        assertFailsWith<AssertionError> { value5 shouldNotBe null }.let {
            assertEquals("Value should not be null", it.message)
        }
    }

    @Test fun `should perform shouldNotBe with lambda`() {
        value2 shouldNotBe { isEmpty() }
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldNotBe with lambda fails`() {
        assertFailsWith<AssertionError> { value2 shouldNotBe { isNotEmpty() } }.let {
            assertEquals("Value should not be valid for test, was \"Test string\"", it.message)
        }
    }

    @Test fun `should perform shouldContain`() {
        list1 shouldContain "alpha"
        list1 shouldContain "gamma"
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldContain fails`() {
        assertFailsWith<AssertionError> { list1 shouldContain "delta" }.let {
            assertEquals("Collection should contain \"delta\"", it.message)
        }
    }

    @Test fun `should perform shouldNotContain`() {
        list1 shouldNotContain "delta"
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldNotContain fails`() {
        assertFailsWith<AssertionError> { list1 shouldNotContain "alpha" }.let {
            assertEquals("Collection should not contain \"alpha\"", it.message)
        }
    }

    @Test fun `should perform shouldContainKey`() {
        map1 shouldContainKey "first"
        map1 shouldContainKey "second"
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldContainKey fails`() {
        assertFailsWith<AssertionError> { map1 shouldContainKey "alpha" }.let {
            assertEquals("Map should contain key \"alpha\"", it.message)
        }
    }

    @Test fun `should perform shouldNotContainKey`() {
        map1 shouldNotContainKey "alpha"
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldNotContainKey fails`() {
        assertFailsWith<AssertionError> { map1 shouldNotContainKey "first" }.let {
            assertEquals("Map should not contain key \"first\"", it.message)
        }
    }

    @Test fun `should perform shouldContain on string`() {
        value2 shouldContain "st"
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldContain on string fails`() {
        assertFailsWith<AssertionError> { value2 shouldContain "abc" }.let {
            assertEquals("String should contain \"abc\", was \"Test string\"", it.message)
        }
    }

    @Test fun `should perform shouldNotContain on string`() {
        value2 shouldNotContain "stop"
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldNotContain on string fails`() {
        assertFailsWith<AssertionError> { value2 shouldNotContain "st" }.let {
            assertEquals("String should not contain \"st\", was \"Test string\"", it.message)
        }
    }

    @Test fun `should perform shouldStartWith`() {
        value2 shouldStartWith "Test"
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldStartWith fails`() {
        assertFailsWith<AssertionError> { value2 shouldStartWith "X" }.let {
            assertEquals("String should start with \"X\", was \"Test string\"", it.message)
        }
    }

    @Test fun `should perform shouldNotStartWith`() {
        value2 shouldNotStartWith "X"
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldNotStartWith fails`() {
        assertFailsWith<AssertionError> { value2 shouldNotStartWith "T" }.let {
            assertEquals("String should not start with \"T\", was \"Test string\"", it.message)
        }
    }

    @Test fun `should perform shouldEndWith`() {
        value2 shouldEndWith "string"
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldEndWith fails`() {
        assertFailsWith<AssertionError> { value2 shouldEndWith "X" }.let {
            assertEquals("String should end with \"X\", was \"Test string\"", it.message)
        }
    }

    @Test fun `should perform shouldNotEndWith`() {
        value2 shouldNotEndWith "X"
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldNotEndWith fails`() {
        assertFailsWith<AssertionError> { value2 shouldNotEndWith "string" }.let {
            assertEquals("String should not end with \"string\", was \"Test string\"", it.message)
        }
    }

    @Suppress("ConstPropertyName")
    companion object {

        const val value1 = 12345
        const val value2 = "Test string"
        const val value3 = true
        const val value4 = false
        val value5: String? = null
        val list1 = listOf("alpha", "beta", "gamma")
        val map1 = mapOf("first" to "alpha", "second" to "beta", "third" to "gamma")

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
