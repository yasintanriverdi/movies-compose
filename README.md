# Movie List Compose Sample App

Playground app to get familiar with Jetpack Compose (inc. Navigation & Paging)

<img src="https://github.com/yasintanriverdi/movies-compose/blob/main/images/home.png" width="200" height="400"> <img src="https://github.com/yasintanriverdi/movies-compose/blob/main/images/detail.png" width="200" height="400"> <img src="https://github.com/yasintanriverdi/movies-compose/blob/main/images/about.png" width="200" height="400">

## Environment setup

You need to get API Key from https://www.themoviedb.org/ and set it to `local.properties` file as `TMDB_API_KEY`

```properties
TMDB_API_KEY = <put your API key>
```

## Tech-stack

Main dependencies used in this project:

* [Jetpack Compose](https://developer.android.com/jetpack/compose)
* [Coil for Jetpack Compose](https://github.com/chrisbanes/accompanist/tree/main/coil)
* [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)
* [Dagger-Hilt](https://dagger.dev/hilt/)
* [Retrofit](https://github.com/square/retrofit)
* [Moshi](https://github.com/square/moshi)
* [Paging](https://developer.android.com/topic/libraries/architecture/paging)