package leetcodeDiscussProbPatterns.dailyQuestions;

public class CheckDivisibilityByDigitSumAndProduct {
	public static void main(String[] args) {

		int n = 99;
		System.out.println(checkDivisibility(n));
		System.out.println(getSum(n));
		System.out.println(getProd(n));
		System.out.println(checkDivisibilityI(n));

	}

	static boolean checkDivisibility(int n) {
		int sum = getSum(n);
		int prod = getProd(n);
//		return n % (sum + prod) == 0 ? true : false;
		return n % (sum + prod) == 0;
	}

	private static int getSum(int n) {
		int sum = 0;
		while (n > 0) {
			int rem = n % 10;
			sum += rem;
			n /= 10;
		}
		return sum;
	}

	private static int getProd(int n) {
		int prod = 1;
		while (n > 0) {
			int rem = n % 10;
			prod *= rem;
			n /= 10;
		}
		return prod;
	}

	static boolean checkDivisibilityI(int n) {
		int s = 0, p = 1;
		for (int i = n; i > 0; i /= 10) {
			int rem = i % 10;
			s += rem;
			p *= rem;
		}
		return n % (s + p) == 0 ? true : false;
	}
}
