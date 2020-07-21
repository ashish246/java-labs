/**
 * 
 */
package com.jackson.api;

import java.io.IOException;
import java.math.BigDecimal;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**
 * Writes BigDecimal as a String while serializing the object to JSON so that
 * scale/precision are not truncated.
 * 
 * @author Ashish Tripathi
 * 
 */
public class JsonMoneySerializer extends JsonSerializer<BigDecimal>
{

    @Override
    public void serialize(BigDecimal pBigDecimal, JsonGenerator pGenerator, SerializerProvider pProvidor)
                    throws IOException, JsonProcessingException
    {
        pGenerator.writeString(pBigDecimal.toString());
    }

}
