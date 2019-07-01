package utilities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {
	public java.sql.Date convertToSqlDate(String date) {
		try {
			return new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(date).getTime());
		} catch (ParseException e) {
			return null;
		}
	}
	
	public String convertToMD5(String message) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(message.getBytes());
			byte[] digest = messageDigest.digest();
			StringBuffer stringBuffer = new StringBuffer();
			for (byte b : digest) {
				stringBuffer.append(String.format("%02x", b & 0xff));
			}
			return new String(stringBuffer);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean isValidEmailAddress(String email) {
		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	public Integer convertToInteger(String number) {
		Integer n;
		try {
			n = Integer.valueOf(number);
		} catch (NumberFormatException e) {
			n = null;
		}
		return n;
	}
	public Double convertToDouble(String number) {
		Double n;
		try {
			n = Double.valueOf(number);
		} catch (NumberFormatException e) {
			n = null;
		}
		return n;
	}

}
