package org.zxb.annotationaop;

import org.springframework.stereotype.Component;

@Component
public class CalculatorImpl implements Calculator{
    @Override
    public int add(int i, int j) {

        int result = i + j;
        System.out.println("函数内部调用加法运算 + result:" + result);
        return result;
    }

    @Override
    public int sub(int i, int j) {

        int result = i - j;
        System.out.println("函数内部调用减法运算 + result:" + result);
        return result;
    }

    @Override
    public int mul(int i, int j) {
        int result = i * j;
        System.out.println("函数内部调用乘法运算 + result:" + result);
        return result;
    }
}
