/**
 * Noah Sibley - N01512207
 * Speedups:
 * n : 1000, Avg. speedup: 13X
 * n : 2000, Avg. speedup: 8X
 * n : 3000, Avg. speedup: 17X
 * n : 4000, Avg. speedup: 48X
 * n : 5000, Avg. speedup: 68X
 * n : 6000, Avg. speedup: 115X
 * n : 7000, Avg. speedup: 114X
 * n : 8000, Avg. speedup: 148X
 * n : 9000, Avg. speedup: 199X
 * n : 10000, Avg. speedup: 252X
 */

package p2;

import java.io.*;
import java.util.Scanner;
public class SparseMatrix2D extends AbstractSparseMatrix2D {

    // The constructor; we set up the pink and blue nodes first in this constructor
    // Then we read the file and create the gray nodes one by one.
    protected SparseMatrix2D(String inputFileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(inputFileName));

        if (scanner.hasNextInt()) {
            sizeOfMatrix = scanner.nextInt();
            rows = new MatrixNode[sizeOfMatrix];
            cols = new MatrixNode[sizeOfMatrix];

            for (int i = 0; i <sizeOfMatrix; i++) {
                rows[i] = new MatrixNode(i, -1);
                cols[i] = new MatrixNode(-1, i);
            }
        }

        while (scanner.hasNextInt()) {
            int i = scanner.nextInt();
            int j = scanner.nextInt();
            int entry = scanner.nextInt();
            setEntry(i, j, entry);
        }
        scanner.close();
    }


    // Please do not touch this method
    public void writeTheFourTraversalsToFile(String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);

        fileWriter.write(printRowMajorLtoR());
        fileWriter.write(printRowMajorRtoL());
        fileWriter.write(printColMajorTtoB());
        fileWriter.write(printColMajorBtoT());

        fileWriter.close();
    }

    // Please do not touch this method; This method adds to matrices and sends the result to a file
    // Runs in O(n^3) time, where n is the size of the matrix
    public static void addMatricesCubic(SparseMatrix2D A, SparseMatrix2D B, String outputFile) throws IOException {
        if (A.sizeOfMatrix != B.sizeOfMatrix)
            throw new IllegalArgumentException("The input matrices must have a same size!");

        FileWriter output = new FileWriter(outputFile);
        output.write(A.sizeOfMatrix + "\n");

        // Runs in O(n^3) time
        for (int i = 0; i < A.sizeOfMatrix; i++)
            for (int j = 0; j < A.sizeOfMatrix; j++) {
                Integer Aij = A.getEntry(i, j); // takes O(n) time
                Integer Bij = B.getEntry(i, j); // takes O(n) time
                if (Aij == null) Aij = 0;
                if (Bij == null) Bij = 0;
                if (Aij + Bij != 0) output.write(i + " " + j + " " + (Aij + Bij) + "\n");
            }
        output.close();
    }

    // Complete this method; This method should run in O(n^2) time
    // Do not declare new arrays/ArrayLists or any other data structure inside this method!
    public static void addMatricesQuadratic(SparseMatrix2D A, SparseMatrix2D B, String outputFile) throws IOException {
        // Do not declare any kind of array or any other data structures inside this method.
        if (A.sizeOfMatrix != B.sizeOfMatrix)
            throw new IllegalArgumentException("The input matrices must have a same size!");

        FileWriter output = new FileWriter(outputFile);

        output.write(A.sizeOfMatrix + "\n");

        for (int i = 0; i < A.sizeOfMatrix; i++) {
            MatrixNode nodeA = A.rows[i].right;
            MatrixNode nodeB = B.rows[i].right;

            while (nodeA != null || nodeB != null) {
                int colA;
                int colB;

                if (nodeA != null) {
                    colA = nodeA.col;
                } else {
                    colA = Integer.MAX_VALUE;
                }

                if (nodeB != null) {
                    colB = nodeB.col;
                } else {
                    colB = Integer.MAX_VALUE;
                }

                if (colA == colB) {
                    int sum = nodeA.entry + nodeB.entry;
                    if (sum != 0) {
                        output.write(i + " " + colA + " " + sum + "\n");
                    }

                    nodeA = nodeA.right;
                    nodeB = nodeB.right;

                } else if (colA < colB) {
                    output.write(i + " " + colA + " " + nodeA.entry + "\n");
                    nodeA = nodeA.right;
                } else {
                    output.write(i + " " + colB + " " + nodeB.entry + "\n");
                    nodeB = nodeB.right;
                }
            }


        }
        output.close();

    }


    // Complete this method; do not declare new arrays/ArrayLists or any other
    // data structure inside this method!
    private void setEntry(Integer i, Integer j, Integer entry) {
        if (i >= sizeOfMatrix || j >= sizeOfMatrix)
            throw new IllegalArgumentException("At least one index is out of bound!");

        MatrixNode newNode = new MatrixNode(i, j, entry);

        MatrixNode currRow = rows[i];
        while (currRow.right != null && currRow.right.col < j) {
            currRow = currRow.right;
        }

        newNode.right = currRow.right;

        if (currRow.right != null) {
            currRow.right.left = newNode;
        }

        currRow.right = newNode;
        newNode.left = currRow;

        MatrixNode currCol = cols[j];
        while (currCol.down != null && currCol.down.row < i) {
            currCol = currCol.down;
        }

        newNode.down = currCol.down;

        if (currCol.down != null) {
            currCol.down.up = newNode;
        }

        currCol.down = newNode;
        newNode.up = currCol;

    }
}
