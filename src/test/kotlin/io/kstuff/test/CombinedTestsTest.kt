/*
 * @(#) CombinedTestsTest.kt
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
import kotlin.test.assertNotNull

class CombinedTestsTest {

    @Test fun `should combine tests`() {
        shouldCombineTests {
            value1 shouldBe 123
            value2 shouldBe "whatever"
            value1 shouldBe ge(100)
            value1 shouldNotBe 100
            value1 shouldNotBe gt(200)
            value1 shouldBeOneOf setOf(123, 456)
            value1 shouldNotBeOneOf setOf(456, 789)
            value1 shouldBeSameInstance value1
            value1 shouldNotBeSameInstance value3
            list1 shouldContain 123
            list1 shouldNotContain 456
            map1 shouldContainKey "alpha"
            map1 shouldNotContainKey "gamma"
        }
    }

    @Test fun `should report errors from combined tests`() {
        assertFailsWith<CombinedAssertionError> {
            shouldCombineTests {
                value1 shouldBe 111
                value1 shouldBe ge(200)
                value2 shouldNotBe "whatever"
                value1 shouldNotBe gt(100)
                value1 shouldBeOneOf setOf(456, 789)
                value1 shouldNotBeOneOf setOf(123, 456)
                value1 shouldBeSameInstance value3
                value1 shouldNotBeSameInstance value1
                list1 shouldContain 456
                list1 shouldNotContain 123
                map1 shouldContainKey "omega"
                map1 shouldNotContainKey "beta"
            }
        }.let {
            val message = it.message
            assertNotNull(message)
            assertEquals("shouldCombineTests found 12 errors", message.substringBefore('\n'))
            assertEquals("Value should be 111, was 123", it.errors[0].first)
            assertEquals("Value should be >= 200, was 123", it.errors[1].first)
            assertEquals("Value should not be \"whatever\"", it.errors[2].first)
            assertEquals("Value should not be > 100, was 123", it.errors[3].first)
            assertEquals("Value should be one of [ 456, 789 ], was 123", it.errors[4].first)
            assertEquals("Value should not be one of [ 123, 456 ], was 123", it.errors[5].first)
            assertEquals("Value should be same instance as 456, was 123", it.errors[6].first)
            assertEquals("Value should not be same instance as 123", it.errors[7].first)
            assertEquals("Collection [ 123, 234, 345 ] should contain 456", it.errors[8].first)
            assertEquals("Collection [ 123, 234, 345 ] should not contain 123", it.errors[9].first)
            assertEquals("Map { \"alpha\": 1, \"beta\": 2 } should contain key \"omega\"", it.errors[10].first)
            assertEquals("Map { \"alpha\": 1, \"beta\": 2 } should not contain key \"beta\"", it.errors[11].first)
        }
    }

    @Test fun `should handle nested combined tests`() {
        assertFailsWith<CombinedAssertionError> {
            shouldCombineTests {
                value1 shouldBe 111
                value2 shouldNotBe "whatever"
                shouldCombineTests {
                    value1 shouldBe 222
                }
            }
        }.let {
            val message = it.message
            assertNotNull(message)
            assertEquals("shouldCombineTests found 3 errors", message.substringBefore('\n'))
            assertEquals("Value should be 111, was 123", it.errors[0].first)
            assertEquals("Value should not be \"whatever\"", it.errors[1].first)
            assertEquals("Value should be 222, was 123", it.errors[2].first)
        }
    }

    @Suppress("ConstPropertyName")
    companion object {
        const val value1 = 123
        const val value2 = "whatever"
        const val value3 = 456
        val list1 = listOf(123, 234, 345)
        val map1 = mapOf("alpha" to 1, "beta" to 2)
    }

}
