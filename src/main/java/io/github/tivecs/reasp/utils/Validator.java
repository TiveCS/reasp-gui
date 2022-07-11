package io.github.tivecs.reasp.utils;

public class Validator {

    public static boolean numIsIntercept(int min, int max, int num){
        return min <= num && num <= max;
    }

    public static boolean intervalIsIntercept(int min, int max, int minInterval, int maxInterval){
        return numIsIntercept(min, max, minInterval) || numIsIntercept(min, max, maxInterval);
    }

}
