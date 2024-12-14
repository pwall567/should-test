# should-test

[![Build Status](https://github.com/pwall567/should-test/actions/workflows/build.yml/badge.svg)](https://github.com/pwall567/should-test/actions/workflows/build.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Kotlin](https://img.shields.io/static/v1?label=Kotlin&message=v1.9.24&color=7f52ff&logo=kotlin&logoColor=7f52ff)](https://github.com/JetBrains/kotlin/releases/tag/v1.9.24)
[![Maven Central](https://img.shields.io/maven-central/v/io.kstuff/should-test?label=Maven%20Central)](https://search.maven.org/search?q=g:%22io.kstuff%22%20AND%20a:%22should-test%22)

Kotlin testing functions

## Background

### Problem

A while ago, someone introduced me to a testing library that used the syntax:
```kotlin
    testValue shouldBe expectedvalue
```
This seemed like a great idea, and good use of the capabilities of Kotlin.
The `shouldBe` function is an `infix` function, meaning that it doesn&rsquo;t require a dot before the function name or
parentheses around the RHS argument.
And as a generic extension function, it could be applied to a wide range of values &ndash; `Int`s, `String`s, or even
complex user-defined classes.

But then I encountered a problem where my test result was not just the wrong value, but the wrong type, and the
`shouldBe` function call had compiled without error.
The function had been written in a way that didn&rsquo;t allow the Kotlin compiler (or the IDE) to perform strong
type-checking on the arguments.

That shook my confidence in the library, and I went back to using the `kotlin.test` functions, but the style of tests
seemed too good to give up on.

### Solution

This library is an attempt to provide testing functions with an attractive syntax, but also retaining the strong typing
of the official Kotlin libraries.
And as a bonus, the library is very simple and lightweight, and like the `kotlin.test` functions, it works equally well
with JUnit 4 and 5.

### &ldquo;should&rdquo;

A quick note on the use of the word &ldquo;should&rdquo; &ndash; the long-established standard on the interpretation of
technical documents [`RFC2119`](https://www.rfc-editor.org/rfc/rfc2119.html) declares that SHOULD is to be understood as
a recommendation (albeit a very strong one).
The correct word to use in a situation where a particular outcome is mandatory is MUST &ndash; this is the most
appropriate word to use for a unit test, where the result must be as specified.

But &ldquo;should&rdquo; is so well-established in the terminology of unit testing that rather than fighting against it,
I have decided to embrace it.
All of the function names of the public API start with `should`, and it is even incorporated into the name of the
library itself.

## Generic Test Functions

### `shouldBe`

The main function is the one that performs a simple equality test:
```kotlin
    testValue shouldBe expectedValue
```
As a generic function, it expects both sides of the comparison to be the same type.

Boolean values may be tested:
```kotlin
    booleanValue shouldBe true
```

Arrays may be tested, including `IntArray`, `BooleanArray` _etc._ (the `contentEquals` function will be used for the
comparison):
```kotlin
    arrayValue shouldBe arrayOf(111, 222, 333)
```

And nullable values may be tested for `null`:
```kotlin
    nullableValue shouldBe null
```

### `shouldNotBe`

The complement to `shouldBe` performs a simple inequality test:
```kotlin
    testValue shouldNotBe wrongValue
```
Like `shouldBe`, it expects both sides of the comparison to be the same type, and it may be applied to boolean, array
and nullable values.

### `shouldBe` with Predicate

A variation on `shouldBe` takes a predicate returning a `Boolean` (see [Named Predicates](#named-predicates) below):
```kotlin
    stringValue shouldBe { it.isNotEmpty() }
```

### `shouldNotBe` with Predicate

The complement of `shouldBe` with a predicate:
```kotlin
    stringValue shouldNotBe { it.isEmpty() }
```

### `shouldBeOneOf`

Tests whether a value is one of a number of possibilities:
```kotlin
    testValue shouldBeOneOf listOf("alpha", "beta", "gamma")
```

### `shouldNotBeOneOf`

Tests whether a value is not one of a number:
```kotlin
    testValue shouldNotBeOneOf listOf("chi", "psi", "omega")
```

### `shouldBeSameInstance`

Performs an identity comparison (`===`) on two values:
```kotlin
    testValue shouldBeSameInstance original
```

### `shouldNotBeSameInstance`

The complement of `shouldBeSameInstance`:
```kotlin
    testValue shouldNotBeSameInstance original
```

### `shouldBeNonNull`

Tests whether a value is non-null:
```kotlin
    testValue.shouldBeNonNull()
```
Because this doesn't have a parameter value, the infix notation isn&rsquo;t available, but it has the advantage that it
uses the Kotlin &ldquo;contract&rdquo; mechanism to indicate to the compiler that if this test succeeds, the value may
safely be considered as non-null in subsequent operations:
```kotlin
    nullableString.shouldBeNonNull()
    nullableString shouldStartWith "Hello"
```
The function also returns the non-null value which can be used in a chained test:
```kotlin
    nullableString.shouldBeNonNull().length shouldBe 10
```

### `shouldBeType`

Tests whether a value is of a specified type:
```kotlin
    testValue.shouldBeType<String>()
```
Like `shouldBeNonNull`, this function doesn&rsquo;t have access to the infix notation, but also like `shouldBeNonNull`,
it uses the Kotlin &ldquo;contract&rdquo; mechanism to indicate to the compiler that the value may be
&ldquo;smart cast&rdquo; to the specified type:
```kotlin
    testValue.shouldBeType<String>()
    testValue shouldStartWith "Hello"
```
It also returns the type-checked value that may be used in a chained test:
```kotlin
    testValue.shouldBeType<String>().length shouldBe 8
```

### `shouldBeEqual`

There is a problem with the `shouldBe` syntax.
As the LHS (the actual value) is being evaluated, the RHS (the expected value) is still unknown, so type inference can
not be used to infer the type of the LHS.

Suppose you have a function that returns a `List` of a generic type; when testing the result of this function, the older
`assertEquals` function would allow type inference to be used to determine the type of the result.
Where type inference is not available, the type must be specified in full, and that can be tedious if the actual type is
complex:
```kotlin
    testFunction<List<Pair<String, Int>>>() shouldBe listOf("name" to 123)
```

The `shouldBeEqual` function allows the use of type inference to determine the inferred type, but of course it loses the
advantage of the infix syntax.
```kotlin
    shouldBeEqual(listOf("name" to 123), testFunction())
```

The `shouldNotBeEqual` function is the complement to the `shouldBeEqual` function.

## Collection Tests

### `shouldContain` and `shouldNotContain`

Tests that a collection (an `Iterable`,  _e.g._ a `List`, `Set` _etc._) contains (or does not contain) a given value:
```kotlin
    testList shouldContain "alpha"
```

### `shouldContainKey` and `shouldNotContainKey`

Tests that a `Map` contains (or does not contain) a given key:
```kotlin
    testMap shouldContainKey "first"
```

## String Tests

### `shouldContain` and `shouldNotContain`

Tests that a string contains (or does not contain) the given substring.

### `shouldStartWith` and `shouldNotStartWith`

Tests that a string starts with (or does not start with) the given prefix.

### `shouldEndWith` and `shouldNotEndWith`

Tests that a string ends with (or does not end with) the given suffix.

### `shouldMatch` and `shouldNotMatch`

Tests that a string matches (or does not match) a given `Regex`.

## Exception Tests

### `shouldThrow`

Tests whether a block of code results in a nominated exception type being thrown:
```kotlin
    shouldThrow<NumberFormatException> {
        numericString.toInt()
    }
```
An alternative form includes a check of the message in the exception:
```kotlin
    shouldThrow<NumberFormatException>("For input string: \"$numericString\"") {
        numericString.toInt()
    }
```
Both forms of `shouldThrow` return the exception (`Throwable`) instance, which may be used in further tests:
```kotlin
    shouldThrow<NumberFormatException>("For input string: \"$numericString\"") {
        numericString.toInt()
    }.let {
        it.cause shouldBe null
    }
```

## Named Predicates

The generic `shouldBe` and `shouldNotBe` functions have forms that take a predicate &ndash; a function that, when
applied to the value, returns a `Boolean`.
The example given above tests whether a string value is empty:
```kotlin
    stringValue shouldBe { it.isNotEmpty() }
```
but the problem with using predicates in this way is that the library does not have sufficient information to form a
useful error message if the predicate returns `false`.

The library includes a `NamedPredicate` interface, which allows a name to be given to the condition being tested; the
`shouldBe` (and `shouldNotBe`) function will use that name when reporting an error.

Implementations of the `NamedPredicate` interface must implement the `name` value, along with the usual `invoke()`
function, as in this example:
```kotlin
    val even = object : NamedPredicate<Int> {
        override val name: String = "even"
        override fun invoke(value: Int): Boolean = (value and 1) == 0
    }
```
Then, a test may be written:
```kotlin
    value1 shouldBe even
```
And an error will be reported as &ldquo;`Value should be even, was 123`&rdquo;.

## Comparable Predicates

There is a set of functions that will create a `NamedPredicate` to perform comparisons on `Comparable` values
automatically.

- `lt(value)`: check that the original value (the value to the left of the `shouldBe`) is less than the value given here
- `le(value)`: check that the original value is less than or equal to the value given here
- `ge(value)`: check that the original value is greater than or equal to the value given here
- `gt(value)`: check that the original value is greater than the value given here

This allows tests such as the following:
```kotlin
    value1 shouldBe gt(100)
```
And these tests will give helpful error messages like &ldquo;`Value should be > 100, was 99`&rdquo;.

These tests are available only on values of classes that implement the `Comparable` interface, _e.g._ `Int`, `Long`.

## Combined Tests

Some developers prefer to see the results of an entire set of tests, rather than having the test framework stop on the
first failing test.
This technique, sometimes referred to as &ldquo;soft assertions&rdquo;, is available using the `shouldCombineTests`
function:
```kotlin
    shouldCombineTests {
        value1 shouldBe 123
        value2 shouldNotBe "whatever"
    }
```
Any tests within the lambda following `shouldCombineTests` will use modified versions of the test functions.
If any of these modified test functions fail, then instead of throwing an `AssertionError`, they will simply add the
error details to an internal list and allow the function to continue.
Then, at the end of the whole block of tests, if there are any errors in the list, a `CombinedAssertionError` will be
thrown &ndash; this includes a formatted message detailing all of the failing tests, along with a list of error results
(named `errors`) that may be checked programmatically.

Not all of the test functions may be nested within `shouldCombineTests`.
The `shouldThrow`, `shouldBeNonNull` and `shouldBeType` functions all return a value, and it is not possible to create a
version that returns a value of the required type following an error.
If these test functions are used with `shouldCombineTests` they will throw an individual `AssertionError` on failure as
usual.

## Dependency Specification

The latest version of the library is 4.2, and it may be obtained from the Maven Central repository.
(The following dependency declarations assume that the library will be included for test purposes; this is
expected to be its principal use.)

### Maven
```xml
    <dependency>
      <groupId>io.kstuff</groupId>
      <artifactId>should-test</artifactId>
      <version>4.2</version>
      <scope>test</scope>
    </dependency>
```
### Gradle
```groovy
    testImplementation 'io.kstuff:should-test:4.2'
```
### Gradle (kts)
```kotlin
    testImplementation("io.kstuff:should-test:4.2")
```

Peter Wall

2024-12-14
