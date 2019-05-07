package com.doubao.proxy.DoubaoProxy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;


public class DoubaoClassLoader extends  ClassLoader{

    private File classPathFile;

    public DoubaoClassLoader(){
        String classPath = DoubaoClassLoader.class.getResource("").getPath();
        classPathFile = new File(classPath);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String className=DoubaoClassLoader.class.getPackage().getName()+"."+name;
        try {
            if (classPathFile != null) {
                File classFile = new File(classPathFile,name.replaceAll("\\.","/")+".class");
                if (classFile.exists()){
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    FileInputStream fis = new FileInputStream(classFile);
                    byte[] buff = new byte[1024];
                    int len;
                    while ((len = fis.read(buff) ) != -1){
                        out.write(buff,0,len);
                    }
                    return defineClass(className,out.toByteArray(),0,out.size());
                }
            }
        }catch (Exception e ){

        }


        return null;
    }
}
