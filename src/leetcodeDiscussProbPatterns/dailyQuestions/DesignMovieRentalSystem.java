package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.*;

public class DesignMovieRentalSystem {


    static class Pair {
        int price, shop;

        Pair(int price, int shop) {
            this.price = price;
            this.shop = shop;
        }
    }

    static class MovieInfo {
        int price, shop, movie;

        MovieInfo(int price, int shop, int movie) {
            this.price = price;
            this.shop = shop;
            this.movie = movie;
        }
    }

    private Map<String, Integer> priceMap;
    private Map<Integer, TreeSet<Pair>> unrented;
    private TreeSet<MovieInfo> rented;

    public DesignMovieRentalSystem(int n, int[][] entries) {
        priceMap = new HashMap<>();
        unrented = new HashMap<>();
        rented = new TreeSet<>((a, b) -> {
            if (a.price != b.price) return a.price - b.price;
            if (a.shop != b.shop) return a.shop - b.shop;
            return a.movie - b.movie;
        });

        for (int[] e : entries) {
            int shop = e[0], movie = e[1], price = e[2];
            priceMap.put(key(shop, movie), price);

            unrented.computeIfAbsent(movie, k -> new TreeSet<>(
                    (p1, p2) -> p1.price != p2.price ? p1.price - p2.price : p1.shop - p2.shop
            ));
            unrented.get(movie).add(new Pair(price, shop));
        }
    }

    public List<Integer> search(int movie) {
        List<Integer> ans = new ArrayList<>();
        if (unrented.containsKey(movie)) {
            Iterator<Pair> it = unrented.get(movie).iterator();
            int k = 0;
            while (it.hasNext() && k < 5) {
                ans.add(it.next().shop);
                k++;
            }
        }
        return ans;
    }

    public void rent(int shop, int movie) {
        int price = priceMap.get(key(shop, movie));
        Pair p = new Pair(price, shop);
        unrented.get(movie).remove(p);
        rented.add(new MovieInfo(price, shop, movie));
    }

    public void drop(int shop, int movie) {
        int price = priceMap.get(key(shop, movie));
        rented.remove(new MovieInfo(price, shop, movie));
        unrented.get(movie).add(new Pair(price, shop));
    }

    public List<List<Integer>> report() {
        List<List<Integer>> ans = new ArrayList<>();
        Iterator<MovieInfo> it = rented.iterator();
        int k = 0;
        while (it.hasNext() && k < 5) {
            MovieInfo info = it.next();
            ans.add(Arrays.asList(info.shop, info.movie));
            k++;
        }
        return ans;
    }

    private String key(int shop, int movie) {
        return shop + "#" + movie;
    }
}

//class Main {
//    public static void main(String[] args) {
//        int[][] entries = {
//                {0, 1, 5}, {0, 2, 6}, {0, 3, 7},
//                {1, 1, 4}, {1, 2, 7}, {2, 1, 5}
//        };
//
//        MovieRentingSystem movieSystem = new MovieRentingSystem(3, entries);
//
//        // search movie 1
//        List<Integer> res1 = movieSystem.search(1);
//        System.out.println("Search(1): " + res1);
//
//        // rent (0,1)
//        movieSystem.rent(0, 1);
//
//        // rent (1,2)
//        movieSystem.rent(1, 2);
//
//        // report
//        List<List<Integer>> report1 = movieSystem.report();
//        System.out.println("Report: " + report1);
//
//        // drop (1,2)
//        movieSystem.drop(1, 2);
//
//        // search movie 2
//        List<Integer> res2 = movieSystem.search(2);
//        System.out.println("Search(2): " + res2);
//    }
//}
