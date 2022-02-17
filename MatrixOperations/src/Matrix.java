
public class Matrix {
	private int nRows;
	private int nCols;
	private int[][] values;

	Matrix(int nRows, int nCols) throws Exception {
		if (nRows < 1 || nCols < 1) {
			throw new Exception("Matrix row or column cannot be less than 1");
		}
		this.nRows = nRows;
		this.nCols = nCols;
		this.values = new int[nRows][nCols];
	}

	public int getValueAt(int r, int c) {
		return values[r][c];
	}

	public void setValueAt(int r, int c, int val) {
		values[r][c] = val;
	}

	public void loadFromArray(int[] items) throws Exception {
		if (items.length != nRows * nCols) {
			throw new Exception("Given array size is invalid regarding to the matrix");
		}

		int i = 0;

		for (int r = 0; r < nRows; r++) {
			for (int c = 0; c < nCols; c++) {
				setValueAt(r, c, items[i]);
				i++;
			}
		}
	}

	public Matrix addition(Matrix B) throws Exception {
		if (nRows != B.nRows || nCols != B.nCols) {
			throw new Exception("Not compitable for addition");
		}
		
		Matrix C = new Matrix(nRows, nCols);

		for (int r = 0; r < nRows; r++) {
			for (int c = 0; c < nCols; c++) {
				C.setValueAt(r, c, getValueAt(r, c) + B.getValueAt(r, c));
			}
		}

		return C;
	}

	public Matrix subtraction(Matrix B) throws Exception {
		if (nRows != B.nRows || nCols != B.nCols) {
			throw new Exception("Not compitable for subtraction");
		}
		
		Matrix C = new Matrix(nRows, nCols);

		for (int r = 0; r < nRows; r++) {
			for (int c = 0; c < nCols; c++) {
				C.setValueAt(r, c, getValueAt(r, c) - B.getValueAt(r, c));
			}
		}

		return C;
	}

	public Matrix getTranspose() throws Exception {
		Matrix T = new Matrix(nCols, nRows);

		for (int i = 0; i < nRows; i++) {
			for (int j = 0; j < nCols; j++) {
				T.setValueAt(j, i, getValueAt(i, j));
			}
		}

		return T;

	}

	public Matrix multiplyBy(int scalar) throws Exception {
		Matrix C = new Matrix(nRows, nCols);

		for (int i = 0; i < nRows; i++) {
			for (int j = 0; j < nCols; j++) {
				C.setValueAt(i, j, getValueAt(i, j) * scalar);
			}
		}

		return C;
	}

	public Matrix multiplyBy(Matrix B) throws Exception {
		if (nCols != B.nRows) {
			throw new Exception("Not compitable for multiplication");
		}

		Matrix C = new Matrix(B.nRows, B.nCols);

		for (int i = 0; i < B.nRows; i++) {
			for (int j = 0; j < B.nCols; j++) {
				int sum = 0;
				for (int k = 0; k < B.nRows; k++) {
					sum += values[i][k] * B.values[k][j];
				}
				C.setValueAt(i, j, sum);
			}
		}

		return C;
	}

	public void printMatrix() {
		for (int i = 0; i < nRows; i++) {
			for (int j = 0; j < nCols; j++) {
				System.out.print(getValueAt(i, j) + " ");
			}
			System.out.println();
		}

	}
}
