package paper;

import java.util.*;

/**
 * 题目9：实时排行榜 Top-K
 *
 * 算法层面：跳表 / 红黑树（TreeMap）实现
 * 工程层面：Redis ZSET 方案
 *
 * UpdateScore: O(log N)
 * TopK: O(K)
 * GetRank: O(log N)
 *
 * follow-up: 好友排行榜 → 查询时过滤好友集合，或为每个用户维护好友子 ZSET
 */
public class Leaderboard {

//    static class UserScore implements Comparable<UserScore> {
//        int userId;
//        int score;
//
//        UserScore(int userId, int score) {
//            this.userId = userId;
//            this.score = score;
//        }
//
//        @Override
//        public int compareTo(UserScore o) {
//            if (this.score != o.score) return Integer.compare(o.score, this.score); // 分数降序
//            return Integer.compare(this.userId, o.userId); // 同分按 userId 升序
//        }
//
//        @Override
//        public String toString() {
//            return "User(" + userId + ", score=" + score + ")";
//        }
//    }
//
//    private final TreeSet<UserScore> board = new TreeSet<>();
//    private final Map<Integer, UserScore> userMap = new HashMap<>();
//
//    public void updateScore(int userId, int score) {
//        if (userMap.containsKey(userId)) {
//            UserScore old = userMap.get(userId);
//            board.remove(old);
//        }
//        UserScore us = new UserScore(userId, score);
//        board.add(us);
//        userMap.put(userId, us);
//    }
//
//    public List<UserScore> topK(int k) {
//        List<UserScore> result = new ArrayList<>();
//        Iterator<UserScore> it = board.iterator();
//        while (it.hasNext() && result.size() < k) {
//            result.add(it.next());
//        }
//        return result;
//    }
//
//    public int getRank(int userId) {
//        if (!userMap.containsKey(userId)) return -1;
//        UserScore us = userMap.get(userId);
//        return board.headSet(us).size() + 1;
//    }

    public static void main(String[] args) {
//        Leaderboard lb = new Leaderboard();
//        lb.updateScore(1, 500);
//        lb.updateScore(2, 800);
//        lb.updateScore(3, 650);
//        lb.updateScore(4, 800);
//        lb.updateScore(5, 300);
//
//        System.out.println("Top 3: " + lb.topK(3));
//        System.out.println("User 3 rank: " + lb.getRank(3));
//
//        lb.updateScore(1, 900);
//        System.out.println("After update - Top 3: " + lb.topK(3));
//        System.out.println("User 1 rank: " + lb.getRank(1));
    }
}
