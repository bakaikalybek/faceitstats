# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project

Android app that surfaces FACEIT player statistics (search players, view career stats and recent match history, drill into per-match scoreboards and per-map performance). Single-module Gradle project (`:app`) — there is no separate library module.

## Build & Run

All commands run from the repo root via the Gradle wrapper.

- Build everything: `./gradlew build`
- Debug APK / install on a connected device: `./gradlew assembleDebug` / `./gradlew installDebug`
- Release APK: `./gradlew assembleRelease` (note: `minifyEnabled false` in `app/build.gradle`)
- JVM unit tests: `./gradlew test` (or `:app:testDebugUnitTest`)
- Instrumented tests (need a device/emulator): `./gradlew connectedAndroidTest`
- Single unit test: `./gradlew :app:testDebugUnitTest --tests "el.professor.faceitstatistics.ExampleUnitTest.addition_isCorrect"`

Toolchain: `compileSdk 33`, `minSdk 29`, `targetSdk 33`, JDK 1.8, Kotlin 1.7.0, Compose compiler ext 1.2.0 (`compose_version` 1.4.1, `koin_version` 3.4.3 set in root `build.gradle`).

## Architecture

Clean-Architecture-style layering inside `app/src/main/java/el/professor/faceitstatistics/`:

- `domain/` — pure Kotlin models (`Player`, `PlayerDetails`, `MatchStatistics`) and the `PlayerRepository` interface. No Android/Retrofit/Room types here.
- `data/` — implementations and IO:
  - `remote/PlayerApi.kt` — Retrofit interface against FACEIT Open Data API (`https://open.faceit.com/data/v4/`). The bearer key and `Constants.game` (`"cs2"`) are sent on every call; changing the game means editing `util/Constants.kt`.
  - `remote/dto/...` — Gson-serialized response DTOs, including a nested `matchDto/` package for match-stats payloads.
  - `local/` — Room: `PlayerDatabase` (v2, `fallbackToDestructiveMigration`), `PlayerDao`, `PlayerEntity`. Used to persist "favorite" players.
  - `mapper/PlayerMapper.kt` — DTO/Entity ↔ domain model conversions. Anything crossing the data/domain boundary must round-trip through here.
  - `repository/PlayerRepositoryImpl.kt` — orchestrates API + DAO, exposes `Flow<Resource<T>>`. `searchPlayer` falls back to the local "favorites" DAO when the query is blank; `getPlayersStatistics` fans out three API calls in parallel via `async` and merges them into a single `PlayerDetails`.
- `presentation/` — one package per screen (`players_list`, `player_details`, `match`, `maps`). Each follows the same MVI-ish split: `*Screen.kt` (Compose), `*State.kt`, `*Events.kt`, `*ViewModel.kt`. Reusable composables live in `presentation/<feature>/composables/`.
- `di/AppModule.kt` — Koin modules: `appModule` (Retrofit `PlayerApi`, Room `PlayerDatabase`), `repositoryModule` (binds `PlayerRepository`), `viewModelModule`. All three are registered in `App.onCreate()`.
- `util/Resource.kt` — `sealed class Resource<T>` with `Loading` / `Success` / `Error`. The repository emits these on a `flow { ... }`; ViewModels collect and copy into their `State`.

## Cross-cutting conventions

- **Navigation is generated.** `MainActivity` hosts `DestinationsNavHost(navGraph = NavGraphs.root)` from [compose-destinations](https://github.com/raamcosta/compose-destinations). `NavGraphs`, typed `Destination`s, and nav-arg parsing are produced by KSP into `build/generated/ksp/<variant>/kotlin/` — if navigation symbols look unresolved, run a build to regenerate them. Annotate screens with `@Destination` and use the generated `*Destination` classes to navigate; do not hand-roll a `NavHost`.
- **DI everywhere.** New ViewModels go in `viewModelModule` and are obtained in Composables via `koinViewModel()`. New singletons go in `appModule`.
- **Repository contract.** All repository methods return `Flow<Resource<T>>` and emit `Loading` first, then `Success`/`Error`. Keep this shape when extending `PlayerRepository` so existing ViewModel collection patterns keep working.
- **Game scope.** `Constants.game` is the single source of truth for which FACEIT title is queried (currently `cs2`). Most API calls default their `game` query param to this constant.
- **Theming.** Material 3 + custom palette/typography in `ui/theme/` (`Theme.kt`, `Color.kt`, `Type.kt`, `CustomFonts.kt`); wrap new screens in `FaceitStatisticsTheme`.
