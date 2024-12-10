/*
 * @(#) CollectionTestsTest.kt
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

import io.kstuff.test.ErrorMessages.appendValue

class CollectionTestsTest {

    @Test fun `should perform shouldContain`() {
        list1 shouldContain "alpha"
        list1 shouldContain "gamma"
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldContain fails`() {
        assertFailsWith<AssertionError> { list1 shouldContain "delta" }.let {
            assertEquals("""Collection [ "alpha", "beta", "gamma" ] should contain "delta"""", it.message)
        }
    }

    @Test fun `should perform shouldNotContain`() {
        list1 shouldNotContain "delta"
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldNotContain fails`() {
        assertFailsWith<AssertionError> { list1 shouldNotContain "alpha" }.let {
            assertEquals("""Collection [ "alpha", "beta", "gamma" ] should not contain "alpha"""", it.message)
        }
    }

    @Test fun `should perform shouldContainKey`() {
        map1 shouldContainKey "first"
        map1 shouldContainKey "second"
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldContainKey fails`() {
        assertFailsWith<AssertionError> { map1 shouldContainKey "alpha" }.let {
            assertEquals("Map ${buildString { appendValue(map1) }} should contain key \"alpha\"", it.message)
        }
    }

    @Test fun `should perform shouldNotContainKey`() {
        map1 shouldNotContainKey "alpha"
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldNotContainKey fails`() {
        assertFailsWith<AssertionError> { map1 shouldNotContainKey "first" }.let {
            assertEquals("Map ${buildString { appendValue(map1) }} should not contain key \"first\"", it.message)
        }
    }

    companion object {

        val list1 = listOf("alpha", "beta", "gamma")
        val map1 = mapOf("first" to "alpha", "second" to "beta", "third" to "gamma")

    }

}
