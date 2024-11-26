# should-test

[![Build Status](https://github.com/pwall567/should-test/actions/workflows/build.yml/badge.svg)](https://github.com/pwall567/should-test/actions/workflows/build.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Kotlin](https://img.shields.io/static/v1?label=Kotlin&message=v1.9.24&color=7f52ff&logo=kotlin&logoColor=7f52ff)](https://github.com/JetBrains/kotlin/releases/tag/v1.9.24)
[![Maven Central](https://img.shields.io/maven-central/v/io.kstuff/should-test?label=Maven%20Central)](https://search.maven.org/search?q=g:%22io.kstuff%22%20AND%20a:%22should-test%22)

Kotlin testing functions

## Background

A while ago, someone introduced me to a testing library that used the syntax:
```kotlin
    testValue shouldBe expectedvalue
```
This seemed like a great idea, and good use of the capabilities of Kotlin.

But then I encountered a problem where my test result was not just the wrong value, but the wrong type, and the
`shouldBe` function call had compiled without error.
The function had been written in a way that didn't allow the Kotlin compiler (or the IDE) to perform strong
type-checking on the arguments.

I tried replacing the `shouldBe` calls with the standard `kotlin.test` functions `expect` and `assertEquals`, and sure
enough, the IDE showed the calls as being in error when the tests used the wrong types.
That shook my confidence in that other library, and I went back to using the `kotlin.test` functions, but the idea never
completely went away.

This library is an attempt to provide testing functions with an attractive syntax, but also retaining the strong typing
of the official Kotlin libraries.
And as a bonus, the library is very simple and lightweight, and like the `kotlin.test` functions, it works equally well
with JUnit 4 and 5.

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

And nullable values may be tested for `null`:
```kotlin
   nullableValue shouldBe null
```

### `shouldNotBe`

The complement to `shouldBe` performs a simple inequality test:
```kotlin
    testValue shouldNotBe wrongValue
```
Like `shouldBe`, it expects both sides of the comparison to be the same type, and it may be applied to boolean and
nullable values.

### `shouldBe` with `Boolean` lambda

A variation on `shouldBe` takes a lambda returning a `Boolean`:
```kotlin
    stringValue shouldBe { isNotEmpty() }
```

### `shouldNotBe` with `Boolean` lambda

The complement of `shouldBe` with a lambda:
```kotlin
    stringValue shouldNotBe { isEmpty() }
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

Tests whether a value is of a specified type:
```kotlin
    testValue.shouldBeNonNull()
```
Because this doesn't have a parameter value, the infix notation isn&rsquo;t available, but it has the advantage that it
returns the non-null value which can then be used in a subsequent test:
```kotlin
    nullableString.shouldBeNonNull().length shouldBe 10
```

### `shouldBeType`

Tests whether a value is of a specified type:
```kotlin
    testValue.shouldBeType<String>()
```
Like `shouldBeNonNull`, this function doesn&rsquo;t have access to the infix notation, but also like `shouldBeNonNull`,
it returns a type-checked value that may be used in further tests:
```kotlin
    testValue.shouldBeType<String>().length shouldBe 8
```

## Collection Tests

### `shouldContain`

Tests that a collection (an `Iterable`,  which may be a `List`, `Set` _etc._) contains a given value:
```kotlin
    testList shouldContain "alpha"
```

### `shouldNotContain`

It should be obvious by now what this function does.

### `shouldContainKey`

Tests that a `Map` contains a given key:
```kotlin
    testMap shouldContainKey "first"
```

### `shouldNotContainKey`

Also obvious.

## String Tests

### `shouldContain`

Tests that a string contains the given substring.

### `shouldNotContain`

The complement of `shouldContain`.

### `shouldStartWith`

Tests that a string starts with the given prefix.

### `shouldNotStartWith`

The complement of `shouldStartWith`.

### `shouldEndWith`

Tests that a string ends with the given suffix.

### `shouldNotEndWith`

The complement of `shouldEndWith`.

### `shouldMatch`

Tests that a string matches a given `Regex`.

### `shouldNotMatch`

The complement of `shouldMatch`.

## Exception Tests

### `shouldThrow`

Tests whether a block of code results in a nominated exception type being thrown:
```kotlin
    shouldThrow<NumberFormatException> {
        numericString.toInt()
    }
```
The function returns the exception (`Throwable`) instance, which may then be the subject of further tests, but if the
only such test is a comparison on the message, a convenience function includes that comparison:
```kotlin
    shouldThrow<NumberFormatException>("For input string: \"$numericString\"") {
        numericString.toInt()
    }
```

## Dependency Specification

The latest version of the library is 2.2, and it may be obtained from the Maven Central repository.
(The following dependency declarations assume that the library will be included for test purposes; this is
expected to be its principal use.)

### Maven
```xml
    <dependency>
      <groupId>io.kstuff</groupId>
      <artifactId>should-test</artifactId>
      <version>2.2</version>
      <scope>test</scope>
    </dependency>
```
### Gradle
```groovy
    testImplementation 'io.kstuff:should-test:2.2'
```
### Gradle (kts)
```kotlin
    testImplementation("io.kstuff:should-test:2.2")
```

Peter Wall

2024-11-27
