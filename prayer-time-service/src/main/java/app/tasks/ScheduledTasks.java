package app.tasks;

import app.domain.PrayerTimesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

    private List<String> times = new ArrayList<>();

    @Autowired
    private PrayerTimesService prayerTimesService;

    @Resource(name="sonosClient")
    private RestTemplate sonosClient;

    @PostConstruct
    @Scheduled(fixedRate = 60000*60)
    public void fetchPrayerTimes() {
        log.info("The time is now {}", dateFormat.format(new Date()));

        List<String> times = prayerTimesService.getTimes();
        log.info("Updating prayer alert times to: {}", times);
        this.times = times;
    }

    @Scheduled(fixedRate = 60000)
    public void checkPrayerTime() {

        String currentHourAndMinute = dateFormat.format(new Date());

        if (times.contains(currentHourAndMinute)) {
            log.info("Prayer time at: {}", currentHourAndMinute);
            playAtSonos();
        }
    }

    private void playAtSonos() {
        ResponseEntity<String> response = sonosClient.getForEntity("/30", String.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            log.error("Unable to play prayer time at sonos speakers");
        }
    }

    public List<String> getTimes() {
        return times;
    }
}