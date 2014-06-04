package br.gov.mds.sebas.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class UtilMD5 {
	public static String produzirChaveMD5(String str)
			throws NoSuchAlgorithmException {
		if (str == null)
			return null;
		MessageDigest md = MessageDigest.getInstance("MD5");
		BigInteger hash = new BigInteger(1, md.digest(str.getBytes()));
		String s = hash.toString(16);
		if (s.length() % 2 != 0)
			s = "0" + s;
		return s;
	}
}