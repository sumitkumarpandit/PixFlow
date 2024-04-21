# PixFlow App Readme

## Description:
This app is designed to load images from URLs obtained by hitting the Unsplash API. It utilizes a custom library for caching and loading images efficiently. The app is built using Jetpack Compose, providing a modern and reactive UI experience.

## I have developed a custom library to load and cache images at run time.
Library: ImageFluxLibrary

## Prerequisites:
Before running the app, ensure you have the following:
- Unsplash API key: You need to obtain an API key from Unsplash to access their API. If you don't have one, you can sign up for free on their website.
- Android Studio: The app is developed for Android devices using Android Studio IDE.

## Installation:
1. Clone the repository to your local machine and checkout master branch.
2. Open the project in Android Studio.
3. Build and run the project on an emulator or physical Android device.

## Usage:
1. Launch the app on your Android device.
2. Upon the first launch, you will be prompted to enter your Unsplash API key.
3. Enter your API key in the provided field and press 'Confirm'.
4. The app will then load images from the Unsplash API and display them in the UI.
5. If Api key is incorrect then it will ask for retry.

## Features:
- Image Loading: The app fetches images from Unsplash URLs and displays them using Jetpack Compose.
- Caching: A custom image caching library is implemented to efficiently store and load images, enhancing performance and reducing data usage.
- API Key Storage: The app securely stores the entered Unsplash API key in SharedPreferences upon first use, eliminating the need to re-enter the key on subsequent launches.
- API Key Prompt: If the stored API key is invalid or missing, the app will prompt the user to enter their Unsplash API key upon launch.
- Reactive UI: Jetpack Compose enables a reactive UI development approach, providing a modern and intuitive user experience.

## License:
This project is licensed under the [MIT License](LICENSE).

## App Build:
https://drive.google.com/file/d/112iZEnyUd5dT2Z5He5YaI_Ua9u1V1eV8/view?usp=sharing

## Screenshots:
[PixFlow_ScreenShots.zip](https://github.com/sumitkumarpandit/PixFlow/files/15052243/PixFlow_ScreenShots.zip)
