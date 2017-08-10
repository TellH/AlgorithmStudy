package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of distinct integers, S, return all possible subsets.
 * 动态规划
 *
 * Given a string S and a string T, count the number of distinct subsequences of T in S.
 A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie,"ACE"is a subsequence of"ABCDE"while"AEC"is not).
 Here is an example:
 S ="rabbbit", T ="rabbit"
 Return3.
 */
public class SubSets {
    public List<List<Integer>> subsets(int[] S) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < S.length; i++) {
            int temp = S[i];
            if (result.isEmpty()){
                result.add(new ArrayList<>(Arrays.asList(temp)));
                result.add(new ArrayList<>());
            }else {
                ArrayList<ArrayList<Integer>> tempList = new ArrayList<>();
                for (List<Integer> list : result) {
                    ArrayList<Integer> newSet = new ArrayList<>(list);
                    newSet.add(temp);
                    tempList.add(newSet);
                }
                result.addAll(tempList);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new SubSets().subsets(new int[]{1,2,3}));
    }
}
