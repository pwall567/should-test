package io.kstuff.test

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ArrayTestsTest {

    @Test fun `should perform shouldBe on Array`() {
        array1 shouldBe array2
    }

    @Test fun `should throw AssertionError when shouldBe on Array fails`() {
        assertFailsWith<AssertionError> {
            array1 shouldBe array9
        }.let {
            assertEquals("""Value should be [ "chi", "psi", "omega" ], was [ "alpha", "beta", "gamma" ]""", it.message)
        }
    }

    @Test fun `should perform shouldNotBe on Array`() {
        array1 shouldNotBe array9
    }

    @Test fun `should throw AssertionError when shouldNotBe on Array fails`() {
        assertFailsWith<AssertionError> {
            array1 shouldNotBe array2
        }.let {
            assertEquals("""Value should not be [ "alpha", "beta", "gamma" ]""", it.message)
        }
    }

    @Test fun `should perform shouldContain on Array`() {
        array1 shouldContain "alpha"
        array1 shouldContain "gamma"
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldContain on Array fails`() {
        assertFailsWith<AssertionError> { array1 shouldContain "delta" }.let {
            assertEquals("""Array [ "alpha", "beta", "gamma" ] should contain "delta"""", it.message)
        }
    }

    @Test fun `should perform shouldNotContain on Array`() {
        array1 shouldNotContain "omega"
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldNotContain on Array fails`() {
        assertFailsWith<AssertionError> { array1 shouldNotContain "beta" }.let {
            assertEquals("""Array [ "alpha", "beta", "gamma" ] should not contain "beta"""", it.message)
        }
    }

    @Test fun `should perform shouldBe on IntArray`() {
        intArray1 shouldBe intArray2
    }

    @Test fun `should throw AssertionError when shouldBe on IntArray fails`() {
        assertFailsWith<AssertionError> {
            intArray1 shouldBe intArray9
        }.let {
            assertEquals("Value should be [ 777, 888, 999 ], was [ 111, 222, 333 ]", it.message)
        }
    }

    @Test fun `should perform shouldNotBe on IntArray`() {
        intArray1 shouldNotBe intArray9
    }

    @Test fun `should throw AssertionError when shouldNotBe on IntArray fails`() {
        assertFailsWith<AssertionError> {
            intArray1 shouldNotBe intArray2
        }.let {
            assertEquals("Value should not be [ 111, 222, 333 ]", it.message)
        }
    }

    @Test fun `should perform shouldContain on IntArray`() {
        intArray1 shouldContain 111
        intArray1 shouldContain 333
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldContain on IntArray fails`() {
        assertFailsWith<AssertionError> { intArray1 shouldContain 999 }.let {
            assertEquals("IntArray [ 111, 222, 333 ] should contain 999", it.message)
        }
    }

    @Test fun `should perform shouldNotContain on IntArray`() {
        intArray1 shouldNotContain 999
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldNotContain on IntArray fails`() {
        assertFailsWith<AssertionError> { intArray1 shouldNotContain 222 }.let {
            assertEquals("IntArray [ 111, 222, 333 ] should not contain 222", it.message)
        }
    }

    @Test fun `should perform shouldBe on LongArray`() {
        longArray1 shouldBe longArray2
    }

    @Test fun `should throw AssertionError when shouldBe on LongArray fails`() {
        assertFailsWith<AssertionError> {
            longArray1 shouldBe longArray9
        }.let {
            assertEquals("Value should be [ 999L, 888L, 777L ], was [ 123L, 456L, 789L ]", it.message)
        }
    }

    @Test fun `should perform shouldNotBe on LongArray`() {
        longArray1 shouldNotBe longArray9
    }

    @Test fun `should throw AssertionError when shouldNotBe on LongArray fails`() {
        assertFailsWith<AssertionError> {
            longArray1 shouldNotBe longArray2
        }.let {
            assertEquals("Value should not be [ 123L, 456L, 789L ]", it.message)
        }
    }

    @Test fun `should perform shouldContain on LongArray`() {
        longArray1 shouldContain 123L
        longArray1 shouldContain 456L
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldContain on LongArray fails`() {
        assertFailsWith<AssertionError> { longArray1 shouldContain 222L }.let {
            assertEquals("LongArray [ 123L, 456L, 789L ] should contain 222L", it.message)
        }
    }

    @Test fun `should perform shouldNotContain on LongArray`() {
        longArray1 shouldNotContain 888L
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldNotContain on LongArray fails`() {
        assertFailsWith<AssertionError> { longArray1 shouldNotContain 456L }.let {
            assertEquals("LongArray [ 123L, 456L, 789L ] should not contain 456L", it.message)
        }
    }

    @Test fun `should perform shouldBe on ShortArray`() {
        shortArray1 shouldBe shortArray2
    }

    @Test fun `should throw AssertionError when shouldBe on ShortArray fails`() {
        assertFailsWith<AssertionError> {
            shortArray1 shouldBe shortArray9
        }.let {
            assertEquals("Value should be [ 77, 88, 99 ], was [ 11, 22, 33 ]", it.message)
        }
    }

    @Test fun `should perform shouldNotBe on ShortArray`() {
        shortArray1 shouldNotBe shortArray9
    }

    @Test fun `should throw AssertionError when shouldNotBe on ShortArray fails`() {
        assertFailsWith<AssertionError> {
            shortArray1 shouldNotBe shortArray2
        }.let {
            assertEquals("Value should not be [ 11, 22, 33 ]", it.message)
        }
    }

    @Test fun `should perform shouldContain on ShortArray`() {
        shortArray1 shouldContain 11
        shortArray1 shouldContain 22
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldContain on ShortArray fails`() {
        assertFailsWith<AssertionError> { shortArray1 shouldContain 99 }.let {
            assertEquals("ShortArray [ 11, 22, 33 ] should contain 99", it.message)
        }
    }

    @Test fun `should perform shouldNotContain on ShortArray`() {
        shortArray1 shouldNotContain 99
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldNotContain on ShortArray fails`() {
        assertFailsWith<AssertionError> { shortArray1 shouldNotContain 22 }.let {
            assertEquals("ShortArray [ 11, 22, 33 ] should not contain 22", it.message)
        }
    }

    @Test fun `should perform shouldBe on ByteArray`() {
        byteArray1 shouldBe byteArray2
    }

    @Test fun `should throw AssertionError when shouldBe on ByteArray fails`() {
        assertFailsWith<AssertionError> {
            byteArray1 shouldBe byteArray9
        }.let {
            assertEquals("Value should be [ 7, 8, 9 ], was [ 1, 2, 3 ]", it.message)
        }
    }

    @Test fun `should perform shouldNotBe on ByteArray`() {
        byteArray1 shouldNotBe byteArray9
    }

    @Test fun `should throw AssertionError when shouldNotBe on ByteArray fails`() {
        assertFailsWith<AssertionError> {
            byteArray1 shouldNotBe byteArray2
        }.let {
            assertEquals("Value should not be [ 1, 2, 3 ]", it.message)
        }
    }

    @Test fun `should perform shouldContain on ByteArray`() {
        byteArray1 shouldContain 1
        byteArray1 shouldContain 3
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldContain on ByteArray fails`() {
        assertFailsWith<AssertionError> { byteArray1 shouldContain 7 }.let {
            assertEquals("ByteArray [ 1, 2, 3 ] should contain 7", it.message)
        }
    }

    @Test fun `should perform shouldNotContain on ByteArray`() {
        byteArray1 shouldNotContain 8
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldNotContain on ByteArray fails`() {
        assertFailsWith<AssertionError> { byteArray1 shouldNotContain 2 }.let {
            assertEquals("ByteArray [ 1, 2, 3 ] should not contain 2", it.message)
        }
    }

    @Test fun `should perform shouldBe on BooleanArray`() {
        booleanArray1 shouldBe booleanArray2
    }

    @Test fun `should throw AssertionError when shouldBe on BooleanArray fails`() {
        assertFailsWith<AssertionError> {
            booleanArray1 shouldBe booleanArray9
        }.let {
            assertEquals("Value should be [ false, true, false ], was [ true, false, true ]", it.message)
        }
    }

    @Test fun `should perform shouldNotBe on BooleanArray`() {
        booleanArray1 shouldNotBe booleanArray9
    }

    @Test fun `should throw AssertionError when shouldNotBe on BooleanArray fails`() {
        assertFailsWith<AssertionError> {
            booleanArray1 shouldNotBe booleanArray2
        }.let {
            assertEquals("Value should not be [ true, false, true ]", it.message)
        }
    }

    @Test fun `should perform shouldContain on BooleanArray`() {
        booleanArray1 shouldContain true
        booleanArray1 shouldContain false
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldContain on BooleanArray fails`() {
        assertFailsWith<AssertionError> { booleanArray3 shouldContain true }.let {
            assertEquals("BooleanArray [ false, false ] should contain true", it.message)
        }
    }

    @Test fun `should perform shouldNotContain on BooleanArray`() {
        booleanArray3 shouldNotContain true
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldNotContain on BooleanArray fails`() {
        assertFailsWith<AssertionError> { booleanArray1 shouldNotContain true }.let {
            assertEquals("BooleanArray [ true, false, true ] should not contain true", it.message)
        }
    }

    @Test fun `should perform shouldBe on DoubleArray`() {
        doubleArray1 shouldBe doubleArray2
    }

    @Test fun `should throw AssertionError when shouldBe on DoubleArray fails`() {
        assertFailsWith<AssertionError> {
            doubleArray1 shouldBe doubleArray9
        }.let {
            assertEquals("Value should be [ 7.5, 8.0, 9.0 ], was [ 1.0, 1.5, 2.0 ]", it.message)
        }
    }

    @Test fun `should perform shouldNotBe on DoubleArray`() {
        doubleArray1 shouldNotBe doubleArray9
    }

    @Test fun `should throw AssertionError when shouldNotBe on DoubleArray fails`() {
        assertFailsWith<AssertionError> {
            doubleArray1 shouldNotBe doubleArray2
        }.let {
            assertEquals("Value should not be [ 1.0, 1.5, 2.0 ]", it.message)
        }
    }

    @Test fun `should perform shouldContain on DoubleArray`() {
        doubleArray1 shouldContain 1.5
        doubleArray1 shouldContain 1.0
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldContain on DoubleArray fails`() {
        assertFailsWith<AssertionError> { doubleArray1 shouldContain 5.5 }.let {
            assertEquals("DoubleArray [ 1.0, 1.5, 2.0 ] should contain 5.5", it.message)
        }
    }

    @Test fun `should perform shouldNotContain on DoubleArray`() {
        doubleArray1 shouldNotContain 9.0
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldNotContain on DoubleArray fails`() {
        assertFailsWith<AssertionError> { doubleArray1 shouldNotContain 1.0 }.let {
            assertEquals("DoubleArray [ 1.0, 1.5, 2.0 ] should not contain 1.0", it.message)
        }
    }

    @Test fun `should perform shouldBe on FloatArray`() {
        floatArray1 shouldBe floatArray2
    }

    @Test fun `should throw AssertionError when shouldBe on FloatArray fails`() {
        assertFailsWith<AssertionError> {
            floatArray1 shouldBe floatArray9
        }.let {
            assertEquals("Value should be [ 6.0F, 6.5F, 7.5F ], was [ 2.5F, 3.0F, 3.5F ]", it.message)
        }
    }

    @Test fun `should perform shouldNotBe on FloatArray`() {
        floatArray1 shouldNotBe floatArray9
    }

    @Test fun `should throw AssertionError when shouldNotBe on FloatArray fails`() {
        assertFailsWith<AssertionError> {
            floatArray1 shouldNotBe floatArray2
        }.let {
            assertEquals("Value should not be [ 2.5F, 3.0F, 3.5F ]", it.message)
        }
    }

    @Test fun `should perform shouldContain on FloatArray`() {
        floatArray1 shouldContain 2.5F
        floatArray1 shouldContain 3.0F
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldContain on FloatArray fails`() {
        assertFailsWith<AssertionError> { floatArray1 shouldContain 7.5F }.let {
            assertEquals("FloatArray [ 2.5F, 3.0F, 3.5F ] should contain 7.5F", it.message)
        }
    }

    @Test fun `should perform shouldNotContain on FloatArray`() {
        floatArray1 shouldNotContain 8.0F
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldNotContain on FloatArray fails`() {
        assertFailsWith<AssertionError> { floatArray1 shouldNotContain 3.5F }.let {
            assertEquals("FloatArray [ 2.5F, 3.0F, 3.5F ] should not contain 3.5F", it.message)
        }
    }

    @Test fun `should perform shouldBe on CharArray`() {
        charArray1 shouldBe charArray2
    }

    @Test fun `should throw AssertionError when shouldBe on CharArray fails`() {
        assertFailsWith<AssertionError> {
            charArray1 shouldBe charArray9
        }.let {
            assertEquals("Value should be \"XYZ\", was \"ABC\"", it.message)
        }
    }

    @Test fun `should perform shouldNotBe on CharArray`() {
        charArray1 shouldNotBe charArray9
    }

    @Test fun `should throw AssertionError when shouldNotBe on CharArray fails`() {
        assertFailsWith<AssertionError> {
            charArray1 shouldNotBe charArray2
        }.let {
            assertEquals("Value should not be \"ABC\"", it.message)
        }
    }

    @Test fun `should perform shouldContain on CharArray`() {
        charArray1 shouldContain 'A'
        charArray1 shouldContain 'B'
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldContain on CharArray fails`() {
        assertFailsWith<AssertionError> { charArray1 shouldContain 'Q' }.let {
            assertEquals("CharArray \"ABC\" should contain 'Q'", it.message)
        }
    }

    @Test fun `should perform shouldNotContain on CharArray`() {
        charArray1 shouldNotContain 'X'
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldNotContain on CharArray fails`() {
        assertFailsWith<AssertionError> { charArray1 shouldNotContain 'C' }.let {
            assertEquals("CharArray \"ABC\" should not contain 'C'", it.message)
        }
    }

    @Test fun `should compare Array using inferred type`() {
        shouldBeEqual(array1, array2)
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldBeEqual on Array fails`() {
        assertFailsWith<AssertionError> { shouldBeEqual(array1, array9) }.let {
            assertEquals("""Value should be [ "alpha", "beta", "gamma" ], was [ "chi", "psi", "omega" ]""", it.message)
        }
    }

    @Test fun `should compare Array not equal using inferred type`() {
        shouldNotBeEqual(array1, array9)
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldNotBeEqual on Array fails`() {
        assertFailsWith<AssertionError> { shouldNotBeEqual(array1, array2) }.let {
            assertEquals("""Value should not be [ "alpha", "beta", "gamma" ]""", it.message)
        }
    }

    @Test fun `should compare IntArray using inferred type`() {
        shouldBeEqual(intArray1, intArray2)
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldBeEqual on IntArray fails`() {
        assertFailsWith<AssertionError> { shouldBeEqual(intArray1, intArray9) }.let {
            assertEquals("Value should be [ 111, 222, 333 ], was [ 777, 888, 999 ]", it.message)
        }
    }

    @Test fun `should compare IntArray not equal using inferred type`() {
        shouldNotBeEqual(intArray1, intArray9)
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldNotBeEqual on IntArray fails`() {
        assertFailsWith<AssertionError> { shouldNotBeEqual(intArray1, intArray2) }.let {
            assertEquals("Value should not be [ 111, 222, 333 ]", it.message)
        }
    }

    @Test fun `should compare LongArray using inferred type`() {
        shouldBeEqual(longArray1, longArray2)
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldBeEqual on LongArray fails`() {
        assertFailsWith<AssertionError> { shouldBeEqual(longArray1, longArray9) }.let {
            assertEquals("Value should be [ 123L, 456L, 789L ], was [ 999L, 888L, 777L ]", it.message)
        }
    }

    @Test fun `should compare LongArray not equal using inferred type`() {
        shouldNotBeEqual(longArray1, longArray9)
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldNotBeEqual on LongArray fails`() {
        assertFailsWith<AssertionError> { shouldNotBeEqual(longArray1, longArray2) }.let {
            assertEquals("Value should not be [ 123L, 456L, 789L ]", it.message)
        }
    }

    @Test fun `should compare ShortArray using inferred type`() {
        shouldBeEqual(shortArray1, shortArray2)
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldBeEqual on ShortArray fails`() {
        assertFailsWith<AssertionError> { shouldBeEqual(shortArray1, shortArray9) }.let {
            assertEquals("Value should be [ 11, 22, 33 ], was [ 77, 88, 99 ]", it.message)
        }
    }

    @Test fun `should compare ShortArray not equal using inferred type`() {
        shouldNotBeEqual(shortArray1, shortArray9)
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldNotBeEqual on ShortArray fails`() {
        assertFailsWith<AssertionError> { shouldNotBeEqual(shortArray1, shortArray2) }.let {
            assertEquals("Value should not be [ 11, 22, 33 ]", it.message)
        }
    }

    @Test fun `should compare ByteArray using inferred type`() {
        shouldBeEqual(byteArray1, byteArray2)
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldBeEqual on ByteArray fails`() {
        assertFailsWith<AssertionError> { shouldBeEqual(byteArray1, byteArray9) }.let {
            assertEquals("Value should be [ 1, 2, 3 ], was [ 7, 8, 9 ]", it.message)
        }
    }

    @Test fun `should compare ByteArray not equal using inferred type`() {
        shouldNotBeEqual(byteArray1, byteArray9)
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldNotBeEqual on ByteArray fails`() {
        assertFailsWith<AssertionError> { shouldNotBeEqual(byteArray1, byteArray2) }.let {
            assertEquals("Value should not be [ 1, 2, 3 ]", it.message)
        }
    }

    @Test fun `should compare BooleanArray using inferred type`() {
        shouldBeEqual(booleanArray1, booleanArray2)
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldBeEqual on BooleanArray fails`() {
        assertFailsWith<AssertionError> { shouldBeEqual(booleanArray1, booleanArray9) }.let {
            assertEquals("Value should be [ true, false, true ], was [ false, true, false ]", it.message)
        }
    }

    @Test fun `should compare BooleanArray not equal using inferred type`() {
        shouldNotBeEqual(booleanArray1, booleanArray9)
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldNotBeEqual on BooleanArray fails`() {
        assertFailsWith<AssertionError> { shouldNotBeEqual(booleanArray1, booleanArray2) }.let {
            assertEquals("Value should not be [ true, false, true ]", it.message)
        }
    }

    @Test fun `should compare DoubleArray using inferred type`() {
        shouldBeEqual(doubleArray1, doubleArray2)
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldBeEqual on DoubleArray fails`() {
        assertFailsWith<AssertionError> { shouldBeEqual(doubleArray1, doubleArray9) }.let {
            assertEquals("Value should be [ 1.0, 1.5, 2.0 ], was [ 7.5, 8.0, 9.0 ]", it.message)
        }
    }

    @Test fun `should compare DoubleArray not equal using inferred type`() {
        shouldNotBeEqual(doubleArray1, doubleArray9)
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldNotBeEqual on DoubleArray fails`() {
        assertFailsWith<AssertionError> { shouldNotBeEqual(doubleArray1, doubleArray2) }.let {
            assertEquals("Value should not be [ 1.0, 1.5, 2.0 ]", it.message)
        }
    }

    @Test fun `should compare FloatArray using inferred type`() {
        shouldBeEqual(floatArray1, floatArray2)
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldBeEqual on FloatArray fails`() {
        assertFailsWith<AssertionError> { shouldBeEqual(floatArray1, floatArray9) }.let {
            assertEquals("Value should be [ 2.5F, 3.0F, 3.5F ], was [ 6.0F, 6.5F, 7.5F ]", it.message)
        }
    }

    @Test fun `should compare FloatArray not equal using inferred type`() {
        shouldNotBeEqual(floatArray1, floatArray9)
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldNotBeEqual on FloatArray fails`() {
        assertFailsWith<AssertionError> { shouldNotBeEqual(floatArray1, floatArray2) }.let {
            assertEquals("Value should not be [ 2.5F, 3.0F, 3.5F ]", it.message)
        }
    }

    @Test fun `should compare CharArray using inferred type`() {
        shouldBeEqual(charArray1, charArray2)
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldBeEqual on CharArray fails`() {
        assertFailsWith<AssertionError> { shouldBeEqual(charArray1, charArray9) }.let {
            assertEquals("Value should be \"ABC\", was \"XYZ\"", it.message)
        }
    }

    @Test fun `should compare CharArray not equal using inferred type`() {
        shouldNotBeEqual(charArray1, charArray9)
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when shouldNotBeEqual on CharArray fails`() {
        assertFailsWith<AssertionError> { shouldNotBeEqual(charArray1, charArray2) }.let {
            assertEquals("Value should not be \"ABC\"", it.message)
        }
    }

    companion object {
        val array1 = arrayOf("alpha", "beta", "gamma")
        val array2 = arrayOf("alpha", "beta", "gamma")
        val array9 = arrayOf("chi", "psi", "omega")
        val intArray1 = intArrayOf(111, 222, 333)
        val intArray2 = intArrayOf(111, 222, 333)
        val intArray9 = intArrayOf(777, 888, 999)
        val longArray1 = longArrayOf(123L, 456L, 789L)
        val longArray2 = longArrayOf(123L, 456L, 789L)
        val longArray9 = longArrayOf(999L, 888L, 777L)
        val shortArray1 = shortArrayOf(11, 22, 33)
        val shortArray2 = shortArrayOf(11, 22, 33)
        val shortArray9 = shortArrayOf(77, 88, 99)
        val byteArray1 = byteArrayOf(1, 2, 3)
        val byteArray2 = byteArrayOf(1, 2, 3)
        val byteArray9 = byteArrayOf(7, 8, 9)
        val booleanArray1 = booleanArrayOf(true, false, true)
        val booleanArray2 = booleanArrayOf(true, false, true)
        val booleanArray3 = booleanArrayOf(false, false)
        val booleanArray9 = booleanArrayOf(false, true, false)
        val doubleArray1 = doubleArrayOf(1.0, 1.5, 2.0)
        val doubleArray2 = doubleArrayOf(1.0, 1.5, 2.0)
        val doubleArray9 = doubleArrayOf(7.5, 8.0, 9.0)
        val floatArray1 = floatArrayOf(2.5F, 3.0F, 3.5F)
        val floatArray2 = floatArrayOf(2.5F, 3.0F, 3.5F)
        val floatArray9 = floatArrayOf(6.0F, 6.5F, 7.5F)
        val charArray1 = charArrayOf('A', 'B', 'C')
        val charArray2 = charArrayOf('A', 'B', 'C')
        val charArray9 = charArrayOf('X', 'Y', 'Z')
    }

}
