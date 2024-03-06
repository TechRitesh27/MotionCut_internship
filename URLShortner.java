// URL shortner code by Ritesh Ukade
// Second internship project at motioncut

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class URLShortener {
    private Map<String, String> shortToLongMap;
    private Map<String, String> longToShortMap;
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_URL_LENGTH = 6;

    public URLShortener() {
        shortToLongMap = new HashMap<>();
        longToShortMap = new HashMap<>();
    }

    public String shortenURL(String longURL) {
        if (longToShortMap.containsKey(longURL)) {
            return longToShortMap.get(longURL);
        }

        String shortURL = generateShortURL();
        shortToLongMap.put(shortURL, longURL);
        longToShortMap.put(longURL, shortURL);
        return shortURL;
    }

    public String expandURL(String shortURL) {
        return shortToLongMap.getOrDefault(shortURL, "Invalid short URL");
    }

    private String generateShortURL() {
        Random random = new Random();
        StringBuilder shortURL = new StringBuilder();
        boolean collision;
        do {
            collision = false;
            for (int i = 0; i < SHORT_URL_LENGTH; i++) {
                shortURL.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
            }
            if (shortToLongMap.containsKey(shortURL.toString())) {
                collision = true;
                shortURL.setLength(0);
            }
        } while (collision);
        return shortURL.toString();
    }

    public static void main(String[] args) {
        URLShortener urlShortener = new URLShortener();
        System.out.println("Welcome to URL Shortener!");

        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Shorten a URL");
            System.out.println("2. Expand a URL");
            System.out.println("3. Exit");

            // Take user input
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    shortenURL(urlShortener);
                    break;
                case 2:
                    expandURL(urlShortener);
                    break;
                case 3:
                    System.out.println("Thank you for using URL Shortener. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static int getUserChoice() {
        int choice;
        try {
            choice = Integer.parseInt(System.console().readLine());
        } catch (NumberFormatException e) {
            choice = -1;
        }
        return choice;
    }

    // Helper method to shorten a URL
    private static void shortenURL(URLShortener urlShortener) {
        System.out.print("Enter the long URL: ");
        String longURL = System.console().readLine();
        String shortURL = urlShortener.shortenURL(longURL);
        System.out.println("Shortened URL: " + shortURL);
    }

    // Helper method to expand a URL
    private static void expandURL(URLShortener urlShortener) {
        System.out.print("Enter the short URL: ");
        String shortURL = System.console().readLine();
        String longURL = urlShortener.expandURL(shortURL);
        System.out.println("Expanded URL: " + longURL);
    }
}
