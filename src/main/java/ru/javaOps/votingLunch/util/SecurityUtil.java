package ru.javaOps.votingLunch.util;

public class SecurityUtil {

    private static int id = 100000;

    public static int authUserId() {
        return id;
    }

    public static void setAuthUserId(int id) {
        SecurityUtil.id = id;
    }

    public static int authRestaurantId() {
        return 100004;
    }
}
