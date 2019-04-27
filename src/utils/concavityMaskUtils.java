package utils;

public final class concavityMaskUtils {
    public static boolean compare(Integer[] concavityArray, int[] mask) {
        for (int i = 0; i < concavityArray.length; ++i) {
            if (mask[i] != 0 && concavityArray[i] != mask[i]) {
                return false;
            }
        }
        return true;
    }
}
