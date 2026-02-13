package scalarQuestions;

public class NumOfOpenDoors {
    public static void main(String[] args) {
        int a = 10;
        System.out.println(numOfClosedDoorI(a));
    }

    static int numOfClosedDoorI(int A) {
        int numberOfOpenDoors = 0;
        int[] countFactor = countFactors(A);

        for (int i = 1; i <= A; i++) {
            if (countFactor[i] % 2 == 1) {
                numberOfOpenDoors++;
            }
        }

        return numberOfOpenDoors;
    }

    private static int[] countFactors(int A) {
        int[] doorStatus = new int[A + 1];

        // Loop through each number to count its factors
        for (int i = 1; i <= A; i++) {
            for (int j = i; j <= A; j += i) {
                doorStatus[j]++;
            }
        }
        return doorStatus;
    }

    static int numOfClosedDoor(int a) {
        boolean[] ans = new boolean[a + 1];
        for (int i = 1; i <= a; i++) {
            if (Math.sqrt(i) == (int) Math.sqrt(i))
                ans[i] = true;
        }
        int count = 0;
        for (int i = 0; i <= a; i++) {
            if (ans[i])
                count++;
        }
        return count;
    }
}
