package renaro.tinderlikesample;

/**
 * Created by mohammed on 3/30/18.
 */

public class PercentageAllocator {

    private static int value = 30;

    public static int get() {
        int oldValue = value;
        value = (oldValue + 30) % 100;
        return oldValue;
    }
}