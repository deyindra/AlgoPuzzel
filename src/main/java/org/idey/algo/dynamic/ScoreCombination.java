package org.idey.algo.dynamic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ScoreCombination {

    public static Set<ScoreCombo> findAllScoreCombinations(int n, int[] scoreTypes) {
        int highestScore = 0;
        for (int i = 0; i < scoreTypes.length; i++) {
            highestScore = Math.max(highestScore, scoreTypes[i]);
        }

        Map<Integer, Set<ScoreCombo>> memoizedResults = new HashMap<>();
        memoizedResults.put(0, new HashSet<>());

        for (int i = 1; i <= n; i++) {
            // create score combination set for intermediate score i
            Set<ScoreCombo> comboSet = new HashSet<>();
            for (int j = 0; j < scoreTypes.length; j++) {
                // add a new score combo to the set
                if (i == scoreTypes[j]) {
                    ScoreCombo newCombo = new ScoreCombo(scoreTypes.length);
                    newCombo.scoreCoefficients[j]++;
                    comboSet.add(newCombo);
                }

                // create a copy of the memoized results with the new result added
                if (memoizedResults.containsKey(i - scoreTypes[j])) {
                    for (ScoreCombo c : memoizedResults.get(i - scoreTypes[j])) {
                        ScoreCombo newCombo = new ScoreCombo(c);
                        newCombo.scoreCoefficients[j]++;
                        comboSet.add(newCombo);
                    }
                }
            }
            if (comboSet.size() > 0) {
                memoizedResults.put(i, comboSet);
            }

            // remove unneeded results to save memory
            memoizedResults.remove(i - highestScore);
        }

        if (memoizedResults.containsKey(n)) {
            System.out.println(memoizedResults);
            return memoizedResults.get(n);
        } else {
            return new HashSet<>();
        }
    }

    public static void displayAllScoreCombinations(int n, int[] scoreTypes) {
        System.out.println("All score combinations for score " + n);
        for (ScoreCombo c : findAllScoreCombinations(n, scoreTypes)) {
            for (int i = 0; i < c.scoreCoefficients.length; i++) {
                System.out.print(c.scoreCoefficients[i] + "x" + scoreTypes[i] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }


    public static void main(String[] args) {
        displayAllScoreCombinations(63, new int[]{1,5,10,25});
    }

}
