# News
[![Kotlin Version](https://img.shields.io/badge/kotlin-1.5.20-blue.svg)](http://kotlinlang.org/)

A news listing app built with Android Jetpack.

Sample app which uses news api to get latest news with search q for Keywords or phrases to search for in the article title and body and pagination for 20 articles and built to illustrate best development practices with Android Jetpack.

Libraries Used
---------------

* [Room][1] - Access your app's SQLite database with in-app objects and compile-time checks.
* [LiveData][2] - Build data objects that notify views when the underlying database changes.
* [Paging library][3] - Load and display small chunks of data at a time.
* [ViewModel][4] - Store UI-related data that isn't destroyed on app rotations. Easily schedule
     asynchronous tasks for optimal execution.
* [Glide][5] for image loading
* [Retrofit][6] Type-safe HTTP client for Android and Java
* [Coroutines][8] asynchronous tasks
* [Hilt][9] Dependency Injection
* [Navigation with safe args][10] navigate across, into, and back out from the different pieces of content within your app

[1]: https://developer.android.com/topic/libraries/architecture/room
[2]: https://developer.android.com/topic/libraries/architecture/livedata
[3]: https://developer.android.com/topic/libraries/architecture/paging
[4]: https://developer.android.com/topic/libraries/architecture/viewmodel
[5]: https://bumptech.github.io/glide/
[6]: https://square.github.io/retrofit/
[8]: https://developer.android.com/kotlin/coroutines?gclid=CjwKCAjwuIWHBhBDEiwACXQYsbqUNwKkURaqBghm_xe328J9HbaAJr-VpKnUCJHvdkLwGDhucYh2_hoCSZkQAvD_BwE&gclsrc=aw.ds
[9]: https://developer.android.com/training/dependency-injection/hilt-android
[10]: https://developer.android.com/guide/navigation/navigation-pass-data

Android Studio IDE setup
------------------------
* Use the latest version of Android Studio.
* **Add your [News API][13] Key in `build.gradle` file to make news fetch work.**

[13]: https://newsapi.org/

## Architecture
news is based on MVVM with clean architecture.

## Unit Testing Frameworks
Unit Tests verify the interactions of viewmodels between repositories and dao & REST api requests.
- [Robolectric](https://github.com/robolectric/robolectric) - Robolectric is the industry-standard unit testing framework for Android.
- [Mockito-Kotlin](https://github.com/nhaarman/mockito-kotlin) - a small library that provides helper functions to work with Mockito in Kotlin.
- [Espresso](https://developer.android.com/training/testing/espresso) -  library that provides helper functions to test Ui.



# Screenshots

<img src="https://github.com/gehadfatah/News/raw/main/screenshots/news.jpg" alt="alt text" width="300">

<img src="https://github.com/gehadfatah/News/raw/main/screenshots/detail.jpg" alt="alt text" width="300">

