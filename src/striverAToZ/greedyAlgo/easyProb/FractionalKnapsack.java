package striverAToZ.greedyAlgo.easyProb;

import java.util.Arrays;
import java.util.Comparator;

public class FractionalKnapsack {
    public static void main(String[] args) {

    }

    static double fracKnapsack(int W, Item[] arr, int n) {
        Arrays.sort(arr, new itemComparator());
        int curWeight = 0;
        double finalValue = 0.0;
        for (int i = 0; i < n; i++) {
            if (curWeight + arr[i].weight <= W) {
                curWeight += arr[i].weight;
                finalValue += arr[i].value;
            } else {
                int remain = W - curWeight;
                finalValue += ((double) arr[i].value / (double) arr[i].weight) * (double) remain;
                break;
            }
        }
        return finalValue;
    }

    // soln for https://practice.geeksforgeeks.org/problems/fractional-knapsack-1587115620/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=fractional-knapsack
    static double fractionalKnapsack(int W, Item arr[], int n) {
        // Your code here
        Arrays.sort(arr, new Comparator<Item>() {
            @Override
            public int compare(Item i1, Item i2) {
                double ratio1 = (double) i1.value / i1.weight;
                double ratio2 = (double) i2.value / i2.weight;
                return ratio2 > ratio1 ? 1 : -1;
            }
        });
        double maxKnapsackVal = 0;
        for (int i = 0; i < n; i++) {
            int value = arr[i].value;
            int weight = arr[i].weight;
            double ratio = (double) value / weight;
            // When Current Item's Weight is More than the Knapsack Capacity
            // then include a Fraction of the Item
            if (weight > W) {
                maxKnapsackVal += W * ratio;
                W = 0;
            }
            // When Current Item's Weight is Equl to the Knapsack Capacity
            // then include the complete Item
            else {
                maxKnapsackVal += value;
                W -= weight;
            }
        }
        return maxKnapsackVal;
    }

    // striver code
    private static class itemComparator implements Comparator<Item> {
        @Override
        public int compare(Item a, Item b) {
            double r1 = (double) (a.value) / (double) (a.weight);
            double r2 = (double) (b.value) / (double) (b.weight);
            if (r1 < r2) {
                return 1;
            } else if (r1 > r2) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    private static class Item {
        int value, weight;

        Item(int x, int y) {
            this.value = x;
            this.weight = y;
        }
    }
}
