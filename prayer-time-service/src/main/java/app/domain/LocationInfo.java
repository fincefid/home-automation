package app.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LocationInfo {
    public String countryName;
    public String stateName;
    public String name;
}
