/**
 * Project Name:java-basic
 * File Name:PrimitiveTypeATest.java
 * Package Name:com.easyway.java.basic.disabuse.primitive
 * Date:2015-1-21下午1:45:53
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.disabuse.primitive;

/**
 * 
 * <pre>
 * 从上面的例子中我们可以得出如下几条结论： 
 * 未带有字符后缀标识的整数默认为int类型；未带有字符后缀标识的浮点数默认为double类型。
 * 如果一个整数的值超出了int类型能够表示的范围，则必须增加后缀“L”（不区分大小写，建议用大写，因为小写的L与阿拉伯数字1很容易混淆），表示为long型。
 * 带有“F”（不区分大小写）后缀的整数和浮点数都是float类型的；带有“D”（不区分大小写）后缀的整数和浮点数都是double类型的。
 * 编译器会在编译期对byte、short、int、long、float、double、char型变量的值进行检查，如果超出了它们的取值范围就会报错。
 * int型值可以赋给所有数值类型的变量；long型值可以赋给long、float、double类型的变量；float型值可以赋给float、double类型的变量；
 * double型值只能赋给double类型变量。
 * 
 * 下图显示了几种基本类型之间的默认逻辑转换关系： 
 *  
 * 图中的实线表示无精度损失的转换，而虚线则表示这样的转换可能会损失一定的精度。如果我们想把一个能表示更大范围或者更高精度的类型，转换为一个
 * 范围更小或者精度更低的类型时，就需要使用强制类型转换（Cast）了。不过我们要尽量避免这种用法，因为它常常引发错误。
 * 请看下面的例子，如果不运行代码，你能预测它的结果吗？
 * </pre>
 * 
 * ClassName:PrimitiveTypeATest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午1:45:53 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class PrimitiveTypeATest {
    public static void main(String[] args) {
        // 给byte类型变量赋值时，数字后无需后缀标识
        byte byte_a = 1;
        // 编译器会做范围检查，如果赋予的值超出了范围就会报错
        // byte byte_b = 1000;
        // 把一个long型值赋值给byte型变量，编译时会报错，即使这个值没有超出byte类型的取值范围
        // byte byte_c = 1L;

        // 给short类型变量赋值时，数字后无需后缀标识
        short short_a = 1;
        // 编译器会做范围检查，如果赋予的值超出了范围就会报错
        // short short_b = 70000;
        // 把一个long型值赋值给short型变量，编译时会报错，即使这个值没有超出short类型的取值范围
        // byte short_c = 1L;

        // 给short类型变量赋值时，数字后无需后缀标识
        int int_a = 1;
        // 编译器会做范围检查，如果赋予的值超出了范围就会报错
        // int int_b = 2200000000;
        // 把一个long型值赋值给int型变量，编译时会报错，即使这个值没有超出int类型的取值范围
        // int int_c = 1L;

        // 可以把一个int型值直接赋值给long型变量，数字后无需后缀标识
        long long_a = 1;
        // 如果给long型变量赋予的值超出了int型值的范围，数字后必须加L（不区分大小写）标识
        long long_b = 2200000000L;
        // 编译器会做范围检查，如果赋予的值超出了范围就会报错
        // long long_c = 9300000000000000000L;

        // 可以把一个int型值直接赋值给float型变量
        float float_a = 1;
        // 可以把一个long型值直接赋值给float型变量
        float float_b = 1L;
        // 没有F（不区分大小写）后缀标识的浮点数默认为double型的，不能将它直接赋值给float型变量
        // float float_c = 1.0;
        // float型数值需要有一个F（不区分大小写）后缀标识
        float float_d = 1.0F;
        // 把一个double型值赋值给float型变量，编译时会报错，即使这个值没有超出float类型的取值范围
        // float float_e = 1.0D;
        // 编译器会做范围检查，如果赋予的值超出了范围就会报错
        // float float_f = 3.5000000E38F;

        // 可以把一个int型值直接赋值给double型变量
        double double_a = 1;
        // 可以把一个long型值直接赋值给double型变量
        double double_b = 1L;
        // 可以把一个float型值直接赋值给double型变量
        double double_c = 1F;
        // 不带后缀标识的浮点数默认为double类型的，可以直接赋值
        double double_d = 1.0;
        // 也可以给数字增加一个D（不区分大小写）后缀标识，明确标出它是double类型的
        double double_e = 1.0D;
        // 编译器会做范围检查，如果赋予的值超出了范围就会报错
        // double double_f = 1.8000000000000000E308D;

        // 把一个double型值赋值给一个byte类型变量，编译时会报错，即使这个值没有超出byte类型的取值范围
        // byte byte_d = 1.0D;
        // 把一个double型值赋值给一个short类型变量，编译时会报错，即使这个值没有超出short类型的取值范围
        // short short_d = 1.0D;
        // 把一个double型值赋值给一个int类型变量，编译时会报错，即使这个值没有超出int类型的取值范围
        // int int_d = 1.0D;
        // 把一个double型值赋值给一个long类型变量，编译时会报错，即使这个值没有超出long类型的取值范围
        // long long_d = 1.0D;

        // 可以用字符初始化一个char型变量
        char char_a = 'a';
        // 也可以用一个int型数值初始化char型变量
        char char_b = 1;
        // 把一个long型值赋值给一个char类型变量，编译时会报错，即使这个值没有超出char类型的取值范围
        // char char_c = 1L;
        // 把一个float型值赋值给一个char类型变量，编译时会报错，即使这个值没有超出char类型的取值范围
        // char char_d = 1.0F;
        // 把一个double型值赋值给一个char类型变量，编译时会报错，即使这个值没有超出char类型的取值范围
        // char char_e = 1.0D;
        // 编译器会做范围检查，如果赋予的值超出了范围就会报错
        // char char_f = 70000;
    }
}
