package practice;

public class UserIdGenerator {

    public static void main(String[] args) {
//        Example 1
        String input1 = "Rajiv";
        String input2 = "Roy";
        int input3 = 560037;
        int input4 = 6;
        System.out.println(userIdGeneration(input1, input2, input3, input4));

//        Example 2
        input1 = "Manoj";
        input2 = "Kumar";
        input3 = 561327;
        input4 = 2;
        System.out.println(userIdGeneration(input1, input2, input3, input4));
    }

    static String userIdGeneration(String input1, String input2, int input3, int input4) {
        String smallerName;
        String longerName;

        if (input1.length() < input2.length()) {
            smallerName = input1;
            longerName = input2;
        } else if (input1.length() > input2.length()) {
            smallerName = input2;
            longerName = input1;
        } else {
            if (input1.compareTo(input2) < 0) {
                smallerName = input1;
                longerName = input2;
            } else {
                smallerName = input2;
                longerName = input1;
            }
        }
        char firstLetter = longerName.charAt(0);
        int digitFromLeft = Character.getNumericValue(String.valueOf(input3).charAt(input4 - 1));
        int digitFromRight = Character.getNumericValue(String.valueOf(input3).charAt(String.valueOf(input3).length() - input4));

        String userId = firstLetter + smallerName + digitFromLeft + digitFromRight;

        StringBuilder toggledUserId = new StringBuilder();
        for (char ch : userId.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                toggledUserId.append(Character.toLowerCase(ch));
            } else {
                toggledUserId.append(Character.toUpperCase(ch));
            }
        }
        return toggledUserId.toString();
    }
}