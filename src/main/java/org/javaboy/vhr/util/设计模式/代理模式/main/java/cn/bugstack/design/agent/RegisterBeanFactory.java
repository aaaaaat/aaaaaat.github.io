package org.javaboy.vhr.util.设计模式.代理模式.main.java.cn.bugstack.design.agent;


import org.javaboy.vhr.util.设计模式.代理模式.main.java.cn.bugstack.design.IUserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * 将bean定义注册到spring容器
 * BeanDefinitionRegistryPostProcessor:spring的接口类用于处理对bean的定义操作
 * 将代理的bean交给spring容器管理，可以非常方便地获取代理的对象bean
 * 这部分是spring中关于一个对象bean注册过程的源码
 */
public class RegisterBeanFactory implements BeanDefinitionRegistryPostProcessor {



    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

        //GenericBeanDefinition用于
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();

        //定义一个对象bean的基本信息setBeanClass(MapperFactoryBean.class)
        beanDefinition.setBeanClass(MapperFactoryBean.class);
        beanDefinition.setScope("singleton");
        //也可以把IUserDao接口类通过addGenericArgumentValue(IUserDao.class)透传给构造函数（对IUserDao中的所有@select进行解析）
        beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(IUserDao.class);

        //xml文件中 id "userDao" 对应起来
        BeanDefinitionHolder definitionHolder = new BeanDefinitionHolder(beanDefinition, "userDao");

        //---------------------给接口生成一个实现类---------------------------

        //最后使用registerBeanDefinition(definitionHolder, registry)注册对象bean
        //也就是注册到DefaultListableBeanFactory中（BeanFactory）
        //代理的方式给接口生成一个实现类，并交给spring管理
        BeanDefinitionReaderUtils.registerBeanDefinition(definitionHolder, registry);


    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        // left intentionally blank
    }


    /**
     * 可以看到接口IUserDao咩有一个硬编码的实现类，而是使用代理的方式给接口生成一个实现类，并交给spring管理
     */
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(RegisterBeanFactory.class);

        //加载bean工厂（beanFactory）
        BeanFactory beanFactory =
                new ClassPathXmlApplicationContext("spring-config.xml");

        //获取代理类的实例对象
        //根据id获取对应管理的bean IUserDao
        IUserDao userDao = beanFactory.getBean("userDao", IUserDao.class);

        //调用方法并返回结果
        String res = userDao.queryUserInfo("100001");

        logger.info("测试结果：{}", res);
    }

}
