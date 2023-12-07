# FuzeccCsTv

## Overview

FuzeccCsTv is an Android app designed to consume the [PandaScore API](https://developers.pandascore.io/), providing information on CS:GO matches and team details. The app utilizes various libraries to enhance functionality and maintain clean code.

## Libraries Used

### 1. [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)

Coroutines simplify asynchronous programming in Kotlin, offering a structured approach for managing background tasks without callbacks or thread blocking.

### 2. [Kotlin Serialization](https://github.com/Kotlin/kotlinx.serialization)

Kotlin Serialization facilitates the conversion of Kotlin objects to JSON and vice versa, simplifying data serialization and deserialization for API interactions.

### 3. [Retrofit](https://square.github.io/retrofit/)

Retrofit handles HTTP requests in a type-safe manner, streamlining network operations and providing a clean interface to communicate with the PandaScore API.

### 4. [Android Compose](https://developer.android.com/jetpack/compose)

Android Compose is a modern UI toolkit for native Android applications, replacing traditional XML layouts with a declarative UI approach using Kotlin code.

### 5. [Turbine](https://github.com/cashapp/turbine)

Turbine simplifies testing asynchronous code, offering a concise syntax for writing robust unit tests for coroutines and asynchronous operations.

### 6. [JUnit](https://junit.org/junit5/)

JUnit is a widely-used testing framework for writing and running unit tests, ensuring the correctness of the application's logic.

### 7. [Android Paging](https://developer.android.com/topic/libraries/architecture/paging)

Android Paging efficiently loads and displays large datasets in a RecyclerView, making it suitable for implementing infinite scrolling and optimizing user experience.

### 8. [Core Splashscreen](https://github.com/Kotlin/kotlinx.coroutines)

Core Splashscreen library is used to implement a splash screen, providing a smooth transition before displaying the main content of the app.

### 9. [Robolectric](https://github.com/robolectric/robolectric)

Robolectric is a framework for unit testing Android applications outside an emulator or device, enhancing the speed and reliability of unit tests.

### 10. [Hilt](https://dagger.dev/hilt/)

Hilt is used for dependency injection, simplifying the setup and management of dependencies in the app.

## Architecture

FuzeccCsTv follows the MVVM (Model-View-ViewModel) architecture and employs the repository pattern for clean and maintainable code.

## Getting Started

1. **Clone the repository:**

    ```bash
    git clone https://github.com/GibranLyra/CsTv.git
    ```

2. **Open the project in Android Studio:**

    Open Android Studio and select "Open an existing Android Studio project." Navigate to the directory where you cloned the project.

3. **Build and run the project:**

    Build and run the project with "Run" button.

[Include any additional setup instructions or prerequisites if necessary.]

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
