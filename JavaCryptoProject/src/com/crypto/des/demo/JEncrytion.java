package com.crypto.des.demo;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 * we show you how to use Java Cryptography Extension (JCE) to encrypt or
 * decrypt a text via Data Encryption Standard (DES) mechanism.
 * 
 * 
 * @author Administrator
 *
 */
public class JEncrytion
{
	public static void main(String[] argv)
	{
		try
		{
			// Create a DES Key.
			KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
			SecretKey myDesKey = keygenerator.generateKey();

			/*
			 * Create a Cipher instance from Cipher class, specify the
			 * following information and separated by a slash (/).
			 * 
			 * 1. Algorithm name 2. Mode (optional) 3. Padding scheme
			 * (optional)
			 */
			/*
			 * DES = Data Encryption Standard. ECB = Electronic Codebook
			 * mode. PKCS5Padding = PKCS #5-style padding.
			 */

			Cipher desCipher;
			// Create the cipher
			desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

			// Initialize the cipher for encryption
			desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);

			// sensitive information
			// Convert String into Byte[] array format.
			byte[] text = "No body can see me".getBytes();

			System.out.println("Text [Byte Format] : " + text);
			System.out.println("Text : " + new String(text));

			// Encrypt the text
			byte[] textEncrypted = desCipher.doFinal(text);

			System.out.println("Text Encryted : " + textEncrypted);

			// Initialize the same cipher for decryption
			desCipher.init(Cipher.DECRYPT_MODE, myDesKey);

			// Decrypt the text
			byte[] textDecrypted = desCipher.doFinal(textEncrypted);

			System.out.println("Text Decryted : " + new String(textDecrypted));

		} catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		} catch (NoSuchPaddingException e)
		{
			e.printStackTrace();
		} catch (InvalidKeyException e)
		{
			e.printStackTrace();
		} catch (IllegalBlockSizeException e)
		{
			e.printStackTrace();
		} catch (BadPaddingException e)
		{
			e.printStackTrace();
		}

	}
}
