package application;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Matriks {
	int[][] x;
	int m;
	int n;

	public Matriks(int[][] data) {
		super();
		n = data[0].length;
		m = data.length;

		x = new int[m][n];

		System.out.println("m x n = " + m + " " + n);
		for (int i = 0; i < m; i++) {
			x[i] = data[i];
			// System.out.println("i = " + i);
			// for (int j = 0; j < n; j++) {
			// x[i][j] = data[i][j];
			// }
		}
	}

	public void printMatriks() {
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(x[i][j] + " ");
			}
			System.out.println();
		}
	}

	public void writeToFile() throws FileNotFoundException {
		PrintWriter writer = new PrintWriter("debugfile.txt");
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				writer.print(x[i][j] + " ");
			}
			writer.println();
		}
		writer.close();
	}
}
