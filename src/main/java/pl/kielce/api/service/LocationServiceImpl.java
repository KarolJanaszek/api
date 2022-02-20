package pl.kielce.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kielce.api.model.Location;
import pl.kielce.api.model.Weather;
import pl.kielce.api.repo.LocationRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;

@Service
public class LocationServiceImpl implements LocationService {
	private final LocationRepo locationRepo;
	private final WeatherService weatherService;
	private final MetaweatherService metaweatherService;

	@Autowired
	public LocationServiceImpl(LocationRepo locationRepo, WeatherService weatherService, MetaweatherService metaweatherService) {
		this.locationRepo = locationRepo;
		this.weatherService = weatherService;
		this.metaweatherService = metaweatherService;
	}

	// from DB
	@Override
	public List<Location> getAllFromDB() {
		return locationRepo.findAll();
	}

	@Override
	public List<Location> getFromDbFavorites() {
		return locationRepo.findAllByFavoriteTrue();
	}

	@Override
	public Optional<Location> getFromDBByWoeid(Integer woeid) {
		return locationRepo.findByWoeid(woeid);
	}

	@Override
	public void addToDb(Location location) {
		locationRepo.save(location);
	}

	@Override
	public void deleteFromDb(Location location) {
		locationRepo.delete(location);
	}

	@Override
	public void toggleFavoriteInDb(Location location) {
		location.setFavorite(!location.isFavorite());
		locationRepo.save(location);
	}

	// from API
	@Override
	public List<Location> getFromApiByName(String name) {
		Location[] locations = metaweatherService.getLocationsByQuery(name);
		if (locations != null) {
			for (Location location : locations) {
				Weather weather = weatherService.getByWoeid(location.getWoeid());
				location.setConsolidatedWeathers(
					weather != null ? weather.getConsolidatedWeather() : null
				);
			}
			return asList(locations);
		}
		return new ArrayList<>();
	}

	@Override
	public Optional<Location> getFromApiByWoeid(Integer woeid) {
		return Optional.ofNullable(metaweatherService.getLocationByWoeid(woeid));
	}

	// from DB or from API
	@Override
	public Optional<Location> getFromDbOrApi(Integer woeid) {
		Optional<Location> fromDBByWoeid = getFromDBByWoeid(woeid);
		if (fromDBByWoeid.isPresent()) {
			return fromDBByWoeid;
		}
		return getFromApiByWoeid(woeid);
	}
}
