package pl.kielce.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ResponseStatusException;
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
}
