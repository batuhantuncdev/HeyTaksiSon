const express = require('express');
const userRouter = express.Router();
const uuid = require('uuid');

const Durak = require('../models/durak.js');
const durakMiddleware = require('../middleware/durakMiddleware.js');


userRouter.get('/getDurakList',  (req, res, next) => {
    return res.status(200).send({
        message: result
    })
    
    // db.query(`SELECT * FROM user;`, (err, result) => { 
    //     if (err) {
    //         throw err;
    //         return res.status(400).send({
    //             message: err,
    //         });
    //     }
    //     if (!result.length) {
    //         return res.status(500).send({
    //             message: 'Please check backend configurations'
    //         })
    //     }
        
    //     return res.status(200).send({
    //         message: result
    //     })
    // });
});
