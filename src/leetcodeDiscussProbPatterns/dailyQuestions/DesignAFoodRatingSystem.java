package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class DesignAFoodRatingSystem {

    private Map<String, String> foodToCuisine;
    private Map<String, Integer> foodToRating;
    private Map<String, PriorityQueue<Food>> cuisineToHeap;

    public DesignAFoodRatingSystem(String[] foods, String[] cuisines, int[] ratings) {
        foodToCuisine = new HashMap<>();
        foodToRating = new HashMap<>();
        cuisineToHeap = new HashMap<>();

        for (int i = 0; i < foods.length; i++) {
            String food = foods[i];
            String cuisine = cuisines[i];
            int rating = ratings[i];

            foodToCuisine.put(food, cuisine);
            foodToRating.put(food, rating);

            cuisineToHeap.putIfAbsent(cuisine, new PriorityQueue<>((a, b) -> {
                if (b.rating != a.rating) {
                    return b.rating - a.rating;
                }
                return a.name.compareTo(b.name);
            }));

            cuisineToHeap.get(cuisine).offer(new Food(food, rating));
        }
    }

    public void changeRating(String food, int newRating) {
        foodToRating.put(food, newRating);
        String cuisine = foodToCuisine.get(food);
        cuisineToHeap.get(cuisine).offer(new Food(food, newRating));

    }

    public String highestRated(String cuisine) {
        PriorityQueue<Food> pq = cuisineToHeap.get(cuisine);
        while (true) {
            Food top = pq.peek();
            if (foodToRating.get(top.name) == top.rating)
                return top.name;

            pq.poll();
        }
    }


}

class Food {
    String name;
    int rating;

    Food(String n, int r) {
        name = n;
        rating = r;
    }
}

//class Main {
//    public static void main(String[] args) {
//        DesignAFoodRatingSystem foodRatings = new DesignAFoodRatingSystem(
//                new String[]{"kimchi", "miso", "sushi", "moussaka", "ramen", "bulgogi"},
//                new String[]{"korean", "japanese", "japanese", "greek", "japanese", "korean"},
//                new int[]{9, 12, 8, 15, 14, 7}
//        );
//
//        System.out.println(foodRatings.highestRated("korean"));   // "kimchi"
//        System.out.println(foodRatings.highestRated("japanese")); // "ramen"
//        foodRatings.changeRating("sushi", 16);
//        System.out.println(foodRatings.highestRated("japanese")); // "sushi"
//        foodRatings.changeRating("ramen", 16);
//        System.out.println(foodRatings.highestRated("japanese")); // "ramen"
//
//    }
//}
