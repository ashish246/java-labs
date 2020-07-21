package com.jackson.api;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

/**
 * Factory class to get a {@link SimpleDateFormat} with the date format used for
 * JSON date serialization and deserialization. The used format is specified in
 * {@link <a href="http://www.iso.org/iso/home/standards/iso8601.htm">ISO 8601</a>}
 * 
 * @author Administrator
 * 
 */
public class JsonDateTimeDeserializer extends JsonDeserializer<Date> {
	public static final String ISO8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";

	@Override
	public Date deserialize(JsonParser pJSONparser,
			DeserializationContext pDeserializationContext) throws IOException,
			JsonProcessingException {
		SimpleDateFormat tFormat = new SimpleDateFormat(ISO8601_DATE_FORMAT);

		String tDate = pJSONparser.getText();

		if (tDate == null) {
			return null;
		}

		try {
			return tFormat.parse(tDate);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}
