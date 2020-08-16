package step1;

public class StringUtils {
    public static boolean isEmpty(String target) {
        return target == null || target.isEmpty();
    }

    public static int parseInt(String target) {
        try {
            return Integer.parseInt(target);
        } catch (NumberFormatException e) {
            throw new RuntimeException(String.format("Passed wrong formatted string %s", target), e);
        }
    }
}
