package pl.kielce.api.service;

import pl.kielce.api.model.Weather;

public interface WeatherService {
	Weather getByWoeid(String woeid);
}
