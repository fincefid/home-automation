package app;

import app.domain.LocationInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class);
    }

    @Bean("prayerServiceClient")
    public RestTemplate prayerServiceClient(RestTemplateBuilder builder) {
        return builder.rootUri("http://namaz.diyanet.gov.tr/PrayerTime").build();
    }

    @Bean("sonosClient")
    public RestTemplate sonosClient(RestTemplateBuilder builder) {
        return builder.rootUri("http://localhost:5005/clipall/kabe-ezan.mp3").build();
    }

    @Bean
    public LocationInfo locationInfo() {
        return new LocationInfo("15","14096","14096");
    }
}