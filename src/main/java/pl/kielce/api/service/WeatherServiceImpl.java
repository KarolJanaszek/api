package pl.kielce.api.service;

import org.springframework.stereotype.Service;
import pl.kielce.api.model.Weather;

@Service
public class WeatherServiceImpl implements WeatherService {
	@Override
	public Weather getByWoeid(String woeid) {
		return null;
	}
}
