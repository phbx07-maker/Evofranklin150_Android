EVOFRANKLIN 150 - ANDROID APK V1
================================

Tujuan:
- Membungkus dashboard Evofranklin 150 sebagai aplikasi Android.
- URL saat ini: http://3.0.149.255
- Package: com.evofranklin.app
- Minimum Android: Android 8.0 (API 26)

CARA BUILD PALING MUDAH
-----------------------
1. Install Android Studio di PC Windows.
2. Extract ZIP ini.
3. Android Studio -> Open -> pilih folder Evofranklin150_Android.
4. Tunggu Gradle Sync selesai.
5. Build -> Build APK(s).
6. Setelah build selesai, klik "locate" untuk menemukan app-debug.apk.

Untuk APK release:
Build -> Generate Signed App Bundle / APK -> APK
lalu buat keystore Anda sendiri.

CATATAN KEAMANAN
----------------
Versi ini sementara memakai HTTP karena server masih memakai IP tanpa HTTPS.
Manifest mengizinkan cleartext HTTP khusus agar aplikasi bisa membuka server saat ini.

Sebelum distribusi publik/Play Store, sangat disarankan:
- Pasang domain.
- Aktifkan HTTPS/TLS.
- Tambahkan login/authentication.
- Setelah HTTPS aktif, nonaktifkan cleartextTrafficPermitted.

Jika IP/server berubah, edit:
app/src/main/java/com/evofranklin/app/MainActivity.java
baris HOME_URL.
