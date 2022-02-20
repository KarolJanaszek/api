package pl.kielce.api.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "c_weather")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"id",
	"weather_state_name",
	"weather_state_abbr",
	"wind_direction_compass",
	"created",
	"applicable_date",
	"min_temp",
	"max_temp",
	"the_temp",
	"wind_speed",
	"wind_direction",
	"air_pressure",
	"humidity",
	"visibility",
	"predictability"
})
@Generated("jsonschema2pojo")
public class ConsolidatedWeather {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long dbId;

	@JsonProperty("id")
	private Long cwId;

	@JsonProperty("applicable_date")
	private String applicableDate;

	@JsonProperty("min_temp")
	private Double minTemp;

	@JsonProperty("max_temp")
	private Double maxTemp;

	@JsonProperty("the_temp")
	private Double theTemp;

	@JsonProperty("wind_speed")
	private Double windSpeed;

	@JsonProperty("wind_direction")
	private Double windDirection;

	@JsonProperty("air_pressure")
	private Double airPressure;

	// transient
	@Transient
	@JsonProperty("weather_state_name")
	private String weatherStateName;

	@Transient
	@JsonProperty("weather_state_abbr")
	private String weatherStateAbbr;

	@Transient
	@JsonProperty("wind_direction_compass")
	private String windDirectionCompass;

	@Transient
	@JsonProperty("created")
	private String created;

	@Transient
	@JsonProperty("humidity")
	private Integer humidity;

	@Transient
	@JsonProperty("visibility")
	private Double visibility;

	@Transient
	@JsonProperty("predictability")
	private Integer predictability;

	@Transient
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	// added
	@ManyToOne
	private Location location;


	// added methods
	public Boolean checkIfIsInList(List<ConsolidatedWeather> listToCompare) {
		for (ConsolidatedWeather cw : listToCompare) {
			if (cw.cwId.equals(this.cwId)) {
				return true;
			}
		}
		return false;
	}

	// getters and setters
	@JsonProperty("id")
	public Long getCwId() {
		return cwId;
	}

	@JsonProperty("id")
	public void setCwId(Long id) {
		this.cwId = id;
	}

	@JsonProperty("weather_state_name")
	public String getWeatherStateName() {
		return weatherStateName;
	}

	@JsonProperty("weather_state_name")
	public void setWeatherStateName(String weatherStateName) {
		this.weatherStateName = weatherStateName;
	}

	@JsonProperty("weather_state_abbr")
	public String getWeatherStateAbbr() {
		return weatherStateAbbr;
	}

	@JsonProperty("weather_state_abbr")
	public void setWeatherStateAbbr(String weatherStateAbbr) {
		this.weatherStateAbbr = weatherStateAbbr;
	}

	@JsonProperty("wind_direction_compass")
	public String getWindDirectionCompass() {
		return windDirectionCompass;
	}

	@JsonProperty("wind_direction_compass")
	public void setWindDirectionCompass(String windDirectionCompass) {
		this.windDirectionCompass = windDirectionCompass;
	}

	@JsonProperty("created")
	public String getCreated() {
		return created;
	}

	@JsonProperty("created")
	public void setCreated(String created) {
		this.created = created;
	}

	@JsonProperty("applicable_date")
	public String getApplicableDate() {
		return applicableDate;
	}

	@JsonProperty("applicable_date")
	public void setApplicableDate(String applicableDate) {
		this.applicableDate = applicableDate;
	}

	@JsonProperty("min_temp")
	public Double getMinTemp() {
		return minTemp;
	}

	@JsonProperty("min_temp")
	public void setMinTemp(Double minTemp) {
		this.minTemp = minTemp;
	}

	@JsonProperty("max_temp")
	public Double getMaxTemp() {
		return maxTemp;
	}

	@JsonProperty("max_temp")
	public void setMaxTemp(Double maxTemp) {
		this.maxTemp = maxTemp;
	}

	@JsonProperty("the_temp")
	public Double getTheTemp() {
		return theTemp;
	}

	@JsonProperty("the_temp")
	public void setTheTemp(Double theTemp) {
		this.theTemp = theTemp;
	}

	@JsonProperty("wind_speed")
	public Double getWindSpeed() {
		return windSpeed;
	}

	@JsonProperty("wind_speed")
	public void setWindSpeed(Double windSpeed) {
		this.windSpeed = windSpeed;
	}

	@JsonProperty("wind_direction")
	public Double getWindDirection() {
		return windDirection;
	}

	@JsonProperty("wind_direction")
	public void setWindDirection(Double windDirection) {
		this.windDirection = windDirection;
	}

	@JsonProperty("air_pressure")
	public Double getAirPressure() {
		return airPressure;
	}

	@JsonProperty("air_pressure")
	public void setAirPressure(Double airPressure) {
		this.airPressure = airPressure;
	}

	@JsonProperty("humidity")
	public Integer getHumidity() {
		return humidity;
	}

	@JsonProperty("humidity")
	public void setHumidity(Integer humidity) {
		this.humidity = humidity;
	}

	@JsonProperty("visibility")
	public Double getVisibility() {
		return visibility;
	}

	@JsonProperty("visibility")
	public void setVisibility(Double visibility) {
		this.visibility = visibility;
	}

	@JsonProperty("predictability")
	public Integer getPredictability() {
		return predictability;
	}

	@JsonProperty("predictability")
	public void setPredictability(Integer predictability) {
		this.predictability = predictability;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}
}
