package pl.kielce.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kielce.api.model.Weather;
import pl.kielce.api.service.ConsolidatedWeatherService;
import pl.kielce.api.service.LocationService;
import pl.kielce.api.service.WeatherService;

import java.util.ArrayList;

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

	@GetMapping("/")
	public String get(Model model,
					  @RequestParam(required = false) String query,
					  @RequestParam(required = false) Integer id
					  ) {

		model.addAttribute("loc", query != null ? locationService.getFromApiByName(query) : new ArrayList<>());
		model.addAttribute("weather", id != null ?  weatherService.getByWoeid(id) : new Weather());

		// TODO
		model.addAttribute("locationsInDB", null);
		return "home";
	}
}
