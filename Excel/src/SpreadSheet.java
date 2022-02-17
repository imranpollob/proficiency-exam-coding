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
            System.out.println(e.toString());
            return Float.MIN_VALUE;
        }
    }

}

class IntCell implements Cell {
    int value;

    @Override
    public void setValue(String val) {
        try {
            value = Integer.valueOf(value);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public float getFloatValue() {
        try {
            return Float.valueOf(value);
        } catch (Exception e) {
            System.out.println(e.toString());
            return Float.MIN_VALUE;
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
            System.out.println(e.toString());
        }
    }

    @Override
    public float getFloatValue() {
        try {
            return Float.valueOf(value);
        } catch (Exception e) {
            System.out.println(e.toString());
            return Float.MIN_VALUE;
        }
    }

}

public class SpreadSheet {
    static int colsCount;
    static int rowsCount;
    ArrayList<LinkedList<Cell>> arr;

    public SpreadSheet(int col, int row) {
        colsCount = col;
        rowsCount = row;
    }

    public static void main(String[] args) {
        SpreadSheet excel = new SpreadSheet(5, 5);
        
//        for (int i = 0; i <= colsCount; i++) {
//            arr.add();
//        }
        
    }
    
    private boolean isEmpty(int row, String column) {
        
        
    }

}
