package app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PrayerTimesResponse {
    public String Gunes;
    public String Ogle;
    public String Ikindi;
    public String Aksam;
    public String Yatsi;

}
