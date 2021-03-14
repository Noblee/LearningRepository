package leetcode;

import java.util.*;

public class LC10正则表达式匹配 {
    static class NFANode {

        public Map<Character, List<NFANode>> node = new HashMap<>();


    }

    public boolean isMatch(String s, String p) {

        NFANode head = new NFANode();
        NFANode curHead = head;


        for (int i = 0; i < p.length(); i++) {
            NFANode next = new NFANode();

            if ((Character.isAlphabetic(p.charAt(i)) || p.charAt(i) == '.') && i + 1 < p.length() && p.charAt(i + 1) == '*') {
                curHead.node.put(p.charAt(i), Arrays.asList(curHead, next));
                curHead.node.put('-', Arrays.asList(next));
                i++;
            } else if ((Character.isAlphabetic(p.charAt(i)) || p.charAt(i) == '.') && i + 1 < p.length() && p.charAt(i + 1) == '+') {
                curHead.node.put(p.charAt(i), Arrays.asList(curHead, next));
                i++;
            } else {
                curHead.node.put(p.charAt(i), Arrays.asList(next));
            }
            curHead = next;
        }
        curHead.node.put('%', null);
        char[] chars = s.toCharArray();

        return doIsMatch(chars, 0, head);
    }

    public boolean doIsMatch(char[] chars, int cur, NFANode dfa) {
        boolean ret = false;
        if (cur == chars.length) {
            if (dfa.node.containsKey('%')) {
                return ret|true;
            } else {
                return ret|false;
            }
        }
        List<NFANode> nodes = new ArrayList<>();
        if (dfa.node.containsKey(chars[cur])) nodes.addAll(dfa.node.get(chars[cur]));
        if (dfa.node.containsKey('.')) nodes.addAll(dfa.node.get('.'));

        if (nodes != null) {
            for (NFANode node : nodes) {
                ret |= doIsMatch(chars, cur + 1, node);
            }
        }
        if (dfa.node.containsKey('-')) {
            if(!ret) {
                ret |= doIsMatch(chars, cur, dfa.node.get('-').get(0));
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        LC10正则表达式匹配 lc10正则表达式匹配 = new LC10正则表达式匹配();
        System.out.println(lc10正则表达式匹配.isMatch("aaaaaaaaaaaaab", "a*a*a*a*a*a*a*a*a*a*a*a*c"));
    }
}
