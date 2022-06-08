const { Sequelize, Model, DataTypes } = require("sequelize");

module.exports = (sequelize, Sequelize) => {
  const Durak = sequelize.define("YENI", {
    durakId: {
      type: DataTypes.INTEGER,
      field: 'durak_id', 
      primaryKey: true
    },
    durakAd: {
      type: DataTypes.TEXT,
      field: 'durak_ad'
    },
    durakAdres: {
      type: DataTypes.TEXT,
      field: 'durak_adres', 
    },
    durakSehir: {
      type: DataTypes.TEXT,
      field: 'durak_sehir', 
    },
    durakIlce: {
      type: DataTypes.TEXT,
      field: 'durak_ilce', 
    },
    durakTel: {
      type: DataTypes.TEXT,
      field: 'durak_tel', 
    },
    durakSemt: {
      type: DataTypes.TEXT,
      field: 'durak_semt', 
    },
    durakSahibiAd: {
      type: DataTypes.TEXT,
      field: 'durak_sahibi_ad', 
    },
    durakSahibiSoyad: {
      type: DataTypes.TEXT,
      field: 'durak_sahibi_soyad', 
    },
  });
  return Durak;
};
