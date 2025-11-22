# CampusConnect

An Android application for college students to connect, share information, and stay updated with campus events.

## Features

*   **Community Feed:** Share posts, images, and updates with other students.
*   **Digital Notice Board:** View official notices and announcements from the college administration.
*   **Lost & Found:** A dedicated section to post about items you have lost or found on campus.

## Tech Stack

*   **Language:** Java
*   **UI:** Android SDK with Material Components
*   **Backend:** Supabase (for database, real-time features, and authentication)
*   **Networking:** Retrofit
*   **JSON Parsing:** Gson

## Setup

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/Unknownhero88/campusconnect.git
    ```
2.  **Open in Android Studio:**
    - Open Android Studio.
    - Click on `Open` and navigate to the cloned project directory.
3.  **Build and Run:**
    - Let Android Studio sync the Gradle files.
    - Click on the `Run 'app'` button to build and run the application on an emulator or a physical device.

## Project Structure

```
.
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── example/
│   │   │   │           └── myapplication/
│   │   │   │               ├── Attendance/
│   │   │   │               ├── Community/
│   │   │   │               ├── DigitalNoticeBoard/
│   │   │   │               ├── Home/
│   │   │   │               ├── LostANDFound/
│   │   │   │               ├── StudyMaterial/
│   │   │   │               └── MainActivity.java
│   │   │   ├── res/
│   │   │   │   ├── drawable/
│   │   │   │   ├── layout/
│   │   │   │   ├── menu/
│   │   │   │   ├── mipmap-.../ (launcher icons)
│   │   │   │   ├── values/
│   │   │   │   └── xml/
│   │   │   └── AndroidManifest.xml
│   │   ├── androidTest/
│   │   └── test/
│   └── build.gradle.kts
├── gradle/
├── .gitignore
├── build.gradle.kts
├── gradlew
├── gradlew.bat
├── README.md
└── settings.gradle.kts
```

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

