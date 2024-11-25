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
So I tried replacing the `shouldBe` calls with the standard `kotlin.test` functions `expect` and `assertEquals`, and
sure enough, the IDE showed the calls as being in error when the tests used the wrong types.

That shook my confidence in that library, and I went back to using the `kotlin.test` functions, but the idea never
completely went away.
This library is an attempt to provide testing functions with an attractive syntax, but also retaining the strong typing
of the official Kotlin libraries.
And as a bonus, the library is very simple and lightweight &ndash; the main source file consists of fewer than 250 lines
(including comments).

## Dependency Specification

The latest version of the library is 2.0, and it may be obtained from the Maven Central repository.
(The following dependency declarations assume that the library will be included for test purposes; this is
expected to be its principal use.)

### Maven
```xml
    <dependency>
      <groupId>io.kstuff</groupId>
      <artifactId>should-test</artifactId>
      <version>2.0</version>
      <scope>test</scope>
    </dependency>
```
### Gradle
```groovy
    testImplementation 'io.kstuff:should-test:2.0'
```
### Gradle (kts)
```kotlin
    testImplementation("io.kstuff:should-test:2.0")
```

Peter Wall

2024-11-26
