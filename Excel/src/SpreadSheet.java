import java.util.ArrayList;
import java.util.LinkedList;

interface Cell {
    /**
     * sets the contents of a cell to the value provided as a string argument,
     * performing conversion (e.g. string to float), if necessary, and throwing an
     * exception, if such conversion is not possible
     */
    abstract void setValue(String value);

    /**
     * returns the contents of a cell as a floating point number. The function
     * should throw an exception, if type conversion is impossible (e.g. string of
     * characters to float is impossible, but string of digits to float is possible)
     */
    abstract float getFloatValue();
}

class StrCell implements Cell {
    String value;

    @Override
    public void setValue(String val) {
        value = val;
    }

    @Override
    public float getFloatValue() {
        try {
            return Float.valueOf(value);
        } catch (Exception e) {
            throw e;
        }
    }

}

class IntCell implements Cell {
    int value;

    @Override
    public void setValue(String val) {
        try {
            value = Integer.valueOf(val);
        } catch (Exception e) {
            System.out.println("Can’t convert '" + val + "' to integer");
        }
    }

    @Override
    public float getFloatValue() {
        try {
            return Float.valueOf(value);
        } catch (Exception e) {
            throw e;
        }
    }

}

class FloatCell implements Cell {
    float value;

    @Override
    public void setValue(String val) {
        try {
            value = Float.valueOf(val);
        } catch (Exception e) {
            System.out.println("Can’t convert '" + val + "' to float");
        }
    }

    @Override
    public float getFloatValue() {
        try {
            return Float.valueOf(value);
        } catch (Exception e) {
            throw e;
        }
    }

}

public class SpreadSheet {
    int cols;
    int rows;
    ArrayList<LinkedList<Cell>> arr;

    enum var_type {
        INT, FLOAT, STR
    }

    public SpreadSheet(int col, int row) {
        cols = col;
        rows = row;
        arr = new ArrayList<LinkedList<Cell>>();

        for (int i = 0; i < rows; i++) {
            arr.add(new LinkedList<Cell>());

            for (int j = 0; j < cols; j++) {
                Cell cell = new StrCell();
                cell.setValue("7");

                arr.get(i).add(null);
            }
        }
    }

    public static void main(String[] args) {
        try {

//            SpreadSheet excel = new SpreadSheet(5, 5);
//
//            System.out.println(excel.isEmpty(0, "A"));
//
//            excel.setCellValue(0, "A", "123", var_type.INT);
//
//            System.out.println(excel.getFloatValue(0, "A"));
//
//            System.out.println(excel.isEmpty(0, "A"));
//
//            excel.setCellValue(2, "C", "333", var_type.INT);
//
//            System.out.println(excel.getRange(0, "A", 4, "C"));
//
////            System.out.println(excel.arr);
//            System.out.println(excel.getFloatValue(2, "C"));
//            excel.insertColumn("B");
////            System.out.println(excel.arr);
//            System.out.println(excel.getFloatValue(2, "D"));

            // Creates a 10 by 10 cell spreadsheet
            SpreadSheet excel = new SpreadSheet(10, 10);

            // Sets cell (11, A) to “5” and type INT (this should result in an error message
            // “(11, A) is out of bounds
            excel.setCellValue(11, "A", "5", var_type.INT);
            // cell (1,A) to “1” and type INT,
            excel.setCellValue(1, "A", "1", var_type.INT);
            // cell (2,A) to “3” and type INT,
            excel.setCellValue(2, "A", "3", var_type.INT);
            // cell (1,B) to “4.0” and type FLOAT,
            excel.setCellValue(1, "B", "4.0", var_type.FLOAT);
            // cell (2,B) to “a” and type INT (this should result in
            // an error message “can’t covert ‘a’ to integer”),
            excel.setCellValue(2, "B", "a", var_type.INT);
            // cell (2,B) to “6” and type INT
            excel.setCellValue(2, "B", "6", var_type.INT);

            // Inserts a new column with index “B” into the spreadsheet
            excel.insertColumn("B");
            System.out.println(excel.getFloatValue(2, "C"));
            // Obtains an array of values for the cells in the range from (1,A) to (2,C) and
            // prints the contents of this array (this should print “1.0 3.0 4.0 6.0”)
            System.out.println(excel.getRange(1, "A", 2, "C"));

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void insertColumn(String column) {
        int col = convertColNameToNumber(column);
        for (int i = 0; i < arr.size(); i++) {
            arr.get(i).add(col, null);
        }
    }

    private ArrayList<Float> getRange(int topLeftRow, String topLeftColumn, int botRightRow, String botRightColumn) {
        int topLeftCol = convertColNameToNumber(topLeftColumn);
        int botRightCol = convertColNameToNumber(botRightColumn);
        topLeftRow--;
        botRightRow--;
        try {
            arr.get(topLeftRow).get(topLeftCol);
            arr.get(botRightRow).get(botRightCol);
        } catch (Exception e) {
            System.out.println("Index not satisfied:");
            throw e;
        }

        ArrayList<Float> values = new ArrayList<Float>();

        for (int i = topLeftRow; i <= botRightRow; i++) {
            for (int j = topLeftCol; j <= botRightCol; j++) {
                if (arr.get(i).get(j) != null) {
                    values.add(arr.get(i).get(j).getFloatValue());
                }
            }
        }
        
        return values;
    }

    private boolean setCellValue(int row, String column, String value, var_type valueType) {
        int col = convertColNameToNumber(column);
        row--;
        try {
            arr.get(row).get(col);
        } catch (Exception e) {
            System.out.println("(" + row + ", " + column + ") is out of bounds");
            return false;
        }

        if (valueType == var_type.INT) {
            Cell cell = new IntCell();
            cell.setValue(value);
            arr.get(row).set(col, cell);
        } else if (valueType == var_type.FLOAT) {
            Cell cell = new FloatCell();
            cell.setValue(value);
            arr.get(row).set(col, cell);
        } else if (valueType == var_type.STR) {
            Cell cell = new StrCell();
            cell.setValue(value);
            arr.get(row).set(col, cell);
        }

        return true;
    }

    private float getFloatValue(int row, String column) {
        int col = convertColNameToNumber(column);
        row--;
        return arr.get(row).get(col).getFloatValue();
    }

    private boolean isEmpty(int row, String column) {
        int col = convertColNameToNumber(column);
        row--;
        if (arr.get(row).get(col) == null) {
            return true;
        }

        return false;
    }

    private static int convertColNameToNumber(String str) {
        int col = 0;

        for (int i = 0; i < str.length(); i++) {
            col *= 26;
            col += str.charAt(i) - 'A' + 1;
        }

        return col - 1;
    }

}
