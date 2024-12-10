# Foodezy

**Foodezy** is a user-friendly mobile application designed to make cooking easier and more enjoyable. With Foodezy, you can explore various meal options, learn how to cook them through detailed recipes and instructional videos, and save your favorite meals for offline access.

---

## Features

### 🌟 **Discover Recipes**
- Explore a wide range of recipes from different cuisines.
- View detailed meal descriptions, ingredients, and nutritional information.

### 🎥 **Video Tutorials**
- Watch step-by-step video guides to learn how to prepare meals.

### 💾 **Save Favorites**
- Save your favorite recipes to access them anytime, even offline.


---

## Libraries and Technologies Used

### 1. **Navigation Component**
- Facilitates the creation of a single-activity app with multiple fragments.
- Enhances navigation between different parts of the app seamlessly.

### 2. **Retrofit**
- Handles HTTP connections to fetch meal data from the REST API.
- Converts meal JSON responses into Kotlin/Java objects for easy manipulation.

### 3. **Room**
- Serves as the local database to store meal details for offline access.
- Efficiently manages data persistence and retrieval.

### 4. **MVVM & LiveData**
- Implements the **Model-View-ViewModel (MVVM)** architecture to separate UI logic from business logic.
- Ensures that the UI state remains consistent across screen configuration changes.

### 5. **Coroutines**
- Simplifies background operations such as network requests or database interactions.
- Provides smooth and responsive user experiences by managing asynchronous tasks.

### 6. **View Binding**
- Automatically generates bindings to XML layouts, eliminating the need for `findViewById`.
- Increases safety and reduces boilerplate code when interacting with views.

### 7. **Glide**
- Loads images efficiently into `ImageView` components.
- Handles image caching, improving performance and reducing bandwidth usage.

---

## Getting Started

### Prerequisites
- Android Studio Arctic Fox or newer.
- Minimum SDK Version: 21 (Android 5.0 Lollipop).
- API Key for the meal database API (if required).

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/saranshs17/foodezy.git
