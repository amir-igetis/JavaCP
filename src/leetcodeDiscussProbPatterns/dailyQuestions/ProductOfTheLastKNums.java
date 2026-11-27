package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.ArrayList;

public class ProductOfTheLastKNums {
    public static void main(String[] args) {
        ProductOfNumbers productOfNumbers = new ProductOfNumbers();
        productOfNumbers.add(3);        // [3]
        productOfNumbers.add(0);        // [3,0]
        productOfNumbers.add(2);        // [3,0,2]
        productOfNumbers.add(5);        // [3,0,2,5]
        productOfNumbers.add(4);        // [3,0,2,5,4]
        System.out.println(productOfNumbers.getProduct(2)); // return 20. The product of the last 2 numbers is 5 * 4 = 20
        System.out.println(productOfNumbers.getProduct(3)); // return 40. The product of the last 3 numbers is 2 * 5 * 4 = 40
        System.out.println(productOfNumbers.getProduct(4)); // return 0. The product of the last 4 numbers is 0 * 2 * 5 * 4 = 0
        productOfNumbers.add(8);        // [3,0,2,5,4,8]
        System.out.println(productOfNumbers.getProduct(2)); // return 32. The product of the last 2 numbers is 4 * 8 = 32
    }

    // Prefix Product

    /// Let n be the number of elements added to the ProductOfNumbers object using the add method, and let k be the parameter passed to the getProduct method.
    ///
    /// Time Complexity: O(n)
    ///
    /// add method:
    ///
    /// When adding a number, the operation involves appending to the prefix_product list and updating the size. If the number is 0, the list is reset.
    /// Appending to a list and resetting the list are both O(1) operations.
    /// Therefore, the time complexity of the add method is O(1).
    /// getProduct method:
    ///
    /// The getProduct method involves a division operation to compute the product of the last k elements.
    ///
    /// Division and accessing elements in a list are O(1) operations.
    ///
    /// Therefore, the time complexity of the getProduct method is O(1).
    ///
    /// Therefore the add method runs in O(1) time per operation, and the getProduct method also runs in O(1) time per operation. For n operations, the total time complexity is O(n).
    ///
    /// Space Complexity: O(n)
    ///
    /// The prefixProduct list stores the cumulative product of the numbers added. In the worst case, when no 0 is added, the list grows linearly with the number of elements added. Therefore, the space complexity is O(n).
    private static class ProductOfNumbers {

        // Stores cumulative product of the stream
        private ArrayList<Integer> prefixProduct = new ArrayList<>();
        private int size = 0;

        public ProductOfNumbers() {
            // Initialize the product list with 1 to handle multiplication logic
            this.prefixProduct.add(1);
            this.size = 0;
        }

        public void add(int num) {
            if (num == 0) {
                // If num is 0, reset the cumulative products since multiplication
                // with 0 invalidates previous products
                this.prefixProduct = new ArrayList<Integer>();
                this.prefixProduct.add(1);
                this.size = 0;
            } else {
                // Append the cumulative product of the current number with the last
                // product
                this.prefixProduct.add(this.prefixProduct.get(size) * num);
                this.size++;
            }
        }

        public int getProduct(int k) {
            // Check if the requested product length exceeds the size of the valid
            // product list
            if (k > this.size) return 0;

            // Compute the product of the last k elements using division
            return (
                    this.prefixProduct.get(this.size) /
                            this.prefixProduct.get(this.size - k)
            );
        }
    }
}
