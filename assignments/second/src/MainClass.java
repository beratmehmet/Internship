import classes.Food;
import classes.Pizza;
import classes.*;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MainClass {
    public static void main(String[] args) {
        Pizza pizza = new Pizza();
        List<Class> usages= new ArrayList<>();
        Annotation annos;

        String packageName = "classes";
        List<Class> commands = new ArrayList<Class>();
        URL root = Thread.currentThread().getContextClassLoader().getResource(packageName.replace(".", "/"));

        File[] files = new File(root.getFile()).listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".class");
            }
        });

        for (File file : files) {
            String className = file.getName().replaceAll(".class$", "");
            Class<?> cls = null;
            try {
                cls = Class.forName(packageName + "." + className);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            commands.add((Class) cls);

        }

        for (Class class1: commands
             ) {
           annos = class1.getAnnotation(Food.class);
           if (annos!=null){
               System.out.println(class1.getName());
           }


        }



    }

}
