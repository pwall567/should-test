# Change Log

The format is based on [Keep a Changelog](http://keepachangelog.com/).

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
