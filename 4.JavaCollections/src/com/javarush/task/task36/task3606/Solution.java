package com.javarush.task.task36.task3606;

import java.io.File;
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
        for(File names: files){
            if (names.getName().endsWith(".class")){
                String name = names.getAbsolutePath().split(".class")[0];
                hiddenClasses.add(Class.forName(name));
            }
        }
    }

    public HiddenClass getHiddenClassObjectByKey(String key) {
        for (Class cl : hiddenClasses){
            try {
                if (cl.getSimpleName().contains(key)) return (HiddenClass) cl.getConstructor(null).newInstance();
            }
            catch (Exception e){}
        }
        return null;
    }

    class myLoader extends ClassLoader{
        @Override
        protected Class<?> findClass(String s) throws ClassNotFoundException {
            return super.findClass(s);
        }

        @Override
        public Class<?> loadClass(String s) throws ClassNotFoundException {
            return super.loadClass(s);
        }


    }
}

