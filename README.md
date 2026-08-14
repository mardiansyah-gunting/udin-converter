# Udiners 🌳

**Udiners** adalah aplikasi Android sederhana untuk mengukur tinggi pohon menggunakan metode clinometer dua sudut — cukup masukkan jarak dan dua sudut bidikan, tanpa perlu memanjat atau mengukur langsung.

Aplikasi ini dibuat untuk komunitas relawan lingkungan **TREEO Indonesia | PT Solusi Hutan Digital**, dan dinamai untuk menghormati dedikasi **Edi Yoga** — atau yang lebih dikenal sebagai **"Udin"** — di TREEO Indonesia.

## ✨ Fitur

- **Hitung tinggi pohon** dari jarak datar + sudut atas + sudut bawah, memakai rumus clinometer dua sudut.
- **Ilustrasi cara kerja rumus** — diagram visual, rumus, langkah-langkah, dan tips pengukuran yang akurat, ditampilkan dalam popup.
- **Dua bahasa** — Bahasa Indonesia & English, bisa dipilih manual lewat ikon 🌐 di pojok kiri atas (tersimpan otomatis, tidak perlu diatur ulang tiap buka aplikasi).
- **Tampilan konsisten** — tema terang dikunci (tidak ikut dark mode sistem), tanpa animasi, dan ringan untuk multitasking di HP dengan RAM terbatas.
- **Apresiasi** — cerita singkat kenapa aplikasi ini dinamai "Udiners", plus tautan ke profil LinkedIn Edi Yoga.

## 📐 Rumus

```
Tinggi Pohon = Jarak × (tan(Sudut Atas) − tan(Sudut Bawah))
```

Berlaku untuk pohon dengan tinggi minimal 2 meter, saat posisi mata pengamat lebih rendah dari pangkal pohon (kedua sudut dibidik ke atas). Detail dan tips lengkap ada di dalam aplikasi (tombol **"Cara Kerja Rumus"**).

## 🛠️ Tech Stack

- Kotlin + Android View Binding
- Material Components 3 (Material You)
- Target: `minSdk 26`, `compileSdk 34`

## 🚀 Build dari Source

```bash
git clone https://github.com/mardiansyah-gunting/udin-converter.git
cd udin-converter
./gradlew assembleDebug
```

APK hasil build ada di `app/build/outputs/apk/debug/app-debug.apk`.

## 📥 Download

Sudah tidak mau build sendiri? Ambil APK siap pakai di halaman **[Releases](../../releases)**.

## 🙏 Kredit

Dibuat oleh Udiners. Dedikasi khusus untuk **Edi Yoga (Udin)** atas kontribusinya di **TREEO Indonesia**.
