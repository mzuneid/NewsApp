# ​ NewsApp

A modern Android application using **Clean Architecture**, **MVVM**, and **Jetpack Compose** to deliver the latest news in a beautiful and responsive UI.

---

##  Features

- **Breaking News Feed** – Browse current headlines from multiple sources  
- **Search** – Find articles by keywords  
- **Category Filters** – Explore topics like Business, Tech, Sports, Entertainment  
- **Offline Caching** – Read previously loaded articles even when offline  
- **Infinite Scrolling** – Seamless pagination with smooth load transitions  
- **Bookmarking** – Save articles to read later  
- **Adaptive UI** – Supports both light and dark themes  

---

##  Tech Stack & Architecture

| Layer        | Tools & Libraries           |
|--------------|-----------------------------|
| UI           | Jetpack Compose, Navigation |
| Architecture | MVVM + Clean Architecture   |
| Networking   | Retrofit + OkHttp           |
| Local Cache  | Room + Coroutines + Flow    |
| DI           | Hilt                        |
| Async        | Kotlin Coroutines & Flow    |
| Images       | Coil                        |
| Testing      | JUnit5, MockK, Compose Test |

---

##  Repo Structure

NewsApp/
├── app/ # App module (Compose screens, ViewModels)
├── data/ # Repositories, API (Retrofit), Room DB
├── domain/ # Use cases, models, repository interfaces
├── di/ # Hilt modules
└── utils/ # Constants, extensions, helpers

Add your News API key (e.g., from NewsAPI.org) to local.properties:

NEWS_API_KEY=your_api_key_here


