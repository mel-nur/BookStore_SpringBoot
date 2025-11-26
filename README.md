# BookStore

Basit bir Spring Boot (Thymeleaf) uygulaması — kitap kayıt/listeleme ve "my books" listesi.

**Project Overview**
- **Description:** Thymeleaf temelli küçük bir kitap yönetim örneği.
- **Language / Framework:** Java 17, Spring Boot 3.x, Spring Data JPA, Thymeleaf.

**Prerequisites**
- **Java:** `17` (JDK 17)
- **Maven:** proje kökünde bulunan `mvnw.cmd` ile wrapper kullanılabilir
- **Database:** PostgreSQL (varsayılan) — veya hızlı deneme için H2 (opsiyonel)

**Konfigürasyon (varsayılan)**
- **Dosya:** `src/main/resources/application.properties`
- **Sunucu Portu:** `server.port=1001` → uygulama: `http://localhost:1001`
- **Postgres ayarları (varsayılan):**
  - `spring.datasource.url=jdbc:postgresql://localhost:5432/bookstore_db`
  - `spring.datasource.username=postgres`
  - `spring.datasource.password=12345`

Eğer PostgreSQL yoksa README altındaki "H2 ile hızlı deneme" bölümüne bakın.

**Derleme ve Çalıştırma (Windows PowerShell)**
- Proje köküne gidin:
  - `cd 'c:\Users\cotak_8sfhz1n\IdeaProjects\BookStore'`
- Derleme:
  - `./mvnw.cmd clean package`
- Uygulamayı çalıştırma (development):
  - `./mvnw.cmd spring-boot:run`
- Paketlenmiş jar ile çalıştırma (package sonrası):
  - `java -jar target\BookStore-0.0.1-SNAPSHOT.jar`

**Testleri çalıştırma**
- `./mvnw.cmd test`

**Önemli Endpoints (Thymeleaf view + form tabanlı)**
- **GET:** `/` — Anasayfa (template: `home.html`)
- **GET:** `/book_register` — Yeni kitap formu (template: `book_register.html`)
- **GET:** `/available_books` — Tüm kitaplar sayfası (template: `bookList.html`)
- **POST:** `/save` — Yeni kitap kaydet (form-urlencoded)
  - Body (type: `x-www-form-urlencoded`):
    - `name` (ör. `Küçük Prens`)
    - `author` (ör. `Antoine de Saint-Exupéry`)
    - `price` (ör. `25.00`)
- **GET:** `/mylist/{id}` — Belirtilen kitabı "my books" listesine ekle (redirect `/my_books`)
- **GET:** `/my_books` — Kişisel kitaplar sayfası (template: `myBooks.html`)
- **GET:** `/editBook/{id}` — Kitap düzenleme sayfas (template: `bookEdit.html`)
- **GET:** `/deleteBook/{id}` — Kitap sil (redirect `/available_books`)
- **GET:** `/deleteMyList/{id}` — MyList öğesi sil (redirect `/my_books`)

Not: Controller'lar `@Controller` olarak yazıldıkları için JSON yerine HTML (Thymeleaf) dönerler. Postman ile test ederken HTML yanıtları göreceksiniz.

**Postman ile hızlı test (önerilen)**
- GET tüm kitaplar:
  - Method: `GET`
  - URL: `http://localhost:1001/available_books`
- POST yeni kitap ekleme:
  - Method: `POST`
  - URL: `http://localhost:1001/save`
  - Body -> `x-www-form-urlencoded`
    - `name`: `Deneme Kitabı`
    - `author`: `Yazar Adı`
    - `price`: `10.00`
- GET mylist'e ekle (örnek id=1):
  - Method: `GET`
  - URL: `http://localhost:1001/mylist/1`

**H2 ile hızlı deneme (opsiyonel, eğer Postgres kurmak istemezseniz)**
1. `pom.xml`'e aşağıdaki dependency eklenebilir (`test` veya `runtime` değil, çalışma zamanı için):
```xml
<dependency>
  <groupId>com.h2database</groupId>
  <artifactId>h2</artifactId>
  <scope>runtime</scope>
</dependency>
```
2. `src/main/resources/application.properties` içeriğini geçici olarak şu şekilde değiştirin:
```
spring.datasource.url=jdbc:h2:mem:bookstore_db;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.username=sa
spring.datasource.password=
spring.datasource.driver-class-name=org.h2.Driver
spring.jpa.hibernate.ddl-auto=create-drop
server.port=1001
```
3. Uygulamayı yeniden başlatın; veritabanı bellek üzerinde olacak ve hızlıca test edebilirsiniz.

**Veritabanı tabloları**
- `Book` entity → tablo adı `Books` (alanlar: `id`, `name`, `author`, `price`)
- `MyBookList` entity → tablo adı `MyBookList` (alanlar: `id`, `name`, `author`, `price`)

**Geliştirme Notları / Sık Karşılaşılan Sorunlar**
- Eğer uygulama başlarken DB bağlantı hatası veriyorsa, `application.properties` içindeki PostgreSQL bilgilerini ve PostgreSQL servisinin çalıştığını doğrulayın.
- Port çakışması durumunda `server.port`'u değiştirin.
- Form gönderirken `Content-Type: application/x-www-form-urlencoded` kullandığınızdan emin olun (`raw` JSON POST, controller tarafından doğrudan `@ModelAttribute` ile bağlanmayabilir).

**İleri adımlar (isteğe bağlı)**
- REST API versiyonu oluşturmak için controller'ları `@RestController` olarak yeniden yazıp JSON dönecek endpoint'ler ekleyin.
- DTO kullanarak entity'leri dışa/İçe dönüştürün.

---
Oluşturan: Proje yardımcı README (otomatik)

