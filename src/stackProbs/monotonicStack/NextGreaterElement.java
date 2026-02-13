package stackProbs.monotonicStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class NextGreaterElement {
    public static void main(String[] args) {
        int[] arr = {5, 7, 1, 2, 6, 0};
        // int[] arr1 = nextGreaterElem(arr);
        // System.out.println("The next greater elements are ");
        // for (int i = 0; i < arr1.length; i++) {
        // System.out.println(arr1[i] + " ");
        // }
        System.out.println("The next greater elements are " + Arrays.toString(nextGreaterElemII(arr)));
    }

    static int[] nextGreaterElemII(int[] nums) {
        int[] nxtGrtElem = new int[nums.length];
        Stack<Integer> st = new Stack<>();
        for (int i = (2 * nums.length) - 1; i >= 0; i--) {
            while (!st.isEmpty() && st.peek() <= nums[i % nums.length])
                st.pop();
            if (i < nums.length) {
                if (!st.isEmpty())
                    nxtGrtElem[i] = st.peek();
                else
                    nxtGrtElem[i] = -1;
            }
            st.push(nums[i % nums.length]);
        }
        return nxtGrtElem;
    }

    static long[] nextGreaterElement(long[] arr, int n) {
        long[] nxtGrtElem = new long[n];
        Stack<Long> st = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty()) {
                if (arr[i] < st.peek())
                    break;
                st.pop();
            }
            if (st.isEmpty()) {
                nxtGrtElem[i] = -1;
            } else {
                nxtGrtElem[i] = st.peek();
            }
            st.push(arr[i]);
        }
        return nxtGrtElem;
    }

    static int[] nextGreaterElementI(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> nextGreat = new HashMap<>();
        Stack<Integer> st = new Stack<>();
        for (Integer it : nums2) {
            while (!st.isEmpty() && st.peek() < it) {
                nextGreat.put(st.pop(), it);
            }
            st.push(it);
        }
        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = nextGreat.getOrDefault(nums1[i], -1);
        }
        return nums1;
    }
}
