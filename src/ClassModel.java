import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ClassModel {
    List<String> fieldName;
    List<String> fieldType;
    List<Object> fieldValue;
    Field[] fieldsArray;
    Object instanse;
    int size;

    ClassModel(String classname){
        fieldName = new ArrayList<>();
        fieldType = new ArrayList<>();
        fieldValue = new ArrayList<>();
        try{
            instanse = Class.forName(classname).newInstance();
        } catch (Exception e){
        }
    }

    void setFields(Field[] fields)
    {
        fieldsArray = fields.clone();
        for(Field field: fields)
        {
            size++;
            fieldName.add(field.getName());
            fieldType.add(field.getType().getName());
            try{
                fieldValue.add(field.get(instanse));
            }
            catch (Exception e){

            }

        }
    }
    void setValue(int row, Object value){
        try{
            fieldsArray[row].set(instanse, value);
            System.out.println(fieldsArray[row].get(instanse));
        } catch (Exception ex){
        }
    }
}
