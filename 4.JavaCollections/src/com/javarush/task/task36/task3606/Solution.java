package com.javarush.task.task36.task3606;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/* 
Осваиваем ClassLoader и Reflection
*/
public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "com/javarush/task/task36/task3606/data/second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplse"));
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplf"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException {
        packageName = packageName.replaceAll("%20", " ");
        File file = new File(packageName);
        File[] files = file.listFiles();
        MyLoader loader = new MyLoader();
        for (File names : files) {
            hiddenClasses.add(loader.load(names.toPath()));
        }
    }

    public HiddenClass getHiddenClassObjectByKey(String key) {
        for (Class<?> cl : hiddenClasses) {
            try {
                if (cl.getSimpleName().toLowerCase().startsWith(key.toLowerCase())) {
                    Constructor[] constructors = cl.getDeclaredConstructors();
                    for (Constructor constructor : constructors) {
                        if (constructor.getParameterCount() == 0) {
                            constructor.setAccessible(true);
                            return (HiddenClass) constructor.newInstance();
                        }
                    }


                }
            } catch (Exception e) {

            }
        }
        return null;
    }

    class MyLoader extends ClassLoader {

        public Class<?> load(Path path) {
            try {
                if (path.getFileName().toString().lastIndexOf(".class") == -1)
                    return null;

                byte[] b = Files.readAllBytes(path);
                return defineClass(null, b, 0, b.length); //here main magic
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


    }
}

