#  Todo List App (Refactoring & Database Integration)

Proyek ini adalah hasil pengembangan dari aplikasi desktop "Todo List" berbasis Java Swing. Fokus utama pengembangan adalah memigrasikan sistem penyimpanan data dari memori sementara (RAM) ke dalam *database* relasional secara permanen dan merestrukturisasi kode menggunakan arsitektur MVC (Model-View-Controller).

## Deskripsi Fungsionalitas
Aplikasi ini memungkinkan pengguna untuk mengelola jadwal atau tugas sehari-hari. Fitur utama yang dimiliki program ini meliputi:
1. **Create (Tambah Tugas):** Menyimpan daftar tugas baru beserta statusnya ke dalam database.
2. **Read (Tampilkan Tugas):** Mengambil seluruh riwayat tugas dari database dan menampilkannya secara *real-time* di dalam antarmuka tabel.
3. **Update (Perbarui Tugas):** Mengubah judul atau status tugas yang sudah ada. Dilengkapi dengan fitur *auto-fill*, di mana saat pengguna mengklik baris pada tabel, data akan langsung dimuat secara otomatis ke dalam kotak formulir input untuk memudahkan pengeditan.
4. **Delete (Hapus Tugas):** Menghapus data spesifik dari dalam sistem dan database secara permanen.
5. **Clear:** Membersihkan kotak formulir (teks & pilihan status) untuk memasukkan tugas lain.

## Perubahan Kode & Arsitektur
Aplikasi ini sebelumnya menggunakan `FakeTodoRepository` di mana data akan hilang ketika program ditutup. Untuk memenuhi kebutuhan penyimpanan permanen dan perbaikan struktur tanpa memodifikasi antarmuka bawaan (`TodoView.java`), implementasi berikut diterapkan:

* **Lapisan Model (Data):** Pembuatan file `RealTodoRepository.java` yang mengimplementasikan `TodoRepository`. File ini berisi query SQL (JDBC) untuk melakukan proses *Insert, Select, Update*, dan *Delete* secara langsung ke database MySQL. File `DBConnection.java` ditambahkan khusus untuk mengelola akses *host, user,* dan *database*.
* **Lapisan Controller (Logika):**
  Logika program yang awalnya menumpuk di berkas utama (`Latres.java`) dipisahkan sepenuhnya ke dalam file `DBController.java`. Berkas ini bertindak sebagai perantara yang mendengarkan *event trigger* dari UI (klik tombol & seleksi baris tabel) dan menginstruksikan `RealTodoRepository` untuk memproses data terkait.
* **Lapisan View & Entry Point:**
  Struktur `Latres.java` direduksi murni menjadi *Entry Point* (titik awal) untuk melakukan instansiasi View, Model, dan Controller. Kode GUI bawaan (`TodoView.java`) dijaga *originalitasnya* dengan memanfaatkan antarmuka *public method* yang ada agar tidak menyalahi larangan modifikasi aturan desain sistem (*Open/Closed Principle*).

## Konfigurasi Prasyarat
Untuk menjalankan aplikasi ini, konfigurasi yang diperlukan adalah:
1. Menambahkan ekstensi `mysql-connector-j` ke dalam manajemen dependency (`pom.xml` untuk Maven atau `build.gradle` untuk Gradle).
2. Memastikan server lokal (contoh: XAMPP/MySQL) dalam keadaan aktif.
3. Melakukan Import dengan databsae yang sudah disediakan
