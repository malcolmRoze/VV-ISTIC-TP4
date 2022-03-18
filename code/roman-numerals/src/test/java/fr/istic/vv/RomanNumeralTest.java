package fr.istic.vv;
import net.jqwik.api.*;

public class RomanNumeralTest {
	
    @Property
    boolean integerToRomanNumeral(@ForAll("validIntegerForRomanNumeral") int anInteger) {
    	String aRomanNumeral = RomanNumeraUtils.toRomanNumeral(anInteger);
    	System.out.print(anInteger + " -> " + aRomanNumeral + "   ");
    	return RomanNumeraUtils.isValidRomanNumeral(aRomanNumeral);
    }
    
    @Property
    boolean romanNumeralToInteger(@ForAll("validRomanNumeral") String aRomanNumeral) {
    	int anInteger = RomanNumeraUtils.parseRomanNumeral(aRomanNumeral);
    	System.out.print(aRomanNumeral + " -> " + anInteger + "   ");
        return RomanNumeraUtils.isValidRomanNumeral(RomanNumeraUtils.toRomanNumeral(anInteger));
    }
    
    @Property
    boolean integerToRomanNumeralNonValid(@ForAll("nonValidIntegerForRomanNumeral") int anInteger) {
    	String aRomanNumeral = RomanNumeraUtils.toRomanNumeral(anInteger);
    	System.out.print(anInteger + " -> " + aRomanNumeral + "   ");
    	return RomanNumeraUtils.isValidRomanNumeral(aRomanNumeral);
    }
    
    @Property
    boolean romanNumeralToIntegerNonValid(@ForAll("nonValidRomanNumeral") String aRomanNumeral) {
    	int anInteger = RomanNumeraUtils.parseRomanNumeral(aRomanNumeral);
    	System.out.print(aRomanNumeral + " -> " + anInteger + "   ");
        return RomanNumeraUtils.isValidRomanNumeral(RomanNumeraUtils.toRomanNumeral(anInteger));
    }
    
    @Provide
	Arbitrary<String> validRomanNumeral() {
    	Arbitrary<String> mille = Arbitraries.strings().withChars(new char[] {'M'}).ofMinLength(0).ofMaxLength(3);
    	
    	Arbitrary<String> cent = Arbitraries.of("CM","CD","","C","CC","CCC","D","DC","DCC","DCCC");
    	
    	Arbitrary<String> dix = Arbitraries.of("XC","XL","","X","XX","XXX","L","LX","LXX","LXXX");
    	Arbitrary<String> unit = Arbitraries.of("IX","IV","","I","II","III","V","VI","VII","VIII");
    	
    	return Combinators.combine(mille, cent, dix, unit).as((m,c,d,u) -> m+c+d+u).filter(s -> !s.isEmpty());
	}
    
    @Provide
	Arbitrary<Integer> validIntegerForRomanNumeral() {
		return Arbitraries.integers().between(1, 3999);
	}
    
    @Provide
	Arbitrary<String> nonValidRomanNumeral() {
		return Arbitraries.strings().withChars(new char[] {'M', 'D', 'C', 'L', 'X', 'V', 'I'}).ofMinLength(1).
				ofMaxLength(9);
	}
    
    @Provide
	Arbitrary<Integer> nonValidIntegerForRomanNumeral() {
		return Arbitraries.integers();
	}
    
}
