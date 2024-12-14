/*
 * @(#) GenericTestsTest.kt
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

class GenericTestsTest {

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
        assertFailsWith<AssertionError> { value6 shouldBe null }.let {
            assertEquals("Value should be null, was 12345", it.message)
        }
    }

    @Test fun `should perform shouldBe with lambda`() {
        value2 shouldBe { it.isNotEmpty() }
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldBe with lambda fails`() {
        assertFailsWith<AssertionError> { value2 shouldBe { it.isEmpty() } }.let {
            assertEquals("Value should match predicate, was \"Test string\"", it.message)
        }
    }

    @Test fun `should perform shouldNotBe`() {
        value1 shouldNotBe 12346
        value2 shouldNotBe "Wrong"
        value6 shouldNotBe null
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
        value2 shouldNotBe { it.isEmpty() }
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldNotBe with lambda fails`() {
        assertFailsWith<AssertionError> { value2 shouldNotBe { it.isNotEmpty() } }.let {
            assertEquals("Value should not match predicate, was \"Test string\"", it.message)
        }
    }

    @Test fun `should perform shouldBeOneOf`() {
        value1 shouldBeOneOf listOf(1234, 12345, 123456)
        value2 shouldBeOneOf listOf("Test string", "Dummy string")
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldBeOneOf fails`() {
        assertFailsWith<AssertionError> { value1 shouldBeOneOf listOf(1, 2, 3) }.let {
            assertEquals("Value should be one of [ 1, 2, 3 ], was 12345", it.message)
        }
    }

    @Test fun `should perform shouldNotBeOneOf`() {
        value1 shouldNotBeOneOf listOf(1, 2, 3)
        value2 shouldNotBeOneOf listOf("alpha", "beta", "gamma")
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldNotBeOneOf fails`() {
        assertFailsWith<AssertionError> { value1 shouldNotBeOneOf listOf(12345, 12346) }.let {
            assertEquals("Value should not be one of [ 12345, 12346 ], was 12345", it.message)
        }
    }

    @Test fun `should perform shouldBeSameInstance`() {
        val temp = value2
        temp shouldBeSameInstance value2
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldBeSameInstance fails`() {
        val temp = buildString { append(value2) }
        assertFailsWith<AssertionError> { temp shouldBeSameInstance value2 }.let {
            assertEquals("Value should be same instance as \"Test string\", was \"Test string\"", it.message)
        }
    }

    @Test fun `should perform shouldNotBeSameInstance`() {
        val temp = buildString { append(value2) }
        temp shouldNotBeSameInstance value2
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldNotBeSameInstance fails`() {
        val temp = value2
        assertFailsWith<AssertionError> { temp shouldNotBeSameInstance value2 }.let {
            assertEquals("Value should not be same instance as \"Test string\"", it.message)
        }
    }

    @Test fun `should perform shouldThrow`() {
        shouldThrow<NumberFormatException> { "bad".toInt() }
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldThrow fails`() {
        assertFailsWith<AssertionError> { shouldThrow<NumberFormatException> { "123".toInt() } }.let {
            assertEquals("Should throw NumberFormatException but completed without exception", it.message)
        }
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldThrow finds wrong exception type`() {
        assertFailsWith<AssertionError> { shouldThrow<NumberFormatException> { throw RuntimeException("XXX") } }.let {
            assertEquals("Should throw NumberFormatException but threw RuntimeException", it.message)
        }
    }

    @Test fun `should perform shouldThrow with message`() {
        shouldThrow<RuntimeException>("Something went wrong") { throw RuntimeException("Something went wrong") }
    }

    @Test fun `should throw AssertionError when shouldThrow with message fails`() {
        assertFailsWith<AssertionError> {
            shouldThrow<RuntimeException>("Something went wrong") { throw RuntimeException("Bad message") }
        }.let {
            assertEquals("Should throw with message \"Something went wrong\", was \"Bad message\"", it.message)
        }
    }

    @Test fun `should perform shouldBeNonNull`() {
        value6.shouldBeNonNull() shouldBe 12345
    }

    @Test fun `should smart cast after shouldBeNonNull`() {
        value6.shouldBeNonNull()
        value6 shouldBe 12345
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldBeNonNull fails`() {
        assertFailsWith<AssertionError> { value5.shouldBeNonNull() }.let {
            assertEquals("Value should not be null", it.message)
        }
    }

    @Test fun `should perform shouldBeType`() {
        val temp: Number = value1
        temp.shouldBeType<Int>() shouldBe 12345
    }

    @Test fun `should smart cast after shouldBeType`() {
        val temp: Any = value2
        temp.shouldBeType<String>()
        temp shouldBe "Test string"
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldBeType fails`() {
        val temp: Number = value1
        assertFailsWith<AssertionError> { temp.shouldBeType<Long>() }.let {
            assertEquals("Value should be Long, was Int", it.message)
        }
    }

    @Test fun `should compare using inferred type`() {
        shouldBeEqual(listOf(), emptyList<String>())
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldBeEqual fails`() {
        assertFailsWith<AssertionError> { shouldBeEqual(listOf(), listOf("abc")) }.let {
            assertEquals("Value should be [ ], was [ \"abc\" ]", it.message)
        }
    }

    @Test fun `should compare not equal using inferred type`() {
        shouldNotBeEqual(listOf(), listOf("abc"))
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldNotBeEqual fails`() {
        assertFailsWith<AssertionError> { shouldNotBeEqual(listOf(), emptyList<String>()) }.let {
            assertEquals("Value should not be [ ]", it.message)
        }
    }

    @Suppress("ConstPropertyName")
    companion object {

        const val value1 = 12345
        const val value2 = "Test string"
        const val value3 = true
        const val value4 = false
        val value5: String? = null
        @Suppress("RedundantNullableReturnType")
        val value6: Int? = 12345

    }

}
