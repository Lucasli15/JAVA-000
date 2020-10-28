package com.example.demo;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class DemoApplicationTests extends ClassLoader {

    public static void main(String[] args) {
        try {
            Class<?> hello = new DemoApplicationTests().findClass("Hello");
            Method sayHello = hello.getDeclaredMethod("hello");
            sayHello.setAccessible(true);
            sayHello.invoke(hello.newInstance());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] file = this.getFile(name);
            return defineClass(name, file, 0, file.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 最终目的是调用defineClass(name, array1, 0, array1.length);
        return super.findClass(name);
    }

    public byte[] getFile(String name) throws IOException {
        // 利用IO读取文件
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(name + ".xlass");
        byte[] resultArray, outArray;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outArray = new byte[1];
        while (resourceAsStream.read(outArray) != -1) {
            outArray[0] = (byte) (255 - outArray[0]);
            outputStream.write(outArray);
        }
        resultArray = outputStream.toByteArray();
        return resultArray;
    }
}
