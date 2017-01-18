const express = require('express');
const unirest = require('unirest');
const router = express.Router();

/* GET api listing. */
router.get('/', (req, res) => {
    unirest
        .get('http://localhost:5005/clipall/kabe-ezan.mp3/15')
        .send()
        .end(function (response) {
            console.log("Called sonos-service:", response.body);
        });
    res.send('api works');
});

module.exports = router;