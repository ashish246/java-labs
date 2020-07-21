package com.crypto.aes.demo;

/**
 * he Advanced Encryption Standard (AES) is an encryption algorithm designed for
 * securing electronic data established by the U.S National Institute of
 * Standards and Technology (NIST) in 2001. AES is based on Rijndael cipher
 * developed by cryptographers Joan Daemen and Vincent Rijmen. AES carries out
 * its operation on a 4×4 column major order matrix of bytes. The key size used
 * for an AES cipher specifies the number of repetitions of transformation
 * rounds that convert the input, called the plaintext, into the final output,
 * called the ciphertext.
 * 
 * AES is currently available in three key sizes: 128, 192 and 256 bits.The
 * design and strength of all key lengths of the AES algorithm are sufficient to
 * protect classified information up to the SECRET level. As far as security is
 * concerned, AES is considered to be more secure than its predecessor DES. For
 * more information please refer this link.
 * 
 * In this tutorial, we will learn how to implement AES-256 encryption using
 * Java. We will use the Java Cryptography Extension for encryption, decryption,
 * key generation etc. Before proceeding make sure you have downloaded the
 * jurisdiction policy for your respective Java platform from over here. Place
 * the policy files in the {JDK_HOME}\jre\lib\security folder.
 * 
 * http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-
 * 432124.html
 * 
 * @author Administrator
 *
 */
public class TestAESEncryption
{
	public static void main(String[] args) throws Exception
	{
		// TODO Auto-generated method stub

		AESDemo d = new AESDemo();

		System.out.println("Encrypted string:" + d.encrypt("Hello"));
		String encryptedText = d.encrypt("Hello");
		System.out.println("Decrypted string:" + d.decrypt(encryptedText));

	}
}