package pl.kielce.api.api;

import com.google.common.base.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import pl.kielce.api.model.Location;
import pl.kielce.api.service.LocationService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/favorites", produces = "application/json")
public class FavoritesController {
	private final LocationService locationService;

	@Autowired
	public FavoritesController(LocationService locationService) {
		this.locationService = locationService;
	}

	@GetMapping
	public List<Location> get() {
		List<Location> favorites = locationService.getFromDbFavorites();
		return favorites;
	}

	@GetMapping("/{woeid}")
	public Location get(@PathVariable Integer woeid) {
		Location location = locationService.getFromDBByWoeid(woeid)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return location;
	}

	@PutMapping("/{woeid}")
	public void put(@PathVariable Integer woeid, @RequestBody Location locationJson) {
		if (Objects.equal(locationJson.getWoeid(), woeid)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		Location location = locationService.getFromApiByWoeid(woeid)
			.orElseGet(() -> newLocation(woeid));

		location.setName(locationJson.getName());
		location.setLocationType(locationJson.getLocationType());
		location.setCoordiantes(locationJson.getCoordiantes());
		location.setFavorite(locationJson.isFavorite());
		location.setDistance(locationJson.getDistance());

		if (location.getId() != null) {
			locationService.addToDb(location);
		}
	}

	@DeleteMapping("/{woeid}")
	public void delete(@PathVariable Integer woeid) {
		Location location = locationService.getFromDBByWoeid(woeid)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		locationService.deleteFromDb(location);
	}


	private Location newLocation(Integer woeid) {
		Location location = new Location();
		location.setWoeid(woeid);
		return location;
	}
}
