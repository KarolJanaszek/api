package pl.kielce.api.service;

import pl.kielce.api.model.Location;
import pl.kielce.api.model.Weather;

public interface MetaweatherService {
	Weather getWeatherByWoeid(Integer woeid);
	Location getLocationByWoeid(Integer woeid);
	Location[] getLocationsByQuery(String query);
}
