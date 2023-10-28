

import java.util.Scanner;

public class MatrixProcessing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Add matrices (Додати матрицю)");
            System.out.println("2. Multiply matrix by a constant (Помножити матрицю на константу)");
            System.out.println("3. Multiply matrices (Множення матриць)");
            System.out.println("4. Transpose matrix (Транспонувати матрицю)");
            System.out.println("5. Calculate a determinant (Обчисліть визначник)");
            System.out.println("6. Inverse matrix (Обернена матриця)");
            System.out.println("0. Exit");
            System.out.print("\nEnter your choice: 1, 2, 3, 4, 5, 6 or 0: ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                System.out.println("ATTENTION! The size of the two matrices must be the same!(Розмір двох матриць повинен бути однаковим!)");
                addMatrices();
            } else if (choice.equals("2")) {
                multiplyByConstant();
            } else if (choice.equals("3")) {
                multiplyMatrices();
            } else if (choice.equals("4")) {
                transposeMatrix();
            } else if (choice.equals("5")) {
                calculateDeterminant();
            } else if (choice.equals("6")) {
                inverseMatrix();
            } else if (choice.equals("0")) {
                System.out.println("Have a nice day!");
                break;
            } else {
                System.out.println("Incorrect input, try again. (Невірно введено, повторіть спробу.)");
            }
        }
    }

    public static void addMatrices() {
        int[][] matrixA = getMatrix();
        int[][] matrixB = getMatrix();

        if (matrixA.length != matrixB.length || matrixA[0].length != matrixB[0].length) {
            System.out.println("The operation cannot be performed.(Операція не може бути виконана.)");
            System.out.println("The size of the second matrix must be the same as size of the first matrix! (Розмір другої матриці повинен бути таким самим, як розмір першої матриці!)");
        } else {
            System.out.println("The result is (Резульат):");
            for (int i = 0; i < matrixA.length; i++) {
                for (int j = 0; j < matrixA[0].length; j++) {
                    int sum = matrixA[i][j] + matrixB[i][j];
                    System.out.print(sum + " ");
                }
                System.out.println();
            }
        }
    }

    public static void multiplyByConstant() {
        int[][] matrix = getMatrix();
        Scanner scanner = new Scanner(System.in);
        double constant = 0;

        while (true) {
            try {
                System.out.print("Enter constant:\n> ");
                constant = Double.parseDouble(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Incorrect input! You must enter a number and only one. (Невірне введення! Ви повинні ввести число і тільки одне.)");
            }
        }

        System.out.println("The result is (Резульат) :");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int result = (int) (matrix[i][j] * constant);
                System.out.print(result + "  ");
            }
            System.out.println();
        }
    }

    public static void multiplyMatrices() {
        int[][] matrixA = getMatrix();
        int[][] matrixB = getMatrix();

        if (matrixA[0].length != matrixB.length) {
            System.out.println("The operation cannot be performed.(Операція не може бути виконана.)");
            System.out.println("The number of columns in the first matrix must be equal to the number of rows in the second matrix. (Кількість стовпців у першій матриці повинна бути рівною кількості рядків у другій матриці.)");
        } else {
            System.out.println("The result is (Резульат):");
            int[][] result = new int[matrixA.length][matrixB[0].length];
            for (int i = 0; i < matrixA.length; i++) {
                for (int j = 0; j < matrixB[0].length; j++) {
                    int sum = 0;
                    for (int k = 0; k < matrixA[0].length; k++) {
                        sum += matrixA[i][k] * matrixB[k][j];
                    }
                    result[i][j] = sum;
                    System.out.print(result[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

    public static void transposeMatrix() {
        System.out.println("1. Main diagonal 2. Side diagonal 3. Vertical line 4. Horizontal line");

        Scanner scanner = new Scanner(System.in);
        int[][] matrix = getMatrix();
        int choice;

        while (true) {
            try {
                System.out.print("\nEnter your choice: 1, 2, 3 or 4:\n> ");
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > 4) {
                    System.out.println("Incorrect input, try again (Спробуйте знову)");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Incorrect input, try again (Спробуйте знову)");
            }
        }

        int[][] result;
        switch (choice) {
            case 1: // Main diagonal
                result = new int[matrix[0].length][matrix.length];
                for (int i = 0; i < matrix.length; i++) {
                    for (int j = 0; j < matrix[0].length; j++) {
                        result[j][i] = matrix[i][j];
                    }
                }
                break;
            case 2: // Side diagonal
                result = new int[matrix[0].length][matrix.length];
                for (int i = 0; i < matrix.length; i++) {
                    for (int j = 0; j < matrix[0].length; j++) {
                        result[result.length - j - 1][result[0].length - i - 1] = matrix[i][j];
                    }
                }
                break;
            case 3: // Vertical line
                result = new int[matrix.length][matrix[0].length];
                for (int i = 0; i < matrix.length; i++) {
                    for (int j = 0; j < matrix[0].length; j++) {
                        result[i][result[0].length - j - 1] = matrix[i][j];
                    }
                }
                break;
            case 4: // Horizontal line
                result = new int[matrix.length][matrix[0].length];
                for (int i = 0; i < matrix.length; i++) {
                    for (int j = 0; j < matrix[0].length; j++) {
                        result[result.length - i - 1][j] = matrix[i][j];
                    }
                }
                break;
            default:
                result = new int[0][0];
        }

        System.out.println("The result is (Резульат):");
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void calculateDeterminant() {
        int[][] matrix = getMatrix();
        int det = determinant(matrix);
        System.out.println("The result is (Результат):");
        System.out.println(det);
    }

    public static int determinant(int[][] matrix) {
        int n = matrix.length;
        if (n == 1) {
            return matrix[0][0];
        }
        if (n == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }

        int det = 0;
        for (int i = 0; i < n; i++) {
            int[][] submatrix = new int[n - 1][n - 1];
            for (int j = 1; j < n; j++) {
                for (int k = 0, l = 0; k < n; k++) {
                    if (k == i) {
                        continue;
                    }
                    submatrix[j - 1][l] = matrix[j][k];
                    l++;
                }
            }
            det += matrix[0][i] * (i % 2 == 0 ? 1 : -1) * determinant(submatrix);
        }
        return det;
    }

    public static void inverseMatrix() {
        int[][] matrix = getMatrix();
        int det = determinant(matrix);
        if (det == 0) {
            System.out.println("This matrix doesn't have an inverse (Ця матриця не має оберненої матриці)");
        } else {
            System.out.println("The result is (Результат):");
            int[][] inverse = inverse(matrix, det);
            for (int i = 0; i < inverse.length; i++) {
                for (int j = 0; j < inverse[0].length; j++) {
                    System.out.print(inverse[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

    public static int[][] getMatrix() {
        Scanner scanner = new Scanner(System.in);
        int num;
        while (true) {
            try {
                System.out.print("Enter size of matrix:\n> ");
                String[] size = scanner.nextLine().split(" ");
                if (size.length != 2) {
                    throw new Exception("Incorrect input! You should enter the size, like \"3 3\", \"4 4\", etc.");
                }
                num = Integer.parseInt(size[0]);
                int m = Integer.parseInt(size[1]);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Enter matrix:");
        int[][] matrix = new int[num][num];
        for (int i = 0; i < num; i++) {
            while (true) {
                try {
                    String[] row = scanner.nextLine().split(" ");
                    if (row.length != num) {
                        throw new Exception("You should enter only " + num + " numbers in a row!");
                    }
                    for (int j = 0; j < num; j++) {
                        matrix[i][j] = Integer.parseInt(row[j]);
                    }
                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return matrix;
    }

    public static int[][] inverse(int[][] matrix, int det) {
        int n = matrix.length;
        int[][] transposedMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                transposedMatrix[i][j] = matrix[j][i];
            }
        }
        int[][] inverseMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inverseMatrix[i][j] = Math.round(transposedMatrix[i][j] / (float) det);
            }
        }
        return inverseMatrix;
    }
}
