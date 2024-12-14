
@file:Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package io.kstuff.test

import kotlin.internal.InlineOnly
import kotlin.internal.OnlyInputTypes
import kotlin.test.asserter
import io.kstuff.test.ErrorMessages.errorShouldBe
import io.kstuff.test.ErrorMessages.errorShouldNotBe

@InlineOnly infix fun <@OnlyInputTypes T> Array<T>.shouldBe(expected: Array<T>) {
    if (!this.contentEquals(expected))
        asserter.fail(errorShouldBe(expected, this))
}

@InlineOnly infix fun <@OnlyInputTypes T> Array<T>.shouldNotBe(expected: Array<T>) {
    if (this.contentEquals(expected))
        asserter.fail(errorShouldNotBe(this))
}

@InlineOnly infix fun IntArray.shouldBe(expected: IntArray) {
    if (!this.contentEquals(expected))
        asserter.fail(errorShouldBe(expected, this))
}

@InlineOnly infix fun IntArray.shouldNotBe(expected: IntArray) {
    if (this.contentEquals(expected))
        asserter.fail(errorShouldNotBe(this))
}

@InlineOnly infix fun LongArray.shouldBe(expected: LongArray) {
    if (!this.contentEquals(expected))
        asserter.fail(errorShouldBe(expected, this))
}

@InlineOnly infix fun LongArray.shouldNotBe(expected: LongArray) {
    if (this.contentEquals(expected))
        asserter.fail(errorShouldNotBe(this))
}

@InlineOnly infix fun ShortArray.shouldBe(expected: ShortArray) {
    if (!this.contentEquals(expected))
        asserter.fail(errorShouldBe(expected, this))
}

@InlineOnly infix fun ShortArray.shouldNotBe(expected: ShortArray) {
    if (this.contentEquals(expected))
        asserter.fail(errorShouldNotBe(this))
}

@InlineOnly infix fun ByteArray.shouldBe(expected: ByteArray) {
    if (!this.contentEquals(expected))
        asserter.fail(errorShouldBe(expected, this))
}

@InlineOnly infix fun ByteArray.shouldNotBe(expected: ByteArray) {
    if (this.contentEquals(expected))
        asserter.fail(errorShouldNotBe(this))
}

@InlineOnly infix fun BooleanArray.shouldBe(expected: BooleanArray) {
    if (!this.contentEquals(expected))
        asserter.fail(errorShouldBe(expected, this))
}

@InlineOnly infix fun BooleanArray.shouldNotBe(expected: BooleanArray) {
    if (this.contentEquals(expected))
        asserter.fail(errorShouldNotBe(this))
}

@InlineOnly infix fun DoubleArray.shouldBe(expected: DoubleArray) {
    if (!this.contentEquals(expected))
        asserter.fail(errorShouldBe(expected, this))
}

@InlineOnly infix fun DoubleArray.shouldNotBe(expected: DoubleArray) {
    if (this.contentEquals(expected))
        asserter.fail(errorShouldNotBe(this))
}

@InlineOnly infix fun FloatArray.shouldBe(expected: FloatArray) {
    if (!this.contentEquals(expected))
        asserter.fail(errorShouldBe(expected, this))
}

@InlineOnly infix fun FloatArray.shouldNotBe(expected: FloatArray) {
    if (this.contentEquals(expected))
        asserter.fail(errorShouldNotBe(this))
}

@InlineOnly infix fun CharArray.shouldBe(expected: CharArray) {
    if (!this.contentEquals(expected))
        asserter.fail(errorShouldBe(expected, this))
}

@InlineOnly infix fun CharArray.shouldNotBe(expected: CharArray) {
    if (this.contentEquals(expected))
        asserter.fail(errorShouldNotBe(this))
}
