package leetcodeContest;

public class MaximumBalancedShipments {

    static int maxBalancedShipments(int[] weight) {
        int n = weight.length;
        if (n == 0) {
            return 0;
        }

        int count = 0;
        int currentMax = weight[0];
        int shipmentStart = 0;

        for (int i = 1; i < n; i++) {
            if (weight[i] < currentMax) {
                count++;
                shipmentStart = i + 1;
                if (shipmentStart < n) {
                    currentMax = weight[shipmentStart];
                }
            } else {
                currentMax = Math.max(currentMax, weight[i]);
            }
        }

        return count;
    }
}
