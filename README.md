# ProductSales: Java + NetBeans Step-by-Step Guide

Study guide for the **November 2025 past paper** (Q1.2 and Q1.3).
It shows, click by click in **NetBeans**, how to build the `ProductSales` class, its interface, and the JUnit unit tests.

## Table of Contents

- [What you will build](#what-you-will-build)
- [Step 0: Create the project](#step-0-create-the-project)
- [Q1.2: ProductSales class and interface](#q12-productsales-class-and-interface)
- [Q1.3: Unit tests](#q13-unit-tests)
- [Final project structure](#final-project-structure)
- [Exam checklist](#exam-checklist)
- [Common errors and fixes](#common-errors-and-fixes)
- [Key concepts to revise](#key-concepts-to-revise)

---

## What you will build

| Question | Task | Files |
|----------|------|-------|
| **Q1.2** | A `ProductSales` class that calculates total, average, maximum and minimum product sales. It must implement an interface. | `IProductSales.java`, `ProductSales.java`, `Main.java` |
| **Q1.3** | Unit tests for the `TotalSales` and `AverageSales` methods, placed in a test package. | `ProductSalesTest.java` |

> **Note on the interface name:** the question text says the class must implement `IProductSales`, but the code block in the paper shows `public interface IProduct`. This guide uses **`IProductSales`** to match the wording. If your paper or lecturer wants `IProduct`, rename it in the interface file **and** in the `implements` clause.

---

## Step 0: Create the project

1. Open NetBeans and go to **File > New Project**.
2. Choose **Java with Ant** (or **Java with Maven**) > **Java Application**, then click **Next**.
3. **Project Name:** `ProductSales`.
4. **Untick** the box **Create Main Class**.
5. Click **Finish**.

Your project now appears in the **Projects** panel on the left. Everything below goes under **Source Packages** (for the code) or **Test Packages** (for the tests).

---

## Q1.2: ProductSales class and interface

### The question

> Create a class named `ProductSales` that contains separate methods to calculate the total, average, maximum and minimum product sales. The `ProductSales` class must implement an `IProductSales` interface that contains the following:
>
> ```java
> int TotalSales(int[][] productSales);
> double AverageSales(int[][] productSales);
> int MaxSale(int[][] productSales);
> int MinSale(int[][] productSales);
> ```

### Step 1: Create the interface

1. In the **Projects** panel, expand `ProductSales`.
2. Right-click **Source Packages** > **New** > **Java Interface**.
   (If you don't see it, choose **Other > Java > Java Interface**.)
3. **Class Name:** `IProductSales`, then click **Finish**.
4. Click inside the new file, press **Ctrl+A** to select everything, and paste this:

**`IProductSales.java`**

```java
public interface IProductSales {
    int TotalSales(int[][] productSales);
    double AverageSales(int[][] productSales);
    int MaxSale(int[][] productSales);
    int MinSale(int[][] productSales);
}
```

5. Save with **Ctrl+S**.

### Step 2: Create the ProductSales class

1. Right-click **Source Packages** > **New** > **Java Class**.
2. **Class Name:** `ProductSales` (spelled exactly like this), then click **Finish**.
3. Press **Ctrl+A** and paste this:

**`ProductSales.java`**

```java
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
```

4. Save with **Ctrl+S**.

### Step 3: Create a Main class to try it out

1. Right-click **Source Packages** > **New** > **Java Class**.
2. **Class Name:** `Main`, then click **Finish**.
3. Press **Ctrl+A** and paste this:

**`Main.java`**

```java
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
```

4. Save with **Ctrl+S**.

### Step 4: Run it

Right-click `Main.java` in the **Projects** panel and choose **Run File** (or press **Shift+F6**).

Expected output in the **Output** window:

```
Total sales:   1195
Average sales: 132.78
Max sale:      210
Min sale:      60
```

### How the code works (be ready to explain this)

- **`implements IProductSales`** means the class promises to provide all four methods from the interface. If one is missing, NetBeans shows a red error.
- **`@Override`** tells Java the method comes from the interface.
- **`int[][]`** is a 2D array. The outer loop (`for (int[] row : productSales)`) visits each row, and the inner loop (`for (int sale : row)`) visits each value in that row.
- **`TotalSales`** starts at 0 and adds every value.
- **`AverageSales`** divides the total by the number of values. `(double)` is needed so Java doesn't cut off the decimals.
- **`MaxSale` / `MinSale`** start from `Integer.MIN_VALUE` / `Integer.MAX_VALUE`, so the first value in the array always replaces them.
- **`CountSales`** is a private helper method that counts how many values there are. It is used by `AverageSales`, `MaxSale` and `MinSale`.
- **Empty or `null` data:** total and average return 0 and 0.0. Max and min throw an `IllegalArgumentException`, because there is no sensible answer for no data.

---

## Q1.3: Unit tests

### The question

> Write unit tests for the application. Create a test package within the application you created, which will contain the necessary unit tests.

| Test Name | Test Purpose |
|-----------|--------------|
| `CalculateTotalSales_ReturnsTotalSales` | Supply the product sales data to the total product sales method. The test will determine that the correct total sales value is returned from the `TotalSales` method in the `ProductSales` class. |
| `AverageSales_ReturnsAverageProductSales` | Supply the product sales data to the average sales method. The test will determine that the correct average sales value is returned from the `AverageSales` method in the `ProductSales` class. |

### Step 1: Generate the test class

1. In the **Projects** panel, expand `ProductSales` > **Source Packages**.
2. Right-click `ProductSales.java` and choose **Tools > Create/Update Tests**.
   (If you don't see it, right-click the project and choose **New > Other > Unit Tests > JUnit Test**.)
3. The dialog shows the class name `ProductSalesTest` and the location **Test Packages**.
4. If it asks for a JUnit version, choose **JUnit 4.x** (or JUnit 5 if that is all it offers), then click **OK**.

NetBeans creates `ProductSalesTest.java` under **Test Packages**. That folder is your **test package**.

### Step 2: Replace the generated code

NetBeans fills the file with placeholder tests that use `null` data and have the wrong names. **Do not keep them.**

1. Open `ProductSalesTest.java`.
2. Press **Ctrl+A** and delete everything.
3. Paste this:

**`ProductSalesTest.java`**

```java
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ProductSalesTest {

    // Sample product sales data: each row = a product, each column = a month
    private final int[][] productSales = {
        {10, 20, 30},
        {40, 50, 60}
    };

    // ---------- REQUIRED TESTS (from the question) ----------

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

    // ---------- EXTRA TESTS (optional, not asked for in the question) ----------

    @Test
    public void MaxSale_ReturnsMaximumSale() {
        // Arrange
        ProductSales ps = new ProductSales();
        int expected = 60;

        // Act
        int actual = ps.MaxSale(productSales);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void MinSale_ReturnsMinimumSale() {
        // Arrange
        ProductSales ps = new ProductSales();
        int expected = 10;

        // Act
        int actual = ps.MinSale(productSales);

        // Assert
        assertEquals(expected, actual);
    }
}
```

4. Save with **Ctrl+S**.

> **If your project uses JUnit 5**, change the first two lines to:
>
> ```java
> import org.junit.jupiter.api.Test;
> import static org.junit.jupiter.api.Assertions.assertEquals;
> ```
>
> Everything else stays the same.
>
> **If NetBeans put a `package` line at the top of the file**, keep it above the `import` lines.

### Step 3: Fix red import errors (only if you see them)

If `org.junit` is underlined in red:

1. Under your project, right-click **Test Libraries** > **Add Library**.
2. Choose **JUnit 4.x** (and **Hamcrest** if it is listed).
3. Click **Add Library**.

### Step 4: Run the tests

1. Right-click `ProductSalesTest.java` and choose **Test File** (or press **Ctrl+F6**).
2. The **Test Results** panel at the bottom should show **green ticks** for every test.
3. Take a screenshot of the panel as proof for your submission.

### How the tests work (be ready to explain this)

- **`@Test`** marks a method as a unit test.
- **Arrange, Act, Assert** is the standard pattern:
  1. **Arrange:** create the object and set the expected result.
  2. **Act:** call the method being tested.
  3. **Assert:** check that the actual result matches the expected result.
- **Test data:** `{{10, 20, 30}, {40, 50, 60}}` gives a total of **210** and an average of **35.0** (210 divided by 6 values).
- **`assertEquals(expected, actual)`** is used for `int` values.
- **`assertEquals(expected, actual, 0.001)`** is used for `double` values. The third number is the allowed difference, because decimals can have tiny rounding errors.
- **Test names** must match the question exactly: `CalculateTotalSales_ReturnsTotalSales` and `AverageSales_ReturnsAverageProductSales`.

---

## Final project structure

```
ProductSales
├── Source Packages
│   ├── IProductSales.java
│   ├── Main.java
│   └── ProductSales.java
└── Test Packages
    └── ProductSalesTest.java
```

---

## Exam checklist

- [ ] Project created with **Create Main Class unticked**
- [ ] `IProductSales` interface has all four methods
- [ ] `ProductSales` says `implements IProductSales`
- [ ] All four methods use `@Override`
- [ ] `AverageSales` returns a `double` (uses the `(double)` cast)
- [ ] Main class runs and prints correct results
- [ ] Test class is inside **Test Packages**
- [ ] Test names match the question exactly (spelling and capital letters)
- [ ] Tests use real data (not `null`)
- [ ] All tests show green ticks
- [ ] Screenshot of the Test Results panel taken
- [ ] Every file saved (Ctrl+S)

---

## Common errors and fixes

| Problem | Fix |
|---------|-----|
| Red underline on `org.junit` | Right-click **Test Libraries > Add Library > JUnit 4.x** (see Q1.3, Step 3). |
| `cannot find symbol: ProductSales` | Make sure all files are in the same package. If one file has a `package ...;` line at the top, add the same line to the others. |
| Class name error | The file name must match the class name exactly, including capital letters. |
| `does not override abstract method` | A method is missing or spelled differently from the interface. Names and parameters must match exactly. |
| Tests fail with `IllegalArgumentException` | A test is passing `null` or an empty array to `MaxSale` / `MinSale`. Use real data. |
| Average test fails by a tiny amount | Use `assertEquals(expected, actual, 0.001)` with the third number (delta). |
| Output window shows nothing | Run **Main.java** with Shift+F6, not the test file. |

---

## Key concepts to revise

- **Interface:** a contract that lists method signatures. A class that implements it must provide every method.
- **2D arrays (`int[][]`):** an array of arrays. Use nested loops to visit every value.
- **Enhanced for loop:** `for (int sale : row)` visits each element without needing an index.
- **Type casting:** `(double) total / count` prevents integer division from cutting off decimals.
- **Unit testing:** checking one small piece of code (a method) against a known expected result.
- **Arrange, Act, Assert:** the structure of a clear unit test.
- **Delta in `assertEquals`:** the allowed difference when comparing `double` values.
