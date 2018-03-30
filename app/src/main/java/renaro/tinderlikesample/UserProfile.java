package renaro.tinderlikesample;

import renaro.tinderlikesample.model.RemoteProfile;

/**
 * Created by renarosantos on 05/02/17.
 */
public class UserProfile {

    public static final int DEFAULT_AGE = 18;
    private final int id;
    private final String imageUrl;
    private final String name;
    private final int age;
    private final int percentage;

    public UserProfile(final int id, final String imageUrl, final String name,
                       final int age, final int percentage) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
        this.age = age;
        this.percentage = percentage;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getPercentage() {
        return percentage;
    }

    public static UserProfile from(final RemoteProfile profile) {
        int age = getAge(profile.age);
        return new UserProfile(profile.id, profile.cover, profile.name, age, PercentageAllocator.get());
    }

    private static int getAge(final String age) {
        try {
            return Integer.parseInt(age);
        } catch (NumberFormatException e) {
            return DEFAULT_AGE;
        }
    }

    public int getId() {
        return id;
    }
}
