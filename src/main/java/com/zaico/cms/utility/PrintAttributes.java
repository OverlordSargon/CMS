package com.zaico.cms.utility;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nzaitsev on 25.08.2016.
 */
public class PrintAttributes {
    public static String result = "";
    public static String header = "";
    private boolean HEAD_EXIST = false;

    public static String getAttributes(final Object valueObj) throws IllegalArgumentException,
            IllegalAccessException
    {
        result = "";
//        System.out.println("Begin - getFieldNamesAndValues");
        Class c1 = valueObj.getClass();
//        System.out.println("Class name got is:: " + c1.getName());
//        Map fieldMap = new HashMap();
        Field[] parentFields = c1.getSuperclass().getDeclaredFields();
        Field[] valueObjFields = c1.getDeclaredFields();
//        parentFields[1] = parentFields[valueObjFields.length+1];
//        parentFields[2] = parentFields[valueObjFields.length+1];
        List<Field[]> af = new ArrayList<Field[]>();
        af.add(parentFields);
        af.add(valueObjFields);
        // compare values now
        result +="<tr>";
        addFieldInfo(af,valueObj);
        result +="</tr>";
//        System.out.println("End - getFieldNamesAndValues");

        return result;
    }

    private static void addFieldInfo(List<Field[]> allFields,final Object obj) throws IllegalArgumentException,
            IllegalAccessException {
        for(Field[] fields: allFields) {
            for (int i = 0; i < fields.length; i++)
            {
                String fieldName = fields[i].getName();
//                System.out.println("Getting Field Values for Field:: " + fields[i].getName());
                fields[i].setAccessible(true);
                Object newObj = fields[i].get(obj);
//                System.out.println("Value of field" + fieldName + "newObj:: " + newObj);
                result+="<td>"+newObj+"<td>";
//                map.put(fieldName, newObj);
            }
        }
    }

    public static String getHeader(final Object valueObj) throws IllegalArgumentException,
            IllegalAccessException
    {
        header = "";
        Class c1 = valueObj.getClass();
        Field[] parentFields = c1.getSuperclass().getDeclaredFields();
        Field[] valueObjFields = c1.getDeclaredFields();
//        parentFields[1] = parentFields[valueObjFields.length+1];
//        parentFields[2] = parentFields[valueObjFields.length+1];
        List<Field[]> af = new ArrayList<Field[]>();
        af.add(parentFields);
        af.add(valueObjFields);
        addFieldHeader(af,valueObj);
        return header;
    }

    private static void addFieldHeader(List<Field[]> allFields,final Object obj) throws IllegalArgumentException,
            IllegalAccessException {
        for(Field[] fields: allFields) {
            for (int i = 0; i < fields.length; i++)
            {
                String fieldName = fields[i].getName();
                header+="<th>"+fieldName+"<th>";
            }
        }
    }

}
