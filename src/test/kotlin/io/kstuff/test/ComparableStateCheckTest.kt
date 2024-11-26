package io.kstuff.test

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ComparableStateCheckTest {

    @Test fun `should use gt comparison with shouldBe`() {
        value1 shouldBe gt(10000)
        value1 shouldBe gt(12344)
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when gt comparison with shouldBe fails`() {
        assertFailsWith<AssertionError> { value1 shouldBe gt(99999) }.let {
            assertEquals("Value should be > 99999, was 12345", it.message)
        }
        assertFailsWith<AssertionError> { value1 shouldBe gt(12345) }.let {
            assertEquals("Value should be > 12345, was 12345", it.message)
        }
    }

    @Test fun `should use gt comparison with shouldNotBe`() {
        value1 shouldNotBe gt(99999)
        value1 shouldNotBe gt(12345)
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when gt comparison with shouldNotBe fails`() {
        assertFailsWith<AssertionError> { value1 shouldNotBe gt(9999) }.let {
            assertEquals("Value should not be > 9999, was 12345", it.message)
        }
        assertFailsWith<AssertionError> { value1 shouldNotBe gt(12344) }.let {
            assertEquals("Value should not be > 12344, was 12345", it.message)
        }
    }

    @Test fun `should use inRange comparison with shouldBe`() {
        value1 shouldBe inRange(10000..19999)
    }

    @Suppress("ComplexRedundantLet")
    @Test fun `should throw AssertionError when inRange comparison with shouldBe fails`() {
        assertFailsWith<AssertionError> { value1 shouldBe inRange(1000..1999) }.let {
            assertEquals("Value should be in range 1000..1999, was 12345", it.message)
        }
    }

    @Suppress("ConstPropertyName")
    companion object {
        const val value1 = 12345
    }

}
