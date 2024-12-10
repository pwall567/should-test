/*
 * @(#) ComparablePredicate.kt
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

class ComparablePredicate<T : Comparable<T>>(
    override val name: String,
    val predicate: (T) -> Boolean
) : NamedPredicate<T> {

    override fun invoke(value: T): Boolean = predicate(value)

}

fun <T : Comparable<T>> gt(value: T): NamedPredicate<T> = ComparablePredicate("> $value") { it > value }

fun <T : Comparable<T>> ge(value: T): NamedPredicate<T> = ComparablePredicate(">= $value") { it >= value }

fun <T : Comparable<T>> le(value: T): NamedPredicate<T> = ComparablePredicate("<= $value") { it <= value }

fun <T : Comparable<T>> lt(value: T): NamedPredicate<T> = ComparablePredicate("< $value") { it < value }
