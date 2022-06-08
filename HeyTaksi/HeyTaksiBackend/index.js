const express = require('express');
const Durak = require('./models/Durak');
const app = express()

const cors = require('cors');

const PORT = process.env.PORT || 3000;

app.use(express.json());

const db = require("./models");
db.sequelize.sync();

// db.sequelize.sync({ force: true }).then(() => {
//     console.log("Drop and re-sync db.");
// });

app.get('/getDurakList',  (req, res) => {
    db.findAll({}).then(duraklar => res.send({
        message: duraklar
    }));
});

app.listen(PORT, () => console.log("Server running on port: " + PORT));