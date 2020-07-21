package com.jackson.api;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class JsonDateSerializer extends JsonSerializer<Date>
{
    @Override
    public void serialize(Date pDate, JsonGenerator pGenerator, SerializerProvider pProvider) throws IOException,
                    JsonProcessingException
    {
        pGenerator.writeString(new SimpleDateFormat("yyyy-MM-dd").format(pDate));
    }
}
