package com.java.json.test;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class ReaderDemo {

	public static void main(String[] args) throws IOException {

		try {
			URL url = new URL(
					"https://graph.facebook.com/search?q=java&type=post");
			try (InputStream is = url.openStream();
					JsonReader rdr = Json.createReader(is)) {

				JsonObject obj = rdr.readObject();
				JsonArray results = obj.getJsonArray("data");
				
				for (JsonObject result : results.getValuesAs(JsonObject.class)) {
					System.out.print(result.getJsonObject("from").getString(
							"name"));
					System.out.print(": ");
					System.out.println(result.getString("message", ""));
					System.out.println("-----------");
				}
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
