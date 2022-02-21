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
            throw e;
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
            throw e;
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

            SpreadSheet excel = new SpreadSheet(5, 5);

            System.out.println(excel.isEmpty(0, "A"));
            excel.setCellValue(0, "A", "123", var_type.INT);
            System.out.println(excel.getFloatValue(0, "A"));
            System.out.println(excel.isEmpty(0, "A"));
            excel.setCellValue(2, "C", "333", var_type.INT);
            System.out.println(excel.getRange(0, "A", 4, "C"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private ArrayList<Float> getRange(int topLeftRow, String topLeftColumn, int botRightRow, String botRightColumn) {
        int topLeftCol = convertColNameToNumber(topLeftColumn);
        int botRightCol = convertColNameToNumber(botRightColumn);

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

        try {
            arr.get(row).get(col);
        } catch (Exception e) {
            throw e;
        }

        if (valueType == var_type.INT) {
            Cell cell = new IntCell();
            cell.setValue(value);
            arr.get(row).add(col, cell);
        } else if (valueType == var_type.FLOAT) {
            Cell cell = new FloatCell();
            cell.setValue(value);
            arr.get(row).add(col, cell);
        } else if (valueType == var_type.STR) {
            Cell cell = new StrCell();
            cell.setValue(value);
            arr.get(row).add(col, cell);
        }

        return true;
    }

    private float getFloatValue(int row, String column) {
        int col = convertColNameToNumber(column);
        return arr.get(row).get(col).getFloatValue();
    }

    private boolean isEmpty(int row, String column) {
        int col = convertColNameToNumber(column);

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
