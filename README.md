# Udiners

Udiners is a lightweight Android app for measuring tree height using the two-angle clinometer method. Enter the horizontal distance to a tree and two sighting angles, and the app calculates its height — no climbing, no direct measurement required.

Built for the environmental volunteers of **TREEO Indonesia | PT Solusi Hutan Digital**, and named as a tribute to **Edi Yoga** — better known as **"Udin"** — for his dedication to TREEO Indonesia.

## Table of Contents

- [Features](#features)
- [Formula](#formula)
- [Installation](#installation)
- [Tech Stack](#tech-stack)
- [Building from Source](#building-from-source)
- [Localization](#localization)
- [Credits](#credits)

## Features

| Feature | Description |
|---|---|
| **Tree height calculation** | Computes tree height from distance and two sighting angles using the two-angle clinometer formula. |
| **Visual formula guide** | An in-app popup with an illustrated diagram, the formula breakdown, step-by-step instructions, and accuracy tips. |
| **Bilingual support** | Indonesian and English, switchable manually via the globe icon in the top-left corner. The selection persists automatically. |
| **Consistent, lightweight UI** | Light theme locked regardless of system dark mode, no animations, and optimized for multitasking on low-RAM devices. |
| **Appreciation** | A short tribute to Edi Yoga, with a link to his LinkedIn profile. |

## Formula

```
Tree Height = Distance × (tan(Top Angle) − tan(Bottom Angle))
```

This formula is valid for trees at least 2 meters tall, measured when the observer's eye level is below the base of the tree (both angles are sighted upward). Full details, assumptions, and measurement tips are available in-app via the **"How the Formula Works"** button.

## Installation

### Download the APK

Download the latest APK from the [Releases](../../releases) page and install it directly on your device.

### Build from source

See [Building from Source](#building-from-source) below.

## Tech Stack

- **Language:** Kotlin
- **UI:** Android View Binding, Material Components 3
- **Minimum SDK:** 26 (Android 8.0)
- **Target/Compile SDK:** 34

## Building from Source

```bash
git clone https://github.com/mardiansyah-gunting/udin-converter.git
cd udin-converter
./gradlew assembleDebug
```

The generated APK will be located at `app/build/outputs/apk/debug/app-debug.apk`.

## Localization

The app ships with full Indonesian and English translations. Language can be changed at any time from within the app and does not follow the device's system locale by default.

## Credits

Built by Udiners. Dedicated to **Edi Yoga ("Udin")** in recognition of his contribution to **TREEO Indonesia**.
