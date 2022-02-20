package pl.kielce.api.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;
import javax.annotation.Nullable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"distance",
	"title",
	"location_type",
	"woeid",
	"latt_long"
})
@Generated("jsonschema2pojo")
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("distance")
	private Integer distance;

	@JsonProperty("title")
	private String name;

	@JsonProperty("location_type")
	private String locationType;

	@JsonProperty("woeid")
	private Integer woeid;

	@JsonProperty("latt_long")
	private String coordiantes;

	// transient
	@Transient
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	// added
	private boolean favorite;

	@OneToMany(mappedBy = "location")
	private List<ConsolidatedWeather> consolidatedWeathers;


	// added methods
	public Boolean checkIfIsInList(@Nullable List<Location> listToCompare) {
		if (listToCompare == null) {
			listToCompare = new ArrayList<>();
		}
		for (Location l : listToCompare) {
			if (l.woeid.equals(this.woeid)) {
				return true;
			}
		}
		return false;
	}

	// getters and setters
	@JsonProperty("distance")
	public Integer getDistance() {
		return distance;
	}

	@JsonProperty("distance")
	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	@JsonProperty("title")
	public String getName() {
		return name;
	}

	@JsonProperty("title")
	public void setName(String title) {
		this.name = title;
	}

	@JsonProperty("location_type")
	public String getLocationType() {
		return locationType;
	}

	@JsonProperty("location_type")
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	@JsonProperty("woeid")
	public Integer getWoeid() {
		return woeid;
	}

	@JsonProperty("woeid")
	public void setWoeid(Integer woeid) {
		this.woeid = woeid;
	}

	@JsonProperty("latt_long")
	public String getCoordiantes() {
		return coordiantes;
	}

	@JsonProperty("latt_long")
	public void setCoordiantes(String lattLong) {
		this.coordiantes = lattLong;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isFavorite() {
		return favorite;
	}

	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}

	public List<ConsolidatedWeather> getConsolidatedWeathers() {
		return consolidatedWeathers;
	}

	public void setConsolidatedWeathers(List<ConsolidatedWeather> consolidatedWeathers) {
		this.consolidatedWeathers = consolidatedWeathers;
	}
}
