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
            assertEquals("Value should be [ \"chi\", \"psi\", \"omega\" ], was [ \"alpha\", \"beta\", \"gamma\" ]",
                    it.message)
        }
    }

    @Test fun `should perform shouldNotBe on Array`() {
        array1 shouldNotBe array9
    }

    @Test fun `should throw AssertionError when shouldNotBe on Array fails`() {
        assertFailsWith<AssertionError> {
            array1 shouldNotBe array2
        }.let {
            assertEquals("Value should not be [ \"alpha\", \"beta\", \"gamma\" ]", it.message)
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
        val booleanArray9 = booleanArrayOf(false, true, false)
        val charArray1 = charArrayOf('A', 'B', 'C')
        val charArray2 = charArrayOf('A', 'B', 'C')
        val charArray9 = charArrayOf('X', 'Y', 'Z')
    }

}
