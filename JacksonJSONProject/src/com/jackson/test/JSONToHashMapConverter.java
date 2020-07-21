package com.jackson.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class JSONToHashMapConverter {

	public static void main(String[] args) {
		HashMap<String, String> hashmap = new HashMap<>();
		hashmap.put("id", "11");
		hashmap.put("firstName", "Lokesh");
		hashmap.put("lastName", "Gupta");

		// serializeMapAsJson(hashmap);

		deserializeMapFromJson();
	}

	/**
	 * Serializes features map into JSON string. Enables default JSON object
	 * typing so that at the time of serialization correct implementation type
	 * of abstract type {@link IFeature} and {@link IFeatureAttributes} can be
	 * stored in JSON, if the map is of the type {@code Map<String, IFeature>}.
	 * Disable default object typing after the serialization so that object
	 * mapper does not affect serialization of any other objects.
	 * 
	 * @param pFeatures
	 *            which is a map of type {@code Map<EProductFeature, IFeature>}
	 * @return JSON string of features
	 */
	public static void serializeMapAsJson(Map<String, String> pFeatures) {
		ObjectMapper tMapper = null;

		if (tMapper == null) {
			tMapper = new ObjectMapper();
		}
		tMapper.enableDefaultTyping(); // default to using
		// DefaultTyping.OBJECT_AND_NON_CONCRETE

		try {
			tMapper.writeValue(new File("src/com/jackson/data/hashmap.json"),
					pFeatures);

			tMapper.disableDefaultTyping();

		} catch (IOException ioEx) {
			ioEx.printStackTrace();
			tMapper = null;
		}
	}

	/**
	 * De-serializes features JSON into Map object. Enables default JSON object
	 * typing so that at the time of de-serialization correct implementation of
	 * abstract type {@link IFeature} and {@link IFeatureAttributes} can be
	 * instantiated. Disable default object typing after the serialization so
	 * that object mapper does not affect de-serialization of any other objects.
	 * 
	 * @param pFeatureJson
	 * @return the features map of the type
	 *         {@code HashMap<EProductFeature, IFeature>}
	 */
	public static void deserializeMapFromJson() {
		ObjectMapper tMapper = null;
		if (tMapper == null) {
			tMapper = new ObjectMapper();
		}

		tMapper.enableDefaultTyping(); // default to using
										// DefaultTyping.OBJECT_AND_NON_CONCRETE
		try {
			HashMap<String, String> tFeatures = tMapper.readValue(new File(
					"src/com/jackson/data/hashmap.json"),
					new TypeReference<HashMap<String, String>>() {// type
																	// reference
																	// used for
																	// the
																	// de-serialization
																	// of
																	// Map
					});

			tMapper.disableDefaultTyping();

			System.out.println(tFeatures.toString());

		} catch (IOException ioEx) {
			ioEx.printStackTrace();
			tMapper = null;
		}

	}

}
