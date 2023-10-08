package com;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CurrencyConverter {
    private Map<String, Double> exchangeRates;
    private List<String> favoriteCurrencies;

    public CurrencyConverter() {
        exchangeRates = new HashMap<>();
        favoriteCurrencies = new ArrayList<>();
        // Initialize exchange rates (you should fetch these from a real API)
        exchangeRates.put("USD", 1.0);
        exchangeRates.put("EUR", 0.85);
        exchangeRates.put("GBP", 0.73);
        exchangeRates.put("JPY", 110.0);
    }

    public void addFavoriteCurrency(String currencyCode) {
        if (!favoriteCurrencies.contains(currencyCode)) {
            favoriteCurrencies.add(currencyCode);
            System.out.println(currencyCode + " added to favorites.");
        } else {
            System.out.println(currencyCode + " is already in favorites.");
        }
    }

    public void viewFavoriteCurrencies() {
        if (favoriteCurrencies.isEmpty()) {
            System.out.println("No favorite currencies added.");
        } else {
            System.out.println("Favorite currencies:");
            for (String currency : favoriteCurrencies) {
                System.out.println(currency);
            }
        }
    }

    public double convertCurrency(double amount, String fromCurrency, String toCurrency) {
        if (exchangeRates.containsKey(fromCurrency) && exchangeRates.containsKey(toCurrency)) {
            double fromRate = exchangeRates.get(fromCurrency);
            double toRate = exchangeRates.get(toCurrency);
            return amount * (toRate / fromRate);
        } else {
            System.out.println("Invalid currency codes.");
            return -1.0;
        }
    }

    public static void main(String[] args) {
        CurrencyConverter converter = new CurrencyConverter();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nCurrency Converter Menu:");
            System.out.println("1. Add favorite currency");
            System.out.println("2. View favorite currencies");
            System.out.println("3. Convert currency");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter currency code to add to favorites: ");
                    String favoriteCurrency = scanner.nextLine().toUpperCase();
                    converter.addFavoriteCurrency(favoriteCurrency);
                    break;
                case 2:
                    converter.viewFavoriteCurrencies();
                    break;
                case 3:
                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();
                    System.out.print("Enter source currency code: ");
                    String fromCurrency = scanner.next().toUpperCase();
                    System.out.print("Enter target currency code: ");
                    String toCurrency = scanner.next().toUpperCase();
                    double result = converter.convertCurrency(amount, fromCurrency, toCurrency);
                    if (result >= 0) {
                        System.out.println("Converted amount: " + result + " " + toCurrency);
                    }
                    break;
                case 4:
                    System.out.println("Exiting Currency Converter.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
