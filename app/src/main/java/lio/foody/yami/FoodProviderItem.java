package lio.foody.yami;

public class FoodProviderItem {
    int restaurantImage;
    String restaurantName, restaurantDescription, restaurantDistance, currentRating="Rated null,", currentReviews="0 Reviews";

    public FoodProviderItem(int restaurantImage, String restaurantName, String restaurantDescription, String restaurantDistance) {
        this.restaurantImage = restaurantImage;
        this.restaurantName = restaurantName;
        this.restaurantDescription = restaurantDescription;
        this.restaurantDistance = restaurantDistance+" away from you";
    }

    public FoodProviderItem(int restaurantImage, String restaurantName, String restaurantDescription, String restaurantDistance, String currentRating, String currentReviews) {
        this.restaurantImage = restaurantImage;
        this.restaurantName = restaurantName;
        this.restaurantDescription = restaurantDescription;
        this.restaurantDistance = restaurantDistance+" away from you";
        this.currentRating = "Rated "+currentRating+",";
        this.currentReviews = currentReviews+" Reviews";
    }

    public String getCurrentRating() {
        return currentRating;
    }

    public void setCurrentRating(String currentRating) {
        this.currentRating = currentRating;
    }

    public String getCurrentReviews() {
        return currentReviews;
    }

    public void setCurrentReviews(String currentReviews) {
        this.currentReviews = currentReviews;
    }

    public int getRestaurantImage() {
        return restaurantImage;
    }

    public void setRestaurantImage(int restaurantImage) {
        this.restaurantImage = restaurantImage;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantDescription() {
        return restaurantDescription;
    }

    public void setRestaurantDescription(String restaurantDescription) {
        this.restaurantDescription = restaurantDescription;
    }

    public String getRestaurantDistance() {
        return restaurantDistance;
    }

    public void setRestaurantDistance(String restaurantDistance) {
        this.restaurantDistance = restaurantDistance;
    }
}
