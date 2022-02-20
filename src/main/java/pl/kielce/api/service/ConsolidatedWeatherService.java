package pl.kielce.api.service;

import pl.kielce.api.model.ConsolidatedWeather;

import java.util.List;
import java.util.Optional;

public interface ConsolidatedWeatherService {
	List<ConsolidatedWeather> getAllFromDB();
	Optional<ConsolidatedWeather> getFromDBById(Long cwId);
	void add(ConsolidatedWeather consolidatedWeather);
	void delete(ConsolidatedWeather consolidatedWeather);
}
