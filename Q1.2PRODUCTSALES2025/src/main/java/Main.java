/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lusan
 */
public class Main {
    
public static void main(String[] args) {
        // Each row = a product, each column = a month
        int[][] sales = {
            {120, 150, 90},
            {200, 180, 210},
            {75, 60, 110}
        };

        IProductSales ps = new ProductSales();

        System.out.println("Total sales:   " + ps.TotalSales(sales));
        System.out.printf("Average sales: %.2f%n", ps.AverageSales(sales));
        System.out.println("Max sale:      " + ps.MaxSale(sales));
        System.out.println("Min sale:      " + ps.MinSale(sales));
    }
}
