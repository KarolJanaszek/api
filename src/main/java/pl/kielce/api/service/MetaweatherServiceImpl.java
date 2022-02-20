package pl.kielce.api.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.kielce.api.model.Location;
import pl.kielce.api.model.Weather;

@Service
public class MetaweatherServiceImpl implements MetaweatherService {
	private static final String HOST = "https://www.metaweather.com/api/";
	private static final String LOCATION = "location/";

	@Override
	public Weather getWeatherByWoeid(Integer woeid) {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(HOST + LOCATION + woeid, Weather.class);
	}

	@Override
	public Location getLocationByWoeid(Integer woeid) {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(HOST + LOCATION + woeid, Location.class);
	}

	@Override
	public Location[] getLocationsByQuery(String query) {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(HOST + LOCATION + "search/?query=" + query, Location[].class);
	}
}
