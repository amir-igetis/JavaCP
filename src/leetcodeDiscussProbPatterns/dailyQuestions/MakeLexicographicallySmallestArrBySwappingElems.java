package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class MakeLexicographicallySmallestArrBySwappingElems {
    public static void main(String[] args) {
        int[] nums = {1, 5, 3, 9, 8};
        int limit = 2;
        System.out.println(Arrays.toString(lexicographicallySmallestArray(nums, limit)));
    }

    // sorting + grouping

    /// Let N be the size of nums.
    ///
    /// Time Complexity: O(N⋅logN)
    ///
    /// Sorting nums takes O(N⋅logN) time. Iterating through each element in sortedNums and updating our two maps takes O(N) time. Iterating through nums to overwrite its values with the sorted list values in each group takes a total of O(N) time. Thus, the total time complexity is O(N⋅logN).
    ///
    /// Space Complexity: O(N+S_n)≈O(N)
    ///
    /// Both our maps have a space complexity of N. The space complexity used for sorting nums depends on the language of implementation:
    ///
    /// In Java, Arrays.sort() is implemented using a variant of the Quick Sort algorithm which has a space complexity of O(logN).
    /// In C++, the sort() function is implemented as a hybrid of Quick Sort, Heap Sort, and Insertion Sort, with a worst-case space complexity of O(logN).
    /// In Python, the sort() method sorts a list using the Timsort algorithm which is a combination of Merge Sort and Insertion Sort and has a space complexity of O(N).
    ///
    /// Thus, the total space complexity is O(N+S_n)≈O(N).
    static int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int[] numsSorted = new int[nums.length];
        for (int i = 0; i < nums.length; i++) numsSorted[i] = nums[i];
        Arrays.sort(numsSorted);

        int currGroup = 0;
        HashMap<Integer, Integer> numToGroup = new HashMap<>();
        numToGroup.put(numsSorted[0], currGroup);

        HashMap<Integer, LinkedList<Integer>> groupToList = new HashMap<>();
        groupToList.put(
                currGroup,
                new LinkedList<Integer>(Arrays.asList(numsSorted[0]))
        );

        for (int i = 1; i < nums.length; i++) {
            if (Math.abs(numsSorted[i] - numsSorted[i - 1]) > limit) {
                // new group
                currGroup++;
            }

            // assign current element to group
            numToGroup.put(numsSorted[i], currGroup);

            // add element to sorted group list
            if (!groupToList.containsKey(currGroup)) {
                groupToList.put(currGroup, new LinkedList<Integer>());
            }
            groupToList.get(currGroup).add(numsSorted[i]);
        }

        // iterate through input and overwrite each element with the next element in its corresponding group
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int group = numToGroup.get(num);
            nums[i] = groupToList.get(group).pop();
        }

        return nums;

    }
}
