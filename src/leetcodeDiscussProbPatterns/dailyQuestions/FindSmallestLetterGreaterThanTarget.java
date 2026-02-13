package leetcodeDiscussProbPatterns.dailyQuestions;

public class FindSmallestLetterGreaterThanTarget {
    public static void main(String[] args) {
        char[] letters = {'c', 'f', 'j'};
        char[] let = {'x', 'x', 'y', 'y'};
        char ans = 'z';
        char target = 'c';
        System.out.println(nextGreatestLetter(letters, target));
        System.out.println(nextGreatestLetter(let, ans));

    }

    static char nextGreatestLetter(char[] letters, char target) {
        int start = 0, end = letters.length - 1;
        char ans = letters[0];
        while (start <= end) {
            int mid = start + (end - start) / 2;
//            if (target == letters[mid])
//                return letters[mid];
            if ((letters[mid] - 'a') > (target - 'a')) {
                ans = letters[mid];
                end = mid - 1;

            } else {
                start = mid + 1;
            }
        }
        return ans;
    }
}
