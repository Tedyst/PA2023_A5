package ro.tedyst;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Objects;

public class Main {
    public static int testCount = 0;
    public static int testPassed = 0;

    public static void inspectClass(Class<?> cls) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        System.out.println();
        System.out.println(cls.getName());
        for (var method : cls.getMethods()) {
            System.out.println("Method " + method.getName());
        }
        for (var field : cls.getFields()) {
            System.out.println("Field " + field.getName());
        }
        for (var constructor : cls.getConstructors()) {
            System.out.println("Constructor " + constructor.getName());
        }
        for(var method : cls.getMethods()){
            if (method.isAnnotationPresent(Test.class)) {
                Object obj = cls.getConstructor().newInstance();
                System.out.println("Annotation " + method.getAnnotation(Test.class));
                testCount++;
                try {
                    method.invoke(obj);
                    testPassed++;
                } catch (Exception e){
                    System.out.println("Test failed: " + e.getMessage());
                }
            }
        }
    }

    public static void searchClasses(ClassLoader cl, File dir, String packagePrefix) {
        if(dir.isDirectory()) {
            String prefix = packagePrefix;
            if (!dir.getName().equals("classes"))
                prefix = packagePrefix + dir.getName() + ".";
            for(File f : Objects.requireNonNull(dir.listFiles())) {
                searchClasses(cl, f, prefix);
            }
        }
        else {
            String fileName = dir.getName();
            if(! fileName.endsWith(".class"))
                return;
            String className = packagePrefix + fileName.substring(0, fileName.length()-".class".length());

            try {
                Class<?> cls = cl.loadClass(className);
                inspectClass(cls);
            } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | NoSuchMethodException | InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    public static String FILE_PATH = "/home/tedy/Git/PA2023_A5/Lab12/target/classes/";

    public static void main(String[] args) throws MalformedURLException {
        File file = new File(FILE_PATH);
        URL url = file.toURI().toURL();
        URL[] urls = new URL[]{url};
        ClassLoader cl = new URLClassLoader(urls);

        searchClasses(cl, new File(file.toURI()), "");

        System.out.println("Tests passed: " + testPassed + "/" + testCount);

    }
}