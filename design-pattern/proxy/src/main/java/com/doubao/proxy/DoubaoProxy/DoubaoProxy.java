package com.doubao.proxy.DoubaoProxy;



import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;

import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class DoubaoProxy{


    static  String ln = "\t\n";
    public static Object newProxyInstance(DoubaoClassLoader classLoader,Class<?>[] interfaces,DoubaoInvocationHandler h) throws Exception{

        //1、生成class源代码文件
        String src = generateSrc(interfaces);

//        System.out.println(src);
        //2、将java文件输出到指定的路径
        String filePath = DoubaoProxy.class.getResource("").getPath();

        File file = new File(filePath+"/$Proxy0.java");
//        System.out.println(file.getAbsoluteFile());
        FileWriter fw = new FileWriter(file);
        fw.write(src);
        fw.close();

        //3、将java文件编译成class文件
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager standardFileManager = javaCompiler.getStandardFileManager(null, null, null);
        Iterable javaFileObjects = standardFileManager.getJavaFileObjects(file);
        JavaCompiler.CompilationTask task = javaCompiler.getTask(null, standardFileManager, null, null,null,javaFileObjects);
        task.call();
        standardFileManager.close();

        //4、
        Class<?> $Proxy0 = classLoader.findClass("$Proxy0");
        Constructor<?> c = $Proxy0.getConstructor(DoubaoInvocationHandler.class);

        return c.newInstance(h);

    }

//    private static String generateSrc(Class<?>[] interfaces) {
//        StringBuffer sb = new StringBuffer();
//        sb.append("package com.doubao.proxy.DoubaoProxy;"+ln);
//        sb.append("import com.doubao.proxy.DoubaoProxy.DoubaoInvocationHandler;"+ln);
//        sb.append("import java.lang.reflect.*;"+ln);
//        //类名
//        sb.append("public final class $Proxy0 extends DoubaoProxy implements Car {"+ln);
//        sb.append("private static Method m1;"+ln);
//        //静态代码块
////        sb.append("static{"+ln);
////        sb.append("try{"+ln);
////        sb.append("m1 = Class.forName(\"com.doubao.proxy.jdk.Car\").getMethod(\"getName\");"+ln);
////        sb.append("}catch(Exception e){"+ln);
////        sb.append("e.printStackTrace();"+ln);
////        sb.append("}"+ln);
////        sb.append("}"+ln);
//        //InvocationHandler
//        sb.append("private DoubaoInvocationHandler h;"+ln);
//        //构造器
//        sb.append("public $Proxy0 (DoubaoInvocationHandler h) {"+ln);
//        sb.append("this.h = h;"+ln);
//        sb.append("}"+ln);
//
//        for (Method m : interfaces[0].getMethods()){
//            Class<?>[] params = m.getParameterTypes();
//
//            StringBuffer paramNames = new StringBuffer();
//            StringBuffer paramValues = new StringBuffer();
//            StringBuffer paramClasses = new StringBuffer();
//
//            for (int i = 0; i < params.length; i++) {
//                Class clazz = params[i];
//                String type = clazz.getName();
//                String paramName = toLowerFirstCase(clazz.getSimpleName());
//                paramNames.append(type + " " +  paramName);
//                paramValues.append(paramName);
//                paramClasses.append(clazz.getName() + ".class");
//                if(i > 0 && i < params.length-1){
//                    paramNames.append(",");
//                    paramClasses.append(",");
//                    paramValues.append(",");
//                }
//            }
//
//            sb.append("public " + m.getReturnType().getName() + " " + m.getName() + "(" + paramNames.toString() + ") {" + ln);
//            sb.append("try{" + ln);
//            sb.append("Method m = " + interfaces[0].getName() + ".class.getMethod(\"" + m.getName() + "\",new Class[]{" + paramClasses.toString() + "});" + ln);
//            sb.append((hasReturnValue(m.getReturnType()) ? "return " : "") + getCaseCode("this.h.invoke(this,m,new Object[]{" + paramValues + "})",m.getReturnType()) + ";" + ln);
//            sb.append("}catch(Error _ex) { }");
//            sb.append("catch(Throwable e){" + ln);
//            sb.append("throw new UndeclaredThrowableException(e);" + ln);
//            sb.append("}");
//            sb.append(getReturnEmptyCode(m.getReturnType()));
//            sb.append("}");
//        }
////        //方法调用
////        sb.append("public String getName (){"+ln);
////        sb.append("try{"+ln);
////        //方法操作
////        sb.append("System.out.println(\"开始调用getName方法\");"+ln);
////        sb.append("return (String)h.invoke(this,m1,null);"+ln);
////        sb.append("}catch (Throwable t) {"+ln);
////        sb.append("throw new UndeclaredThrowableException(t);"+ln);
////        sb.append("}"+ln);
////        sb.append("}"+ln);
//        sb.append("}"+ln);
//        return sb.toString();
//    }
private static String generateSrc(Class<?>[] interfaces){
    StringBuffer sb = new StringBuffer();
    sb.append("package com.doubao.proxy.DoubaoProxy;"+ln);
    sb.append("import com.doubao.proxy.DoubaoProxy.DoubaoInvocationHandler;"+ln);
    sb.append("import java.lang.reflect.*;"+ln);
    sb.append("public class $Proxy0 implements " + interfaces[0].getName() + "{" + ln);
    sb.append("DoubaoInvocationHandler h;" + ln);
    sb.append("public $Proxy0(DoubaoInvocationHandler h) { " + ln);
    sb.append("this.h = h;");
    sb.append("}" + ln);
    for (Method m : interfaces[0].getMethods()){
        Class<?>[] params = m.getParameterTypes();

        StringBuffer paramNames = new StringBuffer();
        StringBuffer paramValues = new StringBuffer();
        StringBuffer paramClasses = new StringBuffer();

        for (int i = 0; i < params.length; i++) {
            Class clazz = params[i];
            String type = clazz.getName();
            String paramName = toLowerFirstCase(clazz.getSimpleName());
            paramNames.append(type + " " +  paramName);
            paramValues.append(paramName);
            paramClasses.append(clazz.getName() + ".class");
            if(i > 0 && i < params.length-1){
                paramNames.append(",");
                paramClasses.append(",");
                paramValues.append(",");
            }
        }

        sb.append("public " + m.getReturnType().getName() + " " + m.getName() + "(" + paramNames.toString() + ") {" + ln);
        sb.append("try{" + ln);
        sb.append("Method m = " + interfaces[0].getName() + ".class.getMethod(\"" + m.getName() + "\",new Class[]{" + paramClasses.toString() + "});" + ln);
        sb.append((hasReturnValue(m.getReturnType()) ? "return " : "") + getCaseCode("this.h.invoke(this,m,new Object[]{" + paramValues + "})",m.getReturnType()) + ";" + ln);
        sb.append("}catch(Error _ex) { }");
        sb.append("catch(Throwable e){" + ln);
        sb.append("throw new UndeclaredThrowableException(e);" + ln);
        sb.append("}");
        sb.append(getReturnEmptyCode(m.getReturnType()));
        sb.append("}");
    }
    sb.append("}" + ln);
    return sb.toString();
}

    private static Map<Class,Class> mappings = new HashMap<Class, Class>();
    static {
        mappings.put(int.class,Integer.class);
        mappings.put(String.class,String.class);
    }

    private static String getReturnEmptyCode(Class<?> returnClass){
        if(mappings.containsKey(returnClass) && returnClass !=String.class){
            return "return 0;";
        }else if(returnClass == void.class){
            return "";
        }else {
            return "return null;";
        }
    }

    private static String getCaseCode(String code,Class<?> returnClass){
        if(mappings.containsKey(returnClass)){
            return "(" + mappings.get(returnClass).getName() +  ")" + code;
        }
        return code;
    }

    private static boolean hasReturnValue(Class<?> clazz){
        return clazz != void.class;
    }

    private static String toLowerFirstCase(String src){
        char [] chars = src.toCharArray();

        if (chars[0]<97){
            chars[0] += 32;
        }

        return String.valueOf(chars);
    }

}
