package org.idey.algo.dynamic;

import java.util.Arrays;

public class ScoreCombo {
    int[] scoreCoefficients;

    public ScoreCombo(int numTypes) {
        scoreCoefficients = new int[numTypes];
    }

    public ScoreCombo(ScoreCombo copy) {
        scoreCoefficients = new int[copy.scoreCoefficients.length];
        for (int i = 0; i < scoreCoefficients.length; i++) {
            scoreCoefficients[i] = copy.scoreCoefficients[i];
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ScoreCombo)) {
            return false;
        }

        ScoreCombo c = (ScoreCombo) o;
        if (scoreCoefficients.length != c.scoreCoefficients.length) {
            return false;
        }
        for (int i = 0; i < scoreCoefficients.length; i++) {
            if (scoreCoefficients[i] != c.scoreCoefficients[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hashCode = 0;
        for (int i = 0; i < scoreCoefficients.length; i++) {
            hashCode += scoreCoefficients[i];
            hashCode = hashCode << 1;
        }
        return hashCode;
    }

    @Override
    public String toString() {
        return Arrays.toString(scoreCoefficients);
    }
}
