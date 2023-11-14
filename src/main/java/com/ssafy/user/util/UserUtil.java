package com.ssafy.user.util;

import java.security.MessageDigest;
import java.security.SecureRandom;

public class UserUtil {
	private static final int SALT_SIZE = 16;
	private static final int STRETCH_COUNT = 10;
	private static final int TEMP_PW_LENGTH = 8;

	private static final char[] rndAllCharacters = new char[] {
			// number
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			// uppercase
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
			'V', 'W', 'X', 'Y', 'Z',
			// lowercase
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
			'v', 'w', 'x', 'y', 'z' };

	// generate SALT
	public static String generateSalt() {
		SecureRandom saltGenerator = new SecureRandom();
		byte[] salt = new byte[SALT_SIZE];
		saltGenerator.nextBytes(salt);

		return parser(salt);
	}

	// hashing password
	public static String hashingPassword(String password, String salt) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-256");

		byte[] hashed = password.getBytes();
		for (int i = 0; i < STRETCH_COUNT; i++) {
			String str = parser(hashed) + salt;
			md.update(str.getBytes());
			hashed = md.digest();
		}

		return parser(hashed);
	}

	// generate Temporary Password
	public static String generateTempPassword() {
		SecureRandom random = new SecureRandom();
		StringBuilder stringBuilder = new StringBuilder();

		int rndAllCharactersLength = rndAllCharacters.length;
		for (int i = 0; i < TEMP_PW_LENGTH; i++) {
			stringBuilder.append(rndAllCharacters[random.nextInt(rndAllCharactersLength)]);
		}

		return stringBuilder.toString();
	}

	// salt bytes to String
	private static String parser(byte[] saltBytes) {
		StringBuilder sb = new StringBuilder();
		for (byte s : saltBytes) {
			sb.append(String.format("%02x", s));
		}

		return sb.toString();
	}
}
