/*
	Input: x^100 
	Output: Integer

	Method:
	1) Start from x^1 and multiply by itself (x^1 x x^2) to find x^2
	2) Keep multiplying x raised to power by itself till resulting raised power will not From previous output multiply x^2 with itself to get x^4
	2) With the multiplied power, raise x to the power and multiply with itself. 
	3) Do this till the doubling of the exponent does not exceed the exponent the input is raised to.

*/

import java.util.*;

public class Exponentiation {


	public static void main (String[] args){
		
		ExponentiationObj input = new ExponentiationObj(3, 10);
		int result = calcExponentiation(input);
		int testResult = (int) Math.pow(3, 10);
		boolean outcome = result == testResult;
		System.out.println("Result: " + testResult + " Output: " + result);
	}

	public static class ExponentiationObj {

		int exponent;
		int base;

		public ExponentiationObj(int base, int exponent){
			this.exponent = exponent;
			this.base = base;
		}
	}


	public static int calcExponentiation(ExponentiationObj input){
		
		int ans = 0;
		int exponent = 1;
		int temp = input.base;


		Map<Integer, Integer> binaryPowers = new HashMap<>();
		binaryPowers.put(exponent, temp);

		while(exponent * 2 < input.exponent) {
			
			temp = temp * temp;
			exponent = exponent + exponent;
			binaryPowers.put(exponent, temp);
		}

		ans += temp;
		// are we at the input.exponent? If not can we use values from our geometric sequence to total it?
		// Find difference between current exponent and input.exponent...

		int difference = input.exponent - exponent;

		while(difference >= 1) {

			int biggestp2 = getBiggestPowerOf2(difference);
			ans *= binaryPowers.get(biggestp2);

			difference -= biggestp2;
		}

		return ans;
	}

	public static int getBiggestPowerOf2(int value){

		for(int i = value; i >= 1; i--){

			if((i & (i-1)) == 0)
				return i;
		}

		return 0;
	}

}