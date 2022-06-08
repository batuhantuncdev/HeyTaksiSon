TODO LIST

User
Taxi
Station
Trip
Route
FavouriteRoute

Map => Google Maps
Notification => Event Mechanism
Messages => Event Mechanism, Socket Mechanism
Payment => Sanal POS
Gamification

User and Profile
1. Kullanici Kayit Olma ++
2. Kullanici Girisi ++
3. Kullanici Update, Kullanici Delete ++
4. Kullanici Profil Sayfasi (getUserByEmail methodu) ++. Fotograf ekledigi anda veya degistiridigi anda Firebase Storage.
5. Kullanici Fotograf Ekleme/Silme => Firebase Storage for Profile Pictures (photoName = userId/pp/<filename>.png)

Yolculuk ve Rota 
5. Rota Tablosu, yapilan yolculuk sonrasi rotayi kaydet, listele, favori ekleme ozelligi
6. Favori Rota servisleri Yolculuk servisleri yazilirken entegre edilecek is mantigi olarak.
7. Rotalari Listeleme Ozelligi 
8. Favori Tablosu => Favourites tablosu => userId, Route Id -> Kullanici favori butonuna tikladiginda ekle
**  //TODO: Yolculuk bittiginde rotalara kaydedilecek, baslangic noktasi, bitis noktasi, blob type harita fotografi

Taksi ve Harita

8. Taksileri Listleme Ekrani
getTaxiList, getTaxiByID
9. Taksileri Haritada Gosterme
10. Taksiye Tiklandiginda Taksi Profili Sayfasi ( Sofor ve Taksi)
11. Taksi Cagir Butonuna Basildiginda en yakin Taksiciye bildirim (Notification Model) dusecek / Trip planlanacak ( Ucret hesaplanacak tahmini veya anlasmali)
12. Taksiyi kullanici kendisi de secebilecek eger secmezse en yakin taksici

Durak
14. Durak Listeleme Ekrani
15. Durak Detay Ekrani
16. Ucret Hesaplama Ekrani Genel
17. Harita Ekraninda Canli Rota Takibi / Taksi Cagrildiktan sonra ve yolculuk sirasinda
18. Yolculuk ekrani her iki kullanici icin de gosterilecek, taksici yolculuk baslat ve yolculuk bitir ozelliklerini kullanabilecek
19. Taksi Duragi Arama 

Odeme

19. Odeme Sistemi => Yuzde kazanc / Tip

Gamification

21. 8 seviye rozet / kullanim istatistigine gore achievement

Mesajlasma

21. Mesajlasma Soket Uzerinden veya Firebase Uzerinden
22. Push Notification Gonderme

Backend
StationController ++
TaxiService
TaxiController
NotificationService
NotificationController
PaymentService
PaymentController
FavoriRotaService
FavoriRotaController

Frontend
Map Taxi List
driver availability
find closest taxi and call and send notification
draw route and wait if notification popup is okay
swagger config
