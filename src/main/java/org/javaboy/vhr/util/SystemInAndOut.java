package org.javaboy.vhr.util;

import java.io.BufferedInputStream;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

/**
 * TODO ACM输入输出
 *
 * @author xyf
 * @version 1.0
 * @date 2021/4/11 1:37 下午
 */
public class SystemInAndOut {
    public static void main(String[] args){
        //多行数据输入情况下
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){

        }

        //有关System.nanoTime() 函数的使用
        // 该函数用来返回最准确的可用系统计时器的当前值
        // 以毫微秒为单位
        long startTime = System.nanoTime();
        long estimatedTime = System.nanoTime() - startTime;

        /**
         * Java之输入输出处理
         */

        //输入:在读入数据量大的情况下，格式1的速度会快一些
        Scanner sc1=new Scanner(new BufferedInputStream(System.in));
        Scanner sc2=new Scanner(System.in);

        //读一个整数
        int n=sc1.nextInt();
        //读一个字符串
        String s=sc1.next();
        //读一个浮点数
        double t=sc1.nextDouble();
        //读一整行
        //String s=sc1.nextLine();

        //判断是否有下一个输入
        //可以用sc.hasNext()或sc.hasNextInt()
        //或sc.hasNextDouble()或sc.hasNextLine()

    }
    /**
     * Input 输入数据有多组，每组占一行，由一个整数组成。
     * Sample Input
     * 56
     * 67
     * 100
     * 123
     */
     //import java.util.Scanner;
     public static class Main1{
         public static void main(String[] args) {
             Scanner sc = new Scanner(System.in);
             while (sc.hasNext()){ //判断是否结束
                 int score=sc.nextInt(); //读入整数

             }
         }
     }

     /**
      * 输入数据有多组，每组占2行
      * 第一行为一个整数N，指示第二行包含N个实数。
      * Sample Input
      * 4
      * 56.9 67.7 90.5 12.8
      * 5
      * 56.9 67.7 90.5 12.8
      */

     // import java.util.Scanner;
      public static class Main2{
          public static void main(String[] args) {
              Scanner sc = new Scanner(System.in);
              while (sc.hasNext()){
                  int n=sc.nextInt();
                  for (int i = 0; i < n; i++) {
                      double a=sc.nextDouble();

                  }
              }
          }
      }

      /**
       * 读入字符串
       * 输入数据有多行
       * 第一行是一个整数n，表示测试实例的个数
       * 后面跟着n行，每行包括一个由字母和数字组成的字符串
       *
       * Sample Input
       * 2
       * asdfasdf123123asdfasdf
       * asdf111111111asdfasdfasdf
       */
   // import java.util.Scanner;
    public static class Main3 {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            for(int i=0;i<n;i++){
                String str = sc.next();
                //......
            }
        }
    }
    //import java.util.Scanner;
    public static class Main4 {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int n = Integer.parseInt(sc.nextLine());
            for(int i=0;i<n;i++){
                String str = sc.nextLine();
                //......
            }
        }
    }

     /**
      * 读入字符串
      * 给定一个日期，输出这个日期是该年的第几天。
      * Input 输入数据有多组
      * 每组占一行，数据格式为YYYY/MM/DD组成
      * 1985/1/20
      * 2006/3/12
      */
    //import java.util.Scanner;
    public static class Main5 {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int[] dd = {0,31,28,31,30,31,30,31,31,30,31,30,31};
            while(sc.hasNext()){
                int days = 0;
                String str = sc.nextLine();
                String[] date = str.split("/");
                int y = Integer.parseInt(date[0]);
                int m = Integer.parseInt(date[1]);
                int d = Integer.parseInt(date[2]);
                if((y%400 == 0 || (y%4 == 0 && y%100 !=0)) && m>2) days ++;
                days += d;
                for(int i=0;i<m;i++){
                    days += dd[i];
                }
                System.out.println(days);
            }
        }
    }

    /**
     * 输出
     * 函数：
     *
     * System.out.print();
     *
     * System.out.println();
     *
     * System.out.format();
     *
     * System.out.printf();
     */

    /**
     *Sample Input
     *
     * 4
     *
     * + 1 2
     *
     * - 1 2
     *
     * * 1 2
     *
     * / 1 2
     *
     * Sample Output
     *
     * 3
     *
     * -1
     *
     * 2
     *
     * 0.50
     */
   // import java.util.Scanner;
    public static class Main6 {
        public static void main(String[] args) {
            Scanner sc =new Scanner(System.in);
            int n = sc.nextInt();
            for(int i=0;i<n;i++){
                String op = sc.next();
                int a = sc.nextInt();
                int b = sc.nextInt();
                if(op.charAt(0)=='+'){
                    System.out.println(a+b);
                }else if(op.charAt(0)=='-'){
                    System.out.println(a-b);
                }else if(op.charAt(0)=='*'){
                    System.out.println(a*b);
                }else if(op.charAt(0)=='/'){
                    if(a % b == 0) System.out.println(a / b);
                    else System.out.format("%.2f", (a / (1.0*b))). println();
                }
            }
        }
    }
    /**
     *规格化的输出：
     * 函数：
     *     // 这里0指一位数字，#指除0以外的数字(如果是0，则不显示),四舍五入.
     *     DecimalFormat fd = new DecimalFormat("#.00#");
     *     DecimalFormat gd = new DecimalFormat("0.000");
     *     System.out.println("x =" + fd.format(x));
     *     System.out.println("x =" + gd.format(x));
     */



    /**
     *字符串处理 String
     *
     * String 类用来存储字符串，可以用charAt方法来取出其中某一字节，计数从0开始： 
     *
     * String a = "Hello"; // a.charAt(1) = 'e' 
     *
     * 用substring方法可得到子串，如上例 
     *
     * System.out.println(a.substring(0, 4)) // output "Hell" 
     *
     * 注意第2个参数位置上的字符不包括进来。这样做使得 s.substring(a, b) 总是有 b-a个字符。 
     *
     * 字符串连接可以直接用 + 号，如 
     *
     * String a = "Hello"; 
     *
     * String b = "world"; 
     *
     * System.out.println(a + ", " + b + "!"); // output "Hello, world!" 
     *
     * 如想直接将字符串中的某字节改变，可以使用另外的StringBuffer类。
     */

     /**
      *高精度
      * BigInteger和BigDecimal可以说是acmer选择java的首要原因。
      * 函数：add, subtract, divide, mod, compareTo等
      * 其中加减乘除模都要求是BigInteger(BigDecimal)和BigInteger(BigDecimal)之间的运算
      * 所以需要把int(double)类型转换为BigInteger(BigDecimal)
      * 用函数BigInteger.valueOf().
      */
