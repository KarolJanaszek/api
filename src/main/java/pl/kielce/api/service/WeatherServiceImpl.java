package pl.kielce.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kielce.api.model.Weather;

@Service
public class WeatherServiceImpl implements WeatherService {
	private final MetaweatherService metaweatherService;

	@Autowired
	public WeatherServiceImpl(MetaweatherService metaweatherService) {
		this.metaweatherService = metaweatherService;
	}

	@Override
	public Weather getByWoeid(Integer woeid) {
		return metaweatherService.getWeatherByWoeid(woeid);
	}
}
