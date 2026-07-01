# FitTrack — Jetpack Compose Fitness Tracker

A 100% Jetpack Compose (Material 3), pure-MVVM fitness tracker built for a final exam project. No XML layouts, no `findViewById` — a single Compose Activity hosts a Bottom-Navigation-driven app with Dashboard, Workouts, and History tabs, backed by a local Room database.

## 1. App Concept

FitTrack is a fully offline, single-user fitness tracker with three tabs behind a Bottom Navigation Bar:

- **Dashboard** — the "wow" landing screen: a hand-drawn, animated circular **Goal Ring** (Canvas `drawArc`, animated with `animateFloatAsState`) showing progress toward a weekly workout goal, plus quick summary stats (total sessions, current streak).
- **Workouts** — a form screen to log a new workout session (exercise name, sets, reps, weight), which persists to Room and immediately feeds the Dashboard ring and History list.
- **History** — a `LazyColumn` of all past workout sessions (most recent first), each row showing exercise/sets/reps/weight/timestamp with a delete action and tap-to-edit (routes back into the Workouts form, pre-filled).

All three screens share one Room database, one `AppContainer` (manual DI — no Hilt), and StateFlow-based ViewModels.

## 2. Package Structure

```
com.example.final_exam_project/
├── FitTrackApplication.kt          # Application subclass; owns the single AppContainer instance
├── MainActivity.kt                 # ComponentActivity; setContent { FitTrackTheme { FitTrackNavHost(...) } }
│
├── data/
│   ├── local/
│   │   ├── WorkoutSession.kt       # @Entity: id, exerciseName, sets, reps, weight, timestamp
│   │   ├── WorkoutDao.kt           # @Dao: insert/update/delete/getAll(Flow)/getById/getCountSince
│   │   └── FitTrackDatabase.kt     # @Database, exposes workoutDao(), singleton getInstance()
│   └── repository/
│       └── WorkoutRepository.kt    # Wraps WorkoutDao; Flow + suspend CRUD
│
├── di/
│   ├── AppContainer.kt             # Interface + DefaultAppContainer(context): builds DB, Repository
│   └── ViewModelFactory.kt         # ViewModelProvider.Factory, built from an AppContainer
│
├── navigation/
│   ├── BottomNavItem.kt            # Sealed class: Dashboard, Workouts, History (route, label, icon)
│   ├── NavGraph.kt                 # NavHost wiring for each screen + edit-by-id Workouts route
│   └── FitTrackNavHost.kt          # Scaffold with bottomBar { NavigationBar } + the NavGraph
│
├── ui/
│   ├── theme/
│   │   ├── Color.kt                # Material 3 color tokens
│   │   ├── Theme.kt                # FitTrackTheme composable (light/dark MaterialTheme wrapper)
│   │   └── Type.kt                 # Typography
│   │
│   ├── components/
│   │   ├── GoalRing.kt             # Canvas-based animated ring — the "wow" feature
│   │   ├── StatCard.kt             # Reusable summary-stat card (label + value)
│   │   ├── WorkoutListItem.kt      # Single row composable for the History LazyColumn
│   │   └── EmptyState.kt           # Reusable "no data yet" placeholder
│   │
│   └── screens/
│       ├── dashboard/
│       │   ├── DashboardScreen.kt      # Renders GoalRing + StatCards
│       │   ├── DashboardViewModel.kt   # Exposes StateFlow<DashboardUiState>
│       │   └── DashboardUiState.kt     # goalProgress, sessionsThisWeek, totalSessions, streak
│       ├── workouts/
│       │   ├── WorkoutsScreen.kt       # Form fields + Save button
│       │   ├── WorkoutsViewModel.kt    # Exposes StateFlow<WorkoutFormUiState>; save()/loadForEdit()
│       │   └── WorkoutFormUiState.kt   # Form fields as text + inline validation errors
│       └── history/
│           ├── HistoryScreen.kt        # LazyColumn of WorkoutListItem
│           ├── HistoryViewModel.kt     # Exposes StateFlow<HistoryUiState>
│           └── HistoryUiState.kt       # sessions: List<WorkoutSession>, isLoading
│
└── util/
    └── DateFormatter.kt             # timestamp -> display string helper
```

## 3. Database Schema

### `WorkoutSession` entity

| Column       | Type      | Notes                              |
|--------------|-----------|-------------------------------------|
| `id`         | `Long`    | `@PrimaryKey(autoGenerate = true)`  |
| `exerciseName` | `String` |                                    |
| `sets`       | `Int`     |                                      |
| `reps`       | `Int`     |                                      |
| `weight`     | `Double`  | kg                                   |
| `timestamp`  | `Long`    | epoch millis, defaults to `System.currentTimeMillis()` |

