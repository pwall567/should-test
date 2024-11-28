/*
 * @(#) CollectionTests.kt
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

@file:Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package io.kstuff.test

import kotlin.internal.InlineOnly
import kotlin.internal.OnlyInputTypes
import kotlin.test.asserter

/**
 * Test that an [Iterable] (_e.g._ [List], [Set]) contains the given element.
 */
@InlineOnly infix fun <@OnlyInputTypes T> Iterable<T>.shouldContain(element: T) {
    if (element !in this)
        asserter.fail("Collection should contain ${out(element)}")
}

/**
 * Test that an [Iterable] (_e.g._ [List], [Set]) does not contain the given element.
 */
@InlineOnly infix fun <@OnlyInputTypes T> Iterable<T>.shouldNotContain(element: T) {
    if (element in this)
        asserter.fail("Collection should not contain ${out(element)}")
}

/**
 * Test that a [Map] contains the given key.
 */
@InlineOnly infix fun <@OnlyInputTypes K, V> Map<K, V>.shouldContainKey(key: K) {
    if (!containsKey(key))
        asserter.fail("Map should contain key ${out(key)}")
}

/**
 * Test that a [Map] does not contain the given key.
 */
@InlineOnly infix fun <@OnlyInputTypes K, V> Map<K, V>.shouldNotContainKey(key: K) {
    if (containsKey(key))
        asserter.fail("Map should not contain key ${out(key)}")
}
