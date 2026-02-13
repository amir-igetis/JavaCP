package striverAToZ.heaps.hardProbs;

import java.util.*;

public class DesignTwitter {
    public static void main(String[] args) {
        Twitter twitter = new Twitter();

        twitter.postTweet(1, 2);
        twitter.postTweet(2, 6);

        System.out.println(twitter.getNewsFeed(1)); // [2]
        twitter.follow(1, 2);
        System.out.println(twitter.getNewsFeed(1)); // [6,2]
        twitter.unfollow(1, 2);
        twitter.postTweet(1, 7);
        System.out.println(twitter.getNewsFeed(1)); // [7,2]
    }

    // tc & sc O(1) & O(K)
    private static class Twitter {
        // Map to store user's tweets (timestamp, tweetId)
        Map<Integer, List<int[]>> tweets;
        // Map to store who follows whom
        Map<Integer, Set<Integer>> following;
        // Time counter to give each tweet a unique timestamp
        int time;

        // Constructor to initialize the system
        public Twitter() {
            tweets = new HashMap<>();
            following = new HashMap<>();
            time = 0;
        }

        // Function to post a new tweet
        public void postTweet(int userId, int tweetId) {
            tweets.putIfAbsent(userId, new ArrayList<>());
            tweets.get(userId).add(new int[]{time++, tweetId});
        }

        // Function to retrieve 10 most recent tweet IDs in the news feed
        public List<Integer> getNewsFeed(int userId) {
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

            // Add own tweets
            if (tweets.containsKey(userId)) {
                for (int[] tweet : tweets.get(userId)) {
                    pq.offer(tweet);
                    if (pq.size() > 10)
                        pq.poll();
                }
            }

            // Add tweets from followed users
            if (following.containsKey(userId)) {
                for (int followee : following.get(userId)) {
                    if (tweets.containsKey(followee)) {
                        for (int[] tweet : tweets.get(followee)) {
                            pq.offer(tweet);
                            if (pq.size() > 10)
                                pq.poll();
                        }
                    }
                }
            }

            // Retrieve tweets in reverse order
            LinkedList<Integer> res = new LinkedList<>();
            while (!pq.isEmpty()) {
                res.addFirst(pq.poll()[1]);
            }
            return res;
        }

        // Function to follow another user
        public void follow(int followerId, int followeeId) {
            following.putIfAbsent(followerId, new HashSet<>());
            following.get(followerId).add(followeeId);
        }

        // Function to unfollow another user
        public void unfollow(int followerId, int followeeId) {
            if (following.containsKey(followerId))
                following.get(followerId).remove(followeeId);
        }
    }
}
