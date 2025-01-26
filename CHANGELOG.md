# Change Log

The format is based on [Keep a Changelog](http://keepachangelog.com/).

## [4.4] - 2025-01-26
### Changed
- `pom.xml`: updated Kotlin version to 2.0.21
- `GenericTests`, `CombinedTests`: fixed incorrect use of contract
- `ErrorMessages`: new messages for above fixes

## [4.3] - 2024-12-15
### Changed
- `ArrayTests`: added `shouldBeEqual` and `shouldNotBeEqual` comparisons on arrays
- `ArrayTests`: added `shouldContain` and `shouldNotContain` tests on arrays
- `ErrorMessages`: added formatted output of floats

## [4.2] - 2024-12-14
### Added
- `ArrayTests`: comparisons on arrays
### Changed
- `ErrorMessages`: added formatted output of arrays

## [4.1] - 2024-12-14
### Changed
- `GenericTests`: added `shouldBeEqual` and `shouldNotBeEqual`

## [4.0] - 2024-12-10
### Added
- `ErrorMessages`: split messages out into separate object (so they can be shared)
- `NamedPredicate`: replaces `StateCheck` _etc._
- `ComparablePredicate`: tests on `Comparable` values
- `CombinedTests`, `CombinedAssertionError`: combined tests
### Changed
- `GenericTests`, `StringTests`, `CollectionTests`: moved message formatting to `ErrorMessages`
### Removed
- `StateCheck`, `ComparableStateCheck`: replaced by `NamedPredicate`

## [3.0] - 2024-11-28
### Changed
- `GenericTests`, `CollectionTests`: use `@InlineOnly` and contracts
- `GenericTests`: sanitize string output

## [2.2] - 2024-11-27
### Added
- `StateCheck`, `ComparableStateCheck`: expanded test mechanism
### Changed
- `GenericTests`: use `StateCheck`
- `pom.xml`: tweak deployment configuration

## [2.1] - 2024-11-26
### Added
- `CollectionTests`, `StringTests`: split out from `TestFunctions`
### Changed
- `TestFunctions`: renamed to `GenericTests`
- `GenericTests`: added `shouldBeOneOf` and `shouldNotBeOneOf`
- `GenericTests`: added `shouldThrow` with message

## [2.0] - 2024-11-26
### Changed
- `TestFunctions`: extensive enhancements

## [1.0] - 2024-11-24
### Added
- all files: initial versions
