package org.idey.algo.oops;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Util {
    public static <T> boolean instanceOf(T object, Class<?> clazz){
        if(object==null){
            return false;
        }else{
            Class<?> baseClass = object.getClass();
            if (baseClass.isArray() && clazz.isArray()) {
                return instanceOf(baseClass.getComponentType(), clazz.getComponentType());
            } else
                return !(!baseClass.isArray() && clazz.isArray())
                        && !(baseClass.isArray() && !clazz.isArray())
                        && instanceOf(baseClass, clazz);
        }
    }

    private static boolean instanceOf(Class<?> baseClass, Class<?> clazz){
        if(baseClass!=null && baseClass.equals(clazz)){
            return true;
        }else{
            if(baseClass!=null) {
                Set<Class<?>> classes = new HashSet<>(Arrays.asList(baseClass.getInterfaces()));
                Class<?> superClass = baseClass.getSuperclass();
                if(superClass!=null){
                    classes.add(superClass);
                }
                for (Class<?> vaClass : classes) {
                    if (instanceOf(vaClass, clazz)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(instanceOf(new Integer[]{1,2,3}, Number[].class));
        System.out.println((new Integer[]{1,2,3} instanceof Object[]));
    }

}
