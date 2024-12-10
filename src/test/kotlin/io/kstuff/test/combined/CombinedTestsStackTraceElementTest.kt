/*
 * @(#) CombinedTestsStackTraceElementTest.kt
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

package io.kstuff.test.combined

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

import io.kstuff.test.CombinedAssertionError
import io.kstuff.test.shouldCombineTests

class CombinedTestsStackTraceElementTest {

    // this has to be in a different package from the main functionality, because the code that
    // finds the StackTraceElement looks for the first element NOT from the same package

    @Test fun `should fill in StackTraceElement for combined tests`() {
        assertFailsWith<CombinedAssertionError> {
            shouldCombineTests {
                value1 shouldBe 111
                value2 shouldNotBe "whatever"
            }
        }.let {
            val message = it.message
            assertNotNull(message)
            assertEquals("shouldCombineTests found 2 errors", message.substringBefore('\n'))
            assertEquals("Value should be 111, was 123", it.errors[0].first)
            assertEquals(this::class.qualifiedName, it.errors[0].second.className.substringBefore('$'))
            assertEquals("Value should not be \"whatever\"", it.errors[1].first)
            assertEquals(this::class.qualifiedName, it.errors[1].second.className.substringBefore('$'))
            System.err.println("Displaying the stack trace for visual checking purposes; this is not an error:")
            it.printStackTrace()
        }
    }

    @Suppress("ConstPropertyName")
    companion object {
        const val value1 = 123
        const val value2 = "whatever"
    }

}
