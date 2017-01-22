const unirest = require('unirest');
const moment = require('moment');
const _ = require('underscore');

var sonos = {
    playPrayer: function() {
        console.log('Playing prayer');
        unirest
            .get('http://localhost:5005/clipall/kabe-ezan.mp3/15')
            .send()
            .end(function (response) {
                console.log("Prayer sent to Sonos");
            });
    }
};

var prayers = {
    scheduledTimes: [],
    init: function() {
        unirest
            .post('http://www.diyanet.gov.tr/PrayerTime/PrayerTimesSet')
            .send({"countryName": "15", "stateName": "14096", "name": "14096"})
            .end(function (response) {
                var test = moment('19:54', 'H:mm').subtract({minutes: 20});
                var gunes = moment(response.body.Gunes, 'H:mm').subtract({minutes: 20});
                this.scheduledTimes =  [
                    test.format('H:mm'),
                    gunes.format('H:mm'),
                    response.body.Ogle,
                    response.body.Ikindi,
                    response.body.Aksam,
                    response.body.Yatsi
                ];
                console.log("Scheduling:" + this.scheduledTimes);
            });
    },
    checkPrayerTime: function() {
        const currentTime = moment().format('H:mm');
        if (_.contains(this.scheduledTimes, currentTime)) {
            sonos.playPrayer();
        } else if (currentTime.endsWith(":00")) {
            console.log('Scheduler is up');
        }
    }
};

module.exports = prayers;