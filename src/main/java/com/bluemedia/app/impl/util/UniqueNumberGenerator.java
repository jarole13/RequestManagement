package com.bluemedia.app.impl.util;

import java.util.Arrays;
import java.util.List;

public class UniqueNumberGenerator {

	private static long current = System.currentTimeMillis();

	private static List<Integer> numberList = Arrays.asList(9, 1, 6, 0, 3, 5, 2, 8, 4, 7);

	public static synchronized Long generateUniqueNumber() {
		long currentTime = System.currentTimeMillis();
		while (currentTime <= current) {
			currentTime++;
		}
		current = currentTime;
		return encodeNumber(currentTime);
	}

	private static Long encodeNumber(Long currentTime) {
		String value = String.valueOf(currentTime);
		String encodedNumber = "1";
		for (int i = 0; i < value.length(); i++) {
			int num = value.charAt(i)-'0';
			encodedNumber = encodedNumber + numberList.get(num).toString();
		}
		return Long.parseLong(encodedNumber);
	}
}
