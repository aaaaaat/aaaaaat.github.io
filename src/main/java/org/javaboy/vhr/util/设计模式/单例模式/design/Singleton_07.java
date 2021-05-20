package org.javaboy.vhr.util.设计模式.单例模式.design;
/**
 * 枚举单例（线程安全）
 * 解决了最主要的线程安全、自由串行化和单一实例问题
 * 即使在面对复杂的串行化或反射攻击时，也无偿地提供了串行化机制，绝对防止对此实例化。
 * 存在继承场景下，这种方式是不可用的
 */
public enum Singleton_07 {

    INSTANCE;

    public void test(){
        System.out.println("hi~");
    }

}
