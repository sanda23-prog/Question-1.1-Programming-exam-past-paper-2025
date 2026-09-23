/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lusan
 */
public class ProductSales implements IProductSales {
    
 // Adds up every value in the 2D array
    @Override
    public int TotalSales(int[][] productSales) {
        int total = 0;
        if (productSales == null) {
            return total;
        }
        for (int[] row : productSales) {
            for (int sale : row) {
                total += sale;
            }
        }
        return total;
    }

    // Total divided by the number of values (works for jagged arrays too)
    @Override
    public double AverageSales(int[][] productSales) {
        int count = CountSales(productSales);
        if (count == 0) {
            return 0.0;
        }
        return (double) TotalSales(productSales) / count;
    }

    // Largest value in the 2D array
    @Override
    public int MaxSale(int[][] productSales) {
        if (CountSales(productSales) == 0) {
            throw new IllegalArgumentException("No sales data supplied.");
        }
        int max = Integer.MIN_VALUE;
        for (int[] row : productSales) {
            for (int sale : row) {
                if (sale > max) {
                    max = sale;
                }
            }
        }
        return max;
    }

    // Smallest value in the 2D array
    @Override
    public int MinSale(int[][] productSales) {
        if (CountSales(productSales) == 0) {
            throw new IllegalArgumentException("No sales data supplied.");
        }
        int min = Integer.MAX_VALUE;
        for (int[] row : productSales) {
            for (int sale : row) {
                if (sale < min) {
                    min = sale;
                }
            }
        }
        return min;
    }

    // Helper: counts how many values are in the array
    private int CountSales(int[][] productSales) {
        int count = 0;
        if (productSales == null) {
            return count;
        }
        for (int[] row : productSales) {
            count += row.length;
        }
        return count;
    }
}
