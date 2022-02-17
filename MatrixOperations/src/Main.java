
public class Main {

	public static void main(String[] args) {
		// nRows, nCols

		try {
			Matrix X = new Matrix(3, 3);
			Matrix Y = new Matrix(3, 3);
			Matrix Z = new Matrix(3, 2);

			int[] x = { 5, 0, -5, 2, 4, 2, -1, 3, 9 };
			int[] y = { 1, 6, -2, -1, 8, -5, 3, 4, 10 };
			int[] z = { 2, 0, 1, 5, -9, -3 };

			X.loadFromArray(x);
			Y.loadFromArray(y);
			Z.loadFromArray(z);

			System.out.println("2X + Y: ");
			X.multiplyBy(2).addition(Y).printMatrix();

			System.out.println("2Y - X: ");
			Y.multiplyBy(2).subtraction(X).printMatrix();

			System.out.println("Transpose of Z: ");
			Z.getTranspose().printMatrix();

			System.out.println("XYZ: ");
			X.multiplyBy(Y).multiplyBy(Z).printMatrix();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}