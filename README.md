# WeatherApp 🌦️

This is a simple Weather Application that uses the OpenWeatherMap API to fetch and display weather data. The app is designed to provide users with up-to-date weather information based on their location or a searched city.

## Features

- 🌍 **Location-Based Weather:** Automatically fetches the current weather for your location.
- 🔍 **Search Functionality:** Allows users to search for weather details in specific cities.
- 📈 **Real-Time Updates:** Displays current temperature, weather conditions, and more.
- 🖼️ **Dynamic UI:** Updates the user interface based on weather conditions (e.g., sunny, cloudy, rainy).

## Technologies Used

- **Programming Language:** Kotlin
- **API Integration:** OpenWeatherMap API
- **UI Framework:** Android SDK
- **Network Library:** Retrofit for API calls
- **JSON Parsing:** Gson 
- **Asynchronous Processing:** Coroutines for smooth and responsive UI

## Installation and Setup

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/RamNikhilRN/WeatherApp-OpenWeatherMapAPI.git


Open the Project:

Use Android Studio to open the project directory.
Add Your API Key:

Obtain an API key from OpenWeatherMap.
Add the API key in the local.properties file or directly in the code where applicable:
properties
Copy code
WEATHER_API_KEY=your_api_key_here
Build and Run the App:

Connect your Android device or use an emulator.
Build and run the app from Android Studio.
Usage
Launch the App:

Upon launch, the app requests location permissions to fetch weather data for your current location.
Search for a City:

Use the search bar to enter the name of a city and view its weather information.
View Weather Details:

See real-time data including:
Current temperature
Weather description (e.g., cloudy, sunny)
Humidity
Wind speed
Screenshots

Future Improvements
🌟 Add a weekly forecast feature.
🌟 Implement caching for offline access.
🌟 Enhance the UI with animations and transitions.
🌟 Add support for multiple languages.
Contributing
Contributions are welcome! Feel free to open an issue or submit a pull request with your improvements or bug fixes.
