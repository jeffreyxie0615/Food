public class Food {
    private String type;
    private Picture info;
    private int rating;

    public Food(String type, String url, String label, int rating) {
        this.type = type;
        this.info = new Picture(url, label);
        this.rating = rating;
    }
}
