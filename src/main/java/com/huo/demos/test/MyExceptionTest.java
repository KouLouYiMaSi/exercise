package com.huo.demos.test;

public class MyExceptionTest {

    public static void main(String[] args) {

         String[] sexs = {"男性","女性","中性"};
         for(int i = 0; i < sexs.length; i++){
             if("中性".equals(sexs[i])){
                 throw new MyException("100","你全家都是中性！");
             }else{
                 System.out.println(sexs[i]);
             }
         }
    }
}