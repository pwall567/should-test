/*
 * @(#) ComparableStateCheck.kt
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

/**
 * A [StateCheck] to implement comparisons on instances of classes that implement [Comparable].
 */
class ComparableStateCheck<T>(
    override val name: String,
    val stateFunction: (T) -> Boolean
) : StateCheck<T> {

    override fun inState(value: T): Boolean = stateFunction(value)

}

/**
 * Create a [ComparableStateCheck] to test whether a value is greater than a maximum.
 */
fun <C : Comparable<C>> gt(other: C): StateCheck<C> = ComparableStateCheck("> $other") { it > other }

/**
 * Create a [ComparableStateCheck] to test whether a value is greater than or equal to an exclusive maximum.
 */
fun <C : Comparable<C>> ge(other: C): StateCheck<C> = ComparableStateCheck(">= $other") { it >= other }

/**
 * Create a [ComparableStateCheck] to test whether a value is equal to another value.  (This is included for
 * completeness; the `shouldBe` test will check for equality.)
 */
fun <C : Comparable<C>> eq(other: C): StateCheck<C> = ComparableStateCheck("== $other") { it == other }

/**
 * Create a [ComparableStateCheck] to test whether a value is not equal to another value.  (This is included for
 * completeness; the `shouldNotBe` test will check for inequality.)
 */
fun <C : Comparable<C>> ne(other: C): StateCheck<C> = ComparableStateCheck("!= $other") { it != other }

/**
 * Create a [ComparableStateCheck] to test whether a value is less than or equal to an exclusive minimum.
 */
fun <C : Comparable<C>> le(other: C): StateCheck<C> = ComparableStateCheck("<= $other") { it <= other }

/**
 * Create a [ComparableStateCheck] to test whether a value is less than a minimum.
 */
fun <C : Comparable<C>> lt(other: C): StateCheck<C> = ComparableStateCheck("< $other") { it < other }

fun <C : Comparable<C>> inRange(range: ClosedRange<C>): StateCheck<C> =
        ComparableStateCheck("in range $range") { it in range }
