/*
 * @(#) ComparablePredicateTest.kt
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

class ComparablePredicateTest {

    @Test fun `should use comparable predicate gt`() {
        value1 shouldBe gt(100)
        value1 shouldNotBe gt(123)
    }

    @Test fun `should use comparable predicate ge`() {
        value1 shouldBe ge(100)
        value1 shouldBe ge(123)
    }

    @Test fun `should use comparable predicate le`() {
        value1 shouldBe le(200)
        value1 shouldBe le(123)
    }

    @Test fun `should use comparable predicate lt`() {
        value1 shouldBe lt(200)
        value1 shouldNotBe lt(123)
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should report ComparablePredicate failure correctly`() {
        assertFailsWith<AssertionError> { value1 shouldBe ge(200) }.let {
            assertEquals("Value should be >= 200, was 123", it.message)
        }
    }

    @Suppress("ConstPropertyName")
    companion object {
        const val value1 = 123
    }

}
