package pl.kielce.api.service;

import pl.kielce.api.model.Location;

import java.util.List;
import java.util.Optional;

public interface LocationService {
	// from DB
	List<Location> getAllFromDB();
	List<Location> getFromDbFavorites();
	Optional<Location> getFromDBByWoeid(Integer woeid);
	void addToDb(Location location);
	void deleteFromDb(Location location);
	void toggleFavoriteInDb(Integer woeid);

	// from API
	List<Location> getFromApiByName(String name);
	Optional<Location> getFromApiByWoeid(Integer woeid);

	// from DB or from API
	Optional<Location> getFromDbOrApi(Integer woeid);
}
