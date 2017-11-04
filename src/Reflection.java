import java.lang.reflect.Field;

public class Reflection {
    static Field[] getFields(String className)
    {
        try{
            Class cl = Class.forName(className);
            return cl.getFields();
        } catch (ClassNotFoundException ex){

        }
        return null;
    }
    static Object parse(Class cl, String string){
        if(cl.equals(Integer.class)) {
            return Integer.parseInt(string);
        } else if(cl.equals(Double.class)){
            return Double.parseDouble(string);
        } else if(cl.equals(String.class)) {
            return string;
        } else if(cl.equals(Boolean.class)){
            return Boolean.parseBoolean(string);
        }
        return string;
    }

}
