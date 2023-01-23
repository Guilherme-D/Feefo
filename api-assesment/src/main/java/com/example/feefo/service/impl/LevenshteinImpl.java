package com.example.feefo.service.impl;

import com.example.feefo.service.LevenshteinService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class LevenshteinImpl implements LevenshteinService {

    /**
     * Calculate the Levenshtein distance using dynamic programming
     *
     * @param input is the input to be compared
     * @param title is the job title to be compared
     * @return the Levenshtein distance between both string
     */
    private Integer calculateLevenshtein(String input, String title){

        // create a 2D matrix to store calculated values from subproblems
        int[][] dp = new int[input.length() + 1][title.length() + 1];

        // iterate over title for each character of input
        for (int i = 0; i <= input.length(); i++){
            for (int j = 0; j <= title.length(); j++) {

                if (i == 0) {
                    // If input is empty, the only option is to insert all character of title in input
                    dp[i][j] = j;

                }else if (j == 0) {
                    // If title is empty, the only option is to remove all input characters
                    dp[i][j] = i;
                }else {

                    //get number of replacements, deletions and insertions
                    int replacements = dp[i - 1][j - 1] + getReplacementNumber( input.charAt(i - 1),title.charAt(j - 1) );
                    int deletions = dp[i - 1][j] + 1;
                    int insertions = dp[i][j - 1] + 1;

                    List<Integer> operationsCount = Arrays.asList(replacements, deletions, insertions);

                    //get the minimum value from replacements, deletions and insertions
                    dp[i][j] = getMinimumValueInList(operationsCount);
                }
            }
        }

        // get the Levenshtein distance from 2D array
        return dp[input.length()][title.length()];
    }

    /**
     * Get the minimum value between the count of all the operations done
     *
     * @param c1 is the char of input to be compared
     * @param c2 is the char of job title to be compared
     * @return 0 if c1 equals c2 , 1 if  c1 different of c2
     */
    private int getReplacementNumber(char c1, char c2){
        return c1 == c2 ? 0 : 1;
    }

    /**
     * Get the minimum value between the count of all the operations done
     *
     * @param operationsSize is the list of calculated value for replace, insert and delete
     * @return the minimum value inside the list
     */
    private int getMinimumValueInList(List<Integer> operationsSize){
        return operationsSize.isEmpty()? Integer.MAX_VALUE : Collections.min(operationsSize);
    }

    /**
     * Calculate the Levenshtein distance and normalize it
     *
     * @param input is the inputted job title
     * @param title is the title to be compared
     * @return the minimum value inside the list
     */
    public Double calculateDistance(String input, String title) {
        double levenshtein = (double) this.calculateLevenshtein(input, title);
        int length = Math.max(input.length(), title.length());
        return levenshtein / length;
    }
}
