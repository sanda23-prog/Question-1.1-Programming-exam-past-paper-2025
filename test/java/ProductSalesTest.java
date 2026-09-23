/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author lusan
 */
public class ProductSalesTest {
    
    // Sample product sales data: each row = a product, each column = a month
    private final int[][] productSales = {
        {10, 20, 30},
        {40, 50, 60}
    };
    

    /**
     * Test of TotalSales method, of class ProductSales.
     */
    @Test
    
    public void CalculateTotalSales_ReturnsTotalSales() {
        // Arrange
        ProductSales ps = new ProductSales();
        int expected = 210; // 10+20+30+40+50+60

        // Act
        int actual = ps.TotalSales(productSales);

        // Assert
        assertEquals(expected, actual);
    }

    /**
     * Test of AverageSales method, of class ProductSales.
     */
    @Test
    public void AverageSales_ReturnsAverageProductSales() {
        // Arrange
        ProductSales ps = new ProductSales();
        double expected = 35.0; // 210 / 6 values

        // Act
        double actual = ps.AverageSales(productSales);

        // Assert (0.001 = allowed rounding difference for doubles)
        assertEquals(expected, actual, 0.001);
    }
}

    
   