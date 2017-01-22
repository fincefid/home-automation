const unirest = require('unirest');
const later = require('later');
const prayers = require('./prayers');

var schedules = function() {
    console.log("Creating schedules");
    prayers.init();
    later.setInterval(prayers.checkPrayerTime, later.parse.text('every 1 min'));
};

module.exports = schedules;