# ECZANE OTOMASYONU

**PROJE ÖZETİ:**

Birçok eczanede halen veresiye sistemi kullanılmakta ilaç alıp parası yetmeyen kişiler ilaç ödemesinin bir kısmını yapıp kalanını borç olarak yazdırıyor ve daha sonra bu eczacılar tarafından unutulabiliyor bu da eczane kasasına eksi bakiye olarak yansıyor ve kasada açık meydana geliyor.

Bu soruna çözüm olarak bu sistem geliştirildi, amacımız eczaneye gelip ilacını almış ama parasının tamamını ödeyememiş kişileri sistemde tutmak ve eczanenin kasasındaki açığı azaltmak.

**İŞLEYİŞ:**

Web arayüzünde girilen kullanıcı ve borç bilgileri veritabanına(MongoDB) eklenir. Kullanıcının borç bilgilerini görmesi için yapılan bu uygulamada veriler API ile çekilir ve kullanıcı TC'sini girdikten sonra eğer sistemde kayıtlı ise ad soyad doğrulaması yapması gerekir. Ad soyad doğru ise kişi bilgilerini görebilir. Kullanıcının TC'si sistemde kayıtlı değilse kişi uyarı alır.

**Not: Veriler şuan statik olarak çekilmektedir.**

Projenin backend(NodeJs) tarafına bu [link](https://github.com/fatihgumus59/Eczane-Otomasyonu) üzerinden erişebilirsiniz.

Projenin mobil tasarımına bu [link](https://www.behance.net/gallery/144389355/Eczane-Otomasyonu) üzerinden erişebilirsiniz.

## Kullanılan teknoloji ve kütüphanler
  - Kotlin
  - Retrofit
  - Picasso
  - ViewPager2
  - Fragment
  - RecyclerView
  - ViewBinding

Projede kullanılan illüstrasyonlar [storyset](https://storyset.com/) sitesinden alınmıştır.

![1](https://user-images.githubusercontent.com/84785937/211318632-87827087-f406-4027-bb88-57cfa5b151ab.jpg)
![2](https://user-images.githubusercontent.com/84785937/211318835-bc355469-7f97-4600-b79f-f72915860097.jpg)
![3](https://user-images.githubusercontent.com/84785937/211318903-6a4bcf00-84ab-4854-82ca-dc15c3127cd1.jpg)
![4](https://user-images.githubusercontent.com/84785937/211319043-3cae951c-a848-4f22-bebb-78329b8ae739.jpg)