//     import java.io.BufferedInputStream;
//     import java.math.BigInteger;
//     import java.util.Scanner;
     public static class Main7 {
        public static void main(String[] args) {
            Scanner cin = new Scanner (new BufferedInputStream(System.in));
            int a = 123, b = 456, c = 7890;
            BigInteger x, y, z, ans;
            x = BigInteger.valueOf(a);
            y = BigInteger.valueOf(b);
            z = BigInteger.valueOf(c);
            ans = x.add(y); System.out.println(ans);
            ans = z.divide(y); System.out.println(ans);
            ans = x.mod(z); System.out.println(ans);
            if (ans.compareTo(x) == 0) System.out.println("1");
        }
    }

    /**
     *  进制转换
     * String st = Integer.toString(num, base); // 把num当做10进制的数转成base进制的st(base <= 35).
     * int num = Integer.parseInt(st, base); // 把st当做base进制，转成10进制的int(parseInt有两个参数,第一个为要转的字符串,第二个为说明是什么进制).  
     * BigInter m = new BigInteger(st, base); // st是字符串，base是st的进制.
     */

    /**
     * 数组排序
     * 函数：Arrays.sort();
     */


    public static class Main8 {
        public static void main(String[] args) {
            Scanner cin = new Scanner (new BufferedInputStream(System.in));
            int n = cin.nextInt();
            int a[] = new int [n];
            for (int i = 0; i < n; i++) a[i] = cin.nextInt();
            Arrays.sort(a);
            for (int i = 0; i < n; i++) System.out.print(a[i] + " ");
        }
    }

}

