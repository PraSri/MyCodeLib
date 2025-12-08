package binarysearch;

import java.util.*;

public class KokoEatingBananas {

    // Input: piles = [1,4,3,2], h = 9
    //
    //Output: 2

    //Explanation: With an eating rate of 2, you can eat the bananas in 6 hours.
    // With an eating rate of 1, you would need 10 hours to eat all the bananas (which exceeds h=9),
    // thus the minimum eating rate is 2.

    // tumhe aisa number dhoona hai jisme tum h hours ke andar bananas kahatam kr pao, aur har hour tum k bananas hi
    // kha skte ho. wo k minimum hona chahiye

    public int minEatingSpeed(int[] piles, int h) {
        int s = 1; // kam se kam tumhe ek banana toh khana hi hoga khatam krne ko

        int e = Arrays.stream(piles).max().getAsInt(); // maximum tum har baar sabse jada wala pile ke barabar agr khaoge
        // toh sabse jaldi khatam hoga

        // hume iske beech me ek value dhundni hai, jo h se choti ho aur minimum ho

        // hum binary search krege in the range [1, max(piles[])]
        // ek example lete hai
        // piles = [1,4,3,2], h = 9
        // range = [1, 4]

        while(s < e) {

            int mid = s + (e-s)/2;

            // maine range [1,4] me mid value nikali
            // wo hai 2
            // abb main dekhuga ki 2, kya possible answer jo ye satisfy kre
            // ki main har ghante 2 banana khao aur saare banana 9 ghante me khatam ho jae
            // piles = [ 1 -> 1h, 4 -> 2h, 3 -> 2h, 2 -> 1h] total hours = 6h
            // toh ye ek answer ho skta hai
            // but ye minimum value hai ki nahi, iske liye hume binary search continue krna padega
            if(isPossible(piles, mid, h)) {
                e = mid; // e = 2
            } else {
                s = mid + 1;
            }
        }

        return s;
    }

    private boolean isPossible(int[] piles, int bananasAllowedPerHour, int expectedHours) {
        int actualHours = 0;
        int i = 0;
        int n = piles.length;
        while(i < n) {
            // time to eat ith pile
            int timeTakenPerPile = piles[i]/bananasAllowedPerHour;
            actualHours += timeTakenPerPile;
            if(piles[i]%bananasAllowedPerHour!=0) {
                actualHours++;
            }
            i++;
        }
        return actualHours<=expectedHours;
    }

}
