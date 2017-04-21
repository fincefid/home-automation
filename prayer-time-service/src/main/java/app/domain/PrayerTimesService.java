package app.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class PrayerTimesService {

    @Resource(name = "prayerServiceClient")
    private RestTemplate restTemplate;

    @Autowired
    private LocationInfo locationInfo;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

    public List<String> getTimes() {

        PrayerTimesResponse response = restTemplate.postForObject("/PrayerTimesSet", locationInfo, PrayerTimesResponse.class);

        log.info("Prayer Response: {}", response);

        LocalTime gunes =  LocalTime.parse(response.Gunes, formatter);
        return Arrays.asList(gunes.minusMinutes(30).toString(),
                response.Ogle,
                response.Ikindi,
                response.Aksam,
                response.Yatsi);
    }
}
