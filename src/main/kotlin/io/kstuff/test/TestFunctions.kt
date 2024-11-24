/*
 * @(#) TestFunctions.kt
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

internal val a = asserter

infix fun String?.shouldBe(expected: String?) {
    if (this != expected) {
        if (isComplex() || expected.isComplex())
            a.assertEquals(null, expected, this) // this function causes IDE to display comparison window
        else
            a.fail("Value should be ${out(expected)}, was ${out(this)}")
    }
}

infix fun <T> T.shouldBe(expected: T) {
    if (this != expected)
        a.fail("Value should be ${out(expected)}, was ${out(this)}")
}

infix fun <T> T.shouldBe(test: T.() -> Boolean) {
    if (!test())
        a.fail("Value should be valid for test, was ${out(this)}")
}

infix fun <T> T.shouldNotBe(expected: T) {
    if (this == expected)
        a.fail("Value should not be ${out(expected)}")
}

infix fun <T> T.shouldNotBe(test: T.() -> Boolean) {
    if (test())
        a.fail("Value should not be valid for test, was ${out(this)}")
}

infix fun <T> Iterable<T>.shouldContain(element: T) {
    if (element !in this)
        a.fail("Collection should contain ${out(element)}")
}

infix fun <T> Iterable<T>.shouldNotContain(element: T) {
    if (element in this)
        a.fail("Collection should not contain ${out(element)}")
}

infix fun <K, V> Map<K, V>.shouldContainKey(key: K) {
    if (!containsKey(key))
        a.fail("Map should contain key ${out(key)}")
}

infix fun <K, V> Map<K, V>.shouldNotContainKey(key: K) {
    if (containsKey(key))
        a.fail("Map should not contain key ${out(key)}")
}

infix fun String.shouldContain(subString: String) {
    if (!contains(subString))
        a.fail("String should contain ${out(subString)}, was ${out(this)}")
}

infix fun String.shouldNotContain(subString: String) {
    if (contains(subString))
        a.fail("String should not contain ${out(subString)}, was ${out(this)}")
}

infix fun String.shouldStartWith(prefix: String) {
    if (!startsWith(prefix))
        a.fail("String should start with ${out(prefix)}, was ${out(this)}")
}

infix fun String.shouldNotStartWith(prefix: String) {
    if (startsWith(prefix))
        a.fail("String should not start with ${out(prefix)}, was ${out(this)}")
}

infix fun String.shouldEndWith(suffix: String) {
    if (!endsWith(suffix))
        a.fail("String should end with ${out(suffix)}, was ${out(this)}")
}

infix fun String.shouldNotEndWith(suffix: String) {
    if (endsWith(suffix))
        a.fail("String should not end with ${out(suffix)}, was ${out(this)}")
}

internal fun out(value: Any?): String = when (value) {
    null -> "null"
    is CharSequence -> "\"$value\""
    else -> value.toString()
}

internal fun String?.isComplex(): Boolean = this != null && (length > 20  || any { it !in ' '..'~' })
