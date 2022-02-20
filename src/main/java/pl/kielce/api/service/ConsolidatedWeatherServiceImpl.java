package pl.kielce.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kielce.api.model.ConsolidatedWeather;
import pl.kielce.api.repo.ConsolidatedWeatherRepo;

import java.util.List;
import java.util.Optional;

@Service
public class ConsolidatedWeatherServiceImpl implements ConsolidatedWeatherService {
	private final ConsolidatedWeatherRepo consolidatedWeatherRepo;

	@Autowired
	public ConsolidatedWeatherServiceImpl(ConsolidatedWeatherRepo consolidatedWeatherRepo) {
		this.consolidatedWeatherRepo = consolidatedWeatherRepo;
	}

	@Override
	public List<ConsolidatedWeather> getAllFromDB() {
		return consolidatedWeatherRepo.findAll();
	}

	@Override
	public Optional<ConsolidatedWeather> getFromDBById(Long cwId) {
		return consolidatedWeatherRepo.findByCwId(cwId);
	}

	@Override
	public void add(ConsolidatedWeather consolidatedWeather) {
		consolidatedWeatherRepo.save(consolidatedWeather);
	}

	@Override
	public void delete(ConsolidatedWeather consolidatedWeather) {
		consolidatedWeatherRepo.delete(consolidatedWeather);
	}
}
