CREATE TABLE `bildirim` (
  `bildirim_ad` varchar(100) NOT NULL,
  `bildirim_tarih` date NOT NULL,
  `bildirim_konu` varchar(100) NOT NULL,
  `bildirim_id` int NOT NULL,
  `bildirim_alan_id` varchar(100) NOT NULL,
  `bildirim_gönderen_id` varchar(100) NOT NULL,
  `bildirim_icerik` varchar(100) NOT NULL,
  `bildirim_sonuc` varchar(100) DEFAULT NULL,
  `bildirim_sure` int NOT NULL,
  PRIMARY KEY (`bildirim_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `durak` (
  `durak_id` int NOT NULL,
  `durak_ad` varchar(100) NOT NULL,
  `durak_adres` varchar(100) NOT NULL,
  `durak_sehir` varchar(100) NOT NULL,
  `durak_ilce` varchar(100) DEFAULT NULL,
  `durak_semt` varchar(100) DEFAULT NULL,
  `durak_tel` varchar(100) NOT NULL,
  `durak_sahibi_ad` varchar(100) NOT NULL,
  `durak_sahibi_soyad` varchar(100) NOT NULL,
  PRIMARY KEY (`durak_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `kullanici` (
  `kullanici_id` int NOT NULL,
  `kullanici_tipi` varchar(100) NOT NULL,
  `kullanici_mail` varchar(100) NOT NULL,
  `kullanici_sifre` varchar(100) NOT NULL,
  `is_online` tinyint NOT NULL,
  `last_login` date DEFAULT NULL,
  `registered_date` date DEFAULT NULL,
  PRIMARY KEY (`kullanici_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `mesaj` (
  `mesaj_konu` text NOT NULL,
  `musteri_id` varchar(100) NOT NULL,
  `mesaj_icerik` varchar(100) NOT NULL,
  `mesaj_alan_id` varchar(100) NOT NULL,
  `mesaj_gönderen_id` varchar(100) NOT NULL,
  `sofor_id` varchar(100) NOT NULL,
  `mesaj_id` int NOT NULL,
  PRIMARY KEY (`mesaj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `musteri` (
  `musteri_id` int NOT NULL,
  `musteri_ad` varchar(100) NOT NULL,
  `musteri_soyad` varchar(100) NOT NULL,
  `musteri_mail` varchar(100) NOT NULL,
  `musteri_tel` int NOT NULL,
  `musteri_sifre` varchar(100) NOT NULL,
  `is_online` tinyint(1) NOT NULL,
  `koordinat_x` point DEFAULT NULL,
  `koordinat_y` point DEFAULT NULL,
  PRIMARY KEY (`musteri_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `odeme` (
  `odeme_tipi` int NOT NULL,
  `odeme_miktari` int NOT NULL,
  `musteri_id` varchar(100) NOT NULL,
  `sofor_id` varchar(100) NOT NULL,
  `yolculuk_id` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `sofor` (
  `sofor_id` int NOT NULL,
  `sofor_ad` varchar(100) NOT NULL,
  `sofor_soyad` varchar(100) NOT NULL,
  `sofor_tel` varchar(100) NOT NULL,
  `sofor_mail` varchar(100) NOT NULL,
  `durak_id` varchar(100) NOT NULL,
  PRIMARY KEY (`sofor_id`),
  UNIQUE KEY `sofor_mail_UNIQUE` (`sofor_mail`),
  UNIQUE KEY `sofor_tel_UNIQUE` (`sofor_tel`),
  KEY `durak_id_fk_idx` (`durak_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `sofor_taksi` (
  `sofor_taksi_id` int NOT NULL,
  `sofor_id` int NOT NULL,
  `taksi_id` int NOT NULL,
  PRIMARY KEY (`sofor_taksi_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `taksi` (
  `taksi_id` int NOT NULL,
  `taksi_plaka` varchar(100) NOT NULL,
  `taksi_model` varchar(100) NOT NULL,
  `taksi_marka` varchar(100) NOT NULL,
  `durak_id` varchar(100) NOT NULL,
  `koordinat_x` point DEFAULT NULL,
  `koordinat_y` point DEFAULT NULL,
  PRIMARY KEY (`taksi_id`),
  KEY `durak_id_fk_idx` (`durak_id`),
  CONSTRAINT `durak_id_fk` FOREIGN KEY (`durak_id`) REFERENCES `durak` (`durak_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `yolculuk` (
  `yolculuk_id` int NOT NULL,
  `musteri_id` varchar(100) NOT NULL,
  `taksi_id` varchar(100) NOT NULL,
  `sofor_id` varchar(100) NOT NULL,
  `bildirim_id` varchar(100) NOT NULL,
  `yolculuk_sure` varchar(100) NOT NULL,
  `yolculuk_ucret` varchar(100) NOT NULL,
  `yolculuk_puan` varchar(100) NOT NULL,
  PRIMARY KEY (`yolculuk_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