### `WorkoutDao`

```kotlin
@Insert(onConflict = OnConflictStrategy.REPLACE)
suspend fun insert(session: WorkoutSession): Long

@Update
suspend fun update(session: WorkoutSession)

@Delete
suspend fun delete(session: WorkoutSession)

@Query("DELETE FROM workout_sessions WHERE id = :id")
suspend fun deleteById(id: Long)

@Query("SELECT * FROM workout_sessions ORDER BY timestamp DESC")
fun getAll(): Flow<List<WorkoutSession>>

@Query("SELECT * FROM workout_sessions WHERE id = :id")
suspend fun getById(id: Long): WorkoutSession?

@Query("SELECT COUNT(*) FROM workout_sessions WHERE timestamp >= :sinceEpochMillis")
fun getCountSince(sinceEpochMillis: Long): Flow<Int>
```

`getCountSince` backs the Dashboard's weekly-goal-ring computation.

### `FitTrackDatabase`

Single `@Database(entities = [WorkoutSession::class], version = 1)`, exposing `workoutDao()`, built via a thread-safe singleton `getInstance(context)`.

## 4. Step-by-Step Implementation Plan / Milestones

- [ ] **Milestone 0 — Verify toolchain (flagged risk).** This project declares **no explicit Kotlin Gradle plugin** (`build.gradle.kts` only applies `com.android.application` at AGP 9.1.1) — it likely relies on AGP 9's built-in/embedded Kotlin compilation rather than the classic `org.jetbrains.kotlin.android` plugin. Before writing any Gradle config, check current AGP 9.1.1 docs for: how the Compose compiler plugin is wired without a separate Kotlin plugin, and how KSP (needed for Room, Milestone 2) aligns with that setup. Do not assume the older `composeOptions.kotlinCompilerExtensionVersion` recipe applies unchanged.
- [ ] **Milestone 1 — Compose setup.** Add Compose BOM, `activity-compose`, `material3` to the version catalog and `app/build.gradle.kts`; enable `buildFeatures.compose = true`. ~~Remove `res/layout/activity_main.xml`~~ (done). Confirm a bare Compose screen builds and runs on a device/emulator.
- [ ] **Milestone 2 — Room data layer.** Add `room-runtime`, `room-ktx`, `room-compiler` via KSP. Flesh out `WorkoutSession`/`WorkoutDao`/`FitTrackDatabase`/`WorkoutRepository` (stubs already scaffolded) into fully working code. Verify compilation of the annotation-processed Room classes.
- [ ] **Milestone 3 — Manual DI + ViewModel wiring.** Confirm `AppContainer`/`DefaultAppContainer`, `FitTrackApplication` (registered in the manifest), and `ViewModelFactory` (stubs scaffolded) instantiate ViewModels correctly end-to-end.
- [ ] **Milestone 4 — Navigation + Bottom Bar shell.** Add `navigation-compose`. Verify tapping each of the three bottom tabs (Dashboard/Workouts/History, scaffolded) switches screens correctly with proper back-stack behavior (`popUpTo` + `launchSingleTop` + `restoreState`, already wired in `FitTrackNavHost`).
- [ ] **Milestone 5 — Workouts + History screens.** Wire up real field-update handlers, validation, and `save()`/`loadForEdit(id)` in `WorkoutsViewModel`. Wire `HistoryViewModel` to collect `repository.allSessions` and implement `delete()`. Smoke test: log a workout, see it in History, delete it, re-add and edit it.
- [ ] **Milestone 6 — Dashboard + animated Goal Ring.** Implement the real `drawArc` background track + animated progress arc in `GoalRing.kt` using `animateFloatAsState`. Compute `goalProgress` in `DashboardViewModel` from `repository.countSince(startOfWeekEpochMillis)` against a weekly goal target. Confirm the ring animates smoothly and updates live as new workouts are logged.
- [ ] **Milestone 7 — Polish & theming.** Finalize `Color.kt`/`Theme.kt`/`Type.kt` (real light/dark M3 scheme, optional dynamic color on Android 12+). Add empty-state visuals, edge-to-edge insets handling, app icon/name. Final smoke test across all three tabs in light and dark mode, including persistence across process death.

### Current state

The package scaffold above (folders + stub Kotlin files) has been created, along with this README. Stub files compile-shape-wise but contain `TODO()`/placeholder bodies and **no new Gradle dependencies have been added yet** — the project will not build cleanly until Milestone 1's dependency work begins. Code for each milestone will be written sequentially in upcoming sessions, in order, starting from Milestone 0/1.
