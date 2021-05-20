package org.javaboy.vhr.util.设计模式.代理模式;

/**
 * TODO 代理模式就是为了方便访问某些资源，使对象类更加易用，从而在操作上使用的代理服务
 * 代理模式常出现在系统或组件中，他们提供一种非常简单易用的方式，控制原本需要编写很多代码才能实现的服务类
 *
 * @author xyf
 * @version 1.0
 * @date 2021/5/20 11:15 上午
 */
public class 代理模式 {

    //场景：经纪人负责演员的日常对接事务，就像代理一样
    //1、在数据库访问层面会提供一个比较基础的应用，避免在对应用服务扩容时造成数据库连接数暴增
    //2、使用过的一些中间件，例如rpc框架，在那拿到jar包对接口的描述后，中间件会在服务启动时生成对应的代理类。
    //   当调用接口时，实际是通过代理类发出的socket信息
    //3、常用的mybatis基本功能是定义接口，不需要写实现类接可以对xml或自定义注解里的sql语句增删改查

    //模拟mybatis-spring中代理类生成部分
    //介绍如何用代理类模式实现mybatis中对类的处理
    //也就是只需定义接口，就可以关联到方法注解中的sql语句，完成对数据库的操作

    //知识点：1、BeanDefinitionRegistryPostProcessor:spring的接口类用于处理对bean的定义操作
    //       2、GenericBeanDefinition用于定义bean的信息，与在mybatis-spring中使用的ScannedGenericBeanDefinition
    //       3、factoryBean：用于处理bean工厂的类

    //主要做的事情是将类的代理注册到spring中，把对象bean交给spring管理，也就起到了"代理"的作用




}
