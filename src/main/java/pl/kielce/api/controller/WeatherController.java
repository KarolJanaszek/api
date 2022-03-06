package pl.kielce.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ResponseStatusException;
import pl.kielce.api.model.ConsolidatedWeather;
import pl.kielce.api.model.Location;
import pl.kielce.api.model.Weather;
import pl.kielce.api.service.ConsolidatedWeatherService;
import pl.kielce.api.service.LocationService;
import pl.kielce.api.service.WeatherService;

import java.util.ArrayList;
import java.util.NoSuchElementException;

@Controller
public class WeatherController {
	private final WeatherService weatherService;
	private final LocationService locationService;
	private final ConsolidatedWeatherService consolidatedWeatherService;

	@Autowired
	public WeatherController(WeatherService weatherService, LocationService locationService, ConsolidatedWeatherService consolidatedWeatherService) {
		this.weatherService = weatherService;
		this.locationService = locationService;
		this.consolidatedWeatherService = consolidatedWeatherService;
	}

	@GetMapping("/app")
	public String getWeather(Model model,
					  @RequestParam(required = false) String query,
					  @RequestParam(required = false) Integer id
	) {
		Location selectedLocation = null;

		model.addAttribute("loc", query != null ?
			locationService.getFromApiByName(query) : new ArrayList<>());
		model.addAttribute("weather", id != null ?
			weatherService.getByWoeid(id) : new Weather());
		model.addAttribute("locationsInDB", locationService.getAllFromDB());
		if (id != null) {
			try {
				selectedLocation = locationService.getFromDbOrApi(id)
					.orElseThrow(() -> new NoSuchElementException("Location not found."));
			} catch (RestClientException e) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		model.addAttribute("selectedLocation", selectedLocation);
		model.addAttribute("favorites", locationService.getFromDbFavorites());
		model.addAttribute("cWeatherInDb", consolidatedWeatherService.getAllFromDB());
		return "home";
	}

	@GetMapping("/app/{locWoeid}/favorite")
	public String toggleFavorite(@PathVariable Integer locWoeid,
								 @RequestParam(required = false, defaultValue = "") String query,
								 @RequestParam(required = false, defaultValue = "") String id
	) {
		locationService.toggleFavoriteInDb(locWoeid);
		return "redirect:/app";
	}

	// "/{locWoeid}/save"
	@GetMapping("/app/{locWoeid}/save")
	public String saveLocation(@PathVariable Integer locWoeid,
							   @RequestParam(required = false, defaultValue = "") String query,
							   @RequestParam(required = false, defaultValue = "") String id
	) {
		Location location = locationService.getFromApiByWoeid(locWoeid)
			.orElseThrow(() -> new NoSuchElementException("Location not found."));
		locationService.addToDb(location);
		return "redirect:/app";
	}

	// "/{locWoeid}/delete"
	@GetMapping("/app/{locWoeid}/delete")
	public String deleteLocation(@PathVariable Integer locWoeid,
								 @RequestParam(required = false, defaultValue = "") String query,
								 @RequestParam(required = false, defaultValue = "") String id
	) {
		Location location = locationService.getFromDBByWoeid(locWoeid)
			.orElseThrow(() -> new NoSuchElementException("Location not found."));
		locationService.deleteFromDb(location);
		return "redirect:/app";
	}

	// "/{locWoeid}/{cwid}/save"
	@GetMapping("/app/{locWoeid}/{cwId}/save")
	public String saveConsolidatedWeather(@PathVariable Integer locWoeid,
										  @PathVariable Long cwId,
										  @RequestParam(required = false, defaultValue = "") String query,
										  @RequestParam(required = false, defaultValue = "") String id
	) {
		Location location = locationService.getFromDBByWoeid(locWoeid)
			.orElseThrow(() -> new NoSuchElementException("Location not found in DB."));

		Weather weather = weatherService.getByWoeid(locWoeid);
		ConsolidatedWeather consolidatedWeather = null;
		for (ConsolidatedWeather cw : weather.getConsolidatedWeather()) {
			if (cw.getCwId().equals(cwId)) {
				consolidatedWeather = cw;
			}
		}
		if (consolidatedWeather != null) {
			consolidatedWeather.setLocation(location);
			consolidatedWeatherService.add(consolidatedWeather);
		} else {
			throw new NoSuchElementException("Consolidated weather not found in that location.");
		}
		return "redirect:/app";
	}

	// "/consolidatedWeather/{cwid}/delete"
	@GetMapping("/app/consolidatedWeather/{cwId}/delete")
	public String deleteConsolidatedWeather(@PathVariable Long cwId,
											@RequestParam(required = false, defaultValue = "") String query,
											@RequestParam(required = false, defaultValue = "") String id
	) {
		ConsolidatedWeather consolidatedWeather = consolidatedWeatherService.getFromDBById(cwId)
				.orElseThrow(() -> new NoSuchElementException("Consolidated weather not found."));
		consolidatedWeatherService.delete(consolidatedWeather);
		return "redirect:/app";
	}
}
