package java.lang.reflect;

public class Array {
    public static int getLength(Object array){
        return ((Object[])array).length;
    }

    public static Object get(Object array, int i){
        return ((Object[])array)[i];
    }
}