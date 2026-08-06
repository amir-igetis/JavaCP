package leetcodeDiscussProbPatterns.dailyQuestions;

public class SmallestDivisibleDigitProductI {
	public static void main(String[] args) {
		int n = 10, t = 2;
		System.out.println(smallestNumber(n, t));
	}

// tc O(10logn) sc O(1)
	static int smallestNumber(int n, int t) {
		while (!check(n, t))
			n++;
		return n;
	}

	private static boolean check(int num, int t) {
		int product = 1;
		while (num > 0) {
			product *= num % 10;
			num /= 10;
			if (product == 0)
				break;
		}
		return product % t == 0;
	}
}
