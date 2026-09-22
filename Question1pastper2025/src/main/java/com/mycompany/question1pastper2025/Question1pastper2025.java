/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.question1pastper2025;

/**
 *
 * @author lusan
 */
public class Question1pastper2025 {

    public static void main(String[] args) {
        
    // 2D Array storing sales data: 2 Years x 3 Quarters
        int[][] salesData = {
            {300, 150, 700}, // Year 1
            {250, 200, 600}  // Year 2
        };

        int totalSales = 0;
        int totalEntries = 0;
        int maxSale = salesData[0][0];
        int minSale = salesData[0][0];

        // Loop through the 2D array to process total, count, max, and min
        for (int row = 0; row < salesData.length; row++) {
            for (int col = 0; col < salesData[row].length; col++) {
                int currentSale = salesData[row][col];
                
                totalSales += currentSale;
                totalEntries++;

                if (currentSale > maxSale) {
                    maxSale = currentSale;
                }

                if (currentSale < minSale) {
                    minSale = currentSale;
                }
            }
        }

        // Calculate integer average as shown in the expected output
        int averageSales = totalSales / totalEntries;

        // Display the formatted Product Sales Report
        System.out.println("PRODUCT SALES REPORT - 2025");
        System.out.println("----------------------------------");
        System.out.printf("%-15s %d%n", "Total sales:", totalSales);
        System.out.printf("%-15s %d%n", "Average sales:", averageSales);
        System.out.printf("%-15s %d%n", "Maximum sale:", maxSale);
        System.out.printf("%-15s %d%n", "Minimum sale:", minSale);
        System.out.println("----------------------------------");
    }
}