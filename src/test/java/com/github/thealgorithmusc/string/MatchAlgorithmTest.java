package com.github.thealgorithmusc.string;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MatchAlgorithmTest {

    @Test
    public void testNaiveMatch() {
        String main, pattern;

        main = "BBC ABCDAB ABCDABCDABDE";

        pattern = "ABCDABD";
        assertEquals(15, MatchAlgorithm.naiveMatch(main, pattern));

        pattern = "ABCDAB";
        assertEquals(4, MatchAlgorithm.naiveMatch(main, pattern));

        pattern = "ABCDACE";
        assertEquals(-1, MatchAlgorithm.naiveMatch(main, pattern));
    }

    @Test
    public void testKmpMatch() {
        String main, pattern;

        main = "BBC ABCDAB ABCDABCDABDE";

        pattern = "ABCDABD";
        assertEquals(15, MatchAlgorithm.kmpMatch(main, pattern));

        pattern = "ABCDAB";
        assertEquals(4, MatchAlgorithm.kmpMatch(main, pattern));

        pattern = "ABCDACE";
        assertEquals(-1, MatchAlgorithm.kmpMatch(main, pattern));
    }

}
