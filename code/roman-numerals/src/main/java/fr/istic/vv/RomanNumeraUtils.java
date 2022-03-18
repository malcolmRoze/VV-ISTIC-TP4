package fr.istic.vv;

import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RomanNumeraUtils {

	static TreeMap<Integer, String> map = new TreeMap<Integer, String>();

	static {
		map.put(1000, "M");
		map.put(900, "CM");
		map.put(500, "D");
		map.put(400, "CD");
		map.put(100, "C");
		map.put(90, "XC");
		map.put(50, "L");
		map.put(40, "XL");
		map.put(10, "X");
		map.put(9, "IX");
		map.put(5, "V");
		map.put(4, "IV");
		map.put(1, "I");
	}

	private static int romanToInt(char c) {
		switch (c) {
		case 'M':
			return 1000;
		case 'D':
			return 500;
		case 'C':
			return 100;
		case 'L':
			return 50;
		case 'X':
			return 10;
		case 'V':
			return 5;
		case 'I':
			return 1;
		}
		return 0;
	}

	public static boolean isValidRomanNumeral(String value) {
		Pattern pattern = Pattern.compile("^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");

		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}

	public static int parseRomanNumeral(String numeral) {
		if (!isValidRomanNumeral(numeral))
			return -1;

		int res = 0;
		int prevC = -1;
		for (char c : numeral.toCharArray()) {
			int currentC = romanToInt(c);
			if (prevC != -1 && currentC > prevC) {
				res += currentC - 2 * prevC;
			} else {
				res += currentC;
			}
			prevC = currentC;
		}

		return res;
	}

	public static String toRomanNumeral(int number) {
		if (number < 1 || number > 3999)
			return "";
		int tmp = map.floorKey(number);
		if (number == tmp) {
			return map.get(number);
		}
		return map.get(tmp) + toRomanNumeral(number - tmp);
	}

}
