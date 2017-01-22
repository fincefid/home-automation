const unirest = require('unirest');
const later = require('later');
const prayers = require('./prayers');

var schedules = function() {
    console.log("Creating schedules");
    prayers.init();
    later.setInterval(prayers.checkPrayerTime, later.parse.text('every 1 min'));
    // fires at 01:00 every day
    later.setInterval(prayers.init, later.parse.cron('00 01 * * ? *'));
};

module.exports = schedules;