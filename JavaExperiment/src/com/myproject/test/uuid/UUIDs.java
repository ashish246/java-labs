package com.myproject.test.uuid;

import java.nio.ByteBuffer;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Factory class to create new {@link UUID}, new String UUIDs and new
 * Intershop-compliant Base64 encoded UUIDs. Also contains helper methods to
 * parse UUIDs from and to Intershop-compliant Base64 encoding.
 * 
 * @author Ashish Tripathi
 * 
 */
public class UUIDs
{
    public static final Pattern UUID_PATTERN = Pattern
                    .compile("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}");

    private UUIDs()
    {
    }

    /**
     * Create a new UUID encoded as Intershop-compatible Base64 string.
     * 
     * @return the UUID as Base64 String.
     */
    public static String create()
    {
        return create(true);
    }

    /**
     * Create a new UUID and optional encoded it as Intershop-compatible Base64
     * string.
     * 
     * @param pAsBase64
     * @return a UUID as String
     */
    public static String create(boolean pAsBase64)
    {
        if (pAsBase64)
        {
        	return UUID.randomUUID().toString();

            //return UUIDMgr.getInstance().createUUIDString();
        }

        return UUID.randomUUID().toString();
    }

    /**
     * Convert a string (either Base64 or Hex UUID string) to an actual UUID
     * object.
     * 
     * @param pUUID
     * @return the {@link String} as {@link UUID} object.
     */
    public static UUID parse(String pUUID)
    {
        // If verify returns true then the pUUID is in non-Base64 format,
        // otherwise it is and have to be decoded first.
        return UUID.fromString(verify(pUUID) ? pUUID : fromBase64(pUUID));
    }

    /**
     * Check if the provided, not Base64 encoded String is a valid UUID
     * according to the regular expression UUID_PATTERN.
     * 
     * @param pUUID
     *            - the UUID as not Base64 encoded
     * @return true if the format is valid, false otherwise.
     */
    public static boolean verify(String pUUID)
    {
        return UUID_PATTERN.matcher(pUUID.toString()).matches();
    }

    /**
     * Decode a Intershop-compliant pBase64 string to a UUID. 
     * 
     * @param pBase64
     * @return the UUID as {@link String}.
     */
    public static String fromBase64(String pBase64)
    {
        ByteBuffer tByteBuffer = ByteBuffer.wrap(pBase64.getBytes());

        return new UUID(tByteBuffer.getLong(), tByteBuffer.getLong()).toString();
    }

    /**
     * Encode a {@link UUID} as a Intershop-compliant Base64 string.
     * 
     * @param pUUID - a {@link UUID}
     * @return the encoded UUID as Base64 string.
     */
    public static String toBase64(UUID pUUID)
    {
        ByteBuffer tByteBuffer = ByteBuffer.wrap(new byte[16]);
        tByteBuffer.putLong(pUUID.getMostSignificantBits());
        tByteBuffer.putLong(pUUID.getLeastSignificantBits());

        //return UUIDCodec.encode(tByteBuffer.array());
        return pUUID.toString();
    }

    /**
     * Encode a {@link UUID} as a Intershop-compliant Base64 string.
     * 
     * @param pUUID
     * @return the encoded UUID as Base64 string.
     */
    public static String toBase64(String pUUID)
    {
        return toBase64(UUID.fromString(pUUID));
    }
}
