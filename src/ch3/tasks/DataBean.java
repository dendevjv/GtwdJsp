package ch3.tasks;

import java.util.HashMap;
import java.util.Map;

public class DataBean {
	private static final String[] VALID_COUNTRIES = { "GB", "US", "DE" };
	private static final String DEFAULT_COUNTRY = VALID_COUNTRIES[0];
	private static Map<String, String[]> VALID_CITIES;
	private static String DEFAULT_CITY;

	static {
		VALID_CITIES = new HashMap<String, String[]>();
		VALID_CITIES.put("GB", new String[] { "London", "Oxford", "Leeds" });
		VALID_CITIES.put("US", new String[] { "New York", "Los Angeles",
				"Miami" });
		VALID_CITIES.put("DE", new String[] { "Berlin", "Frankfurt",
				"Baden-Baden" });
		DEFAULT_CITY = VALID_CITIES.get(DEFAULT_COUNTRY)[0];
	}

	private String name;
	private String city;
	private String country;

	public DataBean() {
	}

	public DataBean(String name, String country, String city) {
		this.name = name;
		this.country = country;
		this.city = city;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final String getCity() {
		if (isCityValid()) {
			return city;
		} else {
			if (isCountryValid()) {
				return VALID_CITIES.get(country)[0];
			} else {
				return DEFAULT_CITY;
			}
		}
	}

	public final void setCity(String city) {
		this.city = city;
	}

	public final String getCountry() {
		if (isCountryValid()) {
			return country;
		} else {
			return DEFAULT_COUNTRY;
		}
	}

	public final void setCountry(String country) {
		this.country = country;
	}

	private boolean isCityValid() {
		return isNonEmpty(city) && isInValidCities(country, city);
	}

	private static boolean isInValidCities(String country, String city) {
		if (isInValidCountries(country)) {
			String[] cities = VALID_CITIES.get(country);
			for (String c : cities) {
				if (c.equals(city)) {
					return true;
				}
			}
			return false;
		}
		return false;
	}

	private boolean isCountryValid() {
		return isNonEmpty(country) && isInValidCountries(country);
	}

	private static boolean isInValidCountries(String country) {
		for (String c : VALID_COUNTRIES) {
			if (c.equals(country)) {
				return true;
			}
		}
		return false;
	}

	private static boolean isNonEmpty(String s) {
		return s != null && s.trim().length() > 0;
	}

	@Override
	public String toString() {
		return "[" + getName() + ", " + getCountry() + ", " + getCity() + "]";
	}

	public String toStringInner() {
		return "[" + name + ", " + country + ", " + city + "]";
	}
}
