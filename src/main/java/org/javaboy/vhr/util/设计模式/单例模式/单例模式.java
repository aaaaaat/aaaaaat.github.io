package org.javaboy.vhr.util.设计模式.单例模式;

/**
 * TODO 需要保证一个类只有一个实例，哪怕多线程同时访问，而且需要提供一个全局访问此实例的点
 * 主要解决的是一个全局使用的类，被频繁的创建销毁，从而提升代码的整体性能
 *
 * @author xyf
 * @version 1.0
 * @date 2021/5/19 11:22 上午
 */
public class 单例模式 {
    //例如，1、Spring中一个单例模式Bean的生成和使用
    //2、数据库的连接池不会被反复地创建和使用
    //3、代码中需要设置全局的一些属性并保存

    //实现方式比较多，主要分为在实现上是否支持懒汉模式，是否支持在线程安全中运用各项技巧
    //当然也有在一些场景下不需要考虑懒汉模式的情况，会直接使用static静态类或属性和方法的方式，供外部调用

    //单例模式的一个特点：不允许外部直接创建
    //实现1：懒汉模式（线程不安全）
//    public class Singleton_01{
//        private static Singleton_01 instance;
//        private Singleton_01(){}
//        public static Singleton_01 getInstance(){
//            //instance可能为空
//            if (null!=instance) return instance;
//            instance=new Singleton_01();
//            return instance;
//        }

//           public static synchronized Singleton_01 getInstance(){ //线程安全的实现方式
//            //instance可能为空
//            if (null!=instance) return instance;
//            instance=new Singleton_01();
//            return instance;
//        }



//    }

      //总结：在日常开发中，如果确保此类是全局可用的，则不需要懒汉模式，那么直接创建并给外部调用即可。
     //但如果有很多的类，有些需要在用户出发一定的条件后才显示，那么一定要用懒汉模式。
    //对于线程的安全，可以按需选择

}
