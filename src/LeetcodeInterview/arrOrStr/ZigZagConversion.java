package LeetcodeInterview.arrOrStr;

public class ZigZagConversion {
    public static void main(String[] args) {
        String s = s = "PAYPALISHIRING";
        int numRows = 3;

        System.out.println(convert(s, numRows));
    }

    private static String convert(String s, int numRows) {
        if (numRows == 1 || numRows >= s.length())
            return s;

        StringBuilder[] str = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            str[i] = new StringBuilder();
        }

        int rowIndex = 0;
        boolean goingDown = false;
        for (char c : s.toCharArray()) {
            str[rowIndex].append(c);
            if (rowIndex == 0 || rowIndex == numRows - 1)
                goingDown = !goingDown;

            rowIndex += goingDown ? 1 : -1;
        }

        StringBuilder res = new StringBuilder();
        for (StringBuilder i : str)
            res.append(i);

        return res.toString();
    }
}
