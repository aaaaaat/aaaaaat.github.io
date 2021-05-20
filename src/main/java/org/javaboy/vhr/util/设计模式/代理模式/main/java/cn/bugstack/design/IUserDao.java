package org.javaboy.vhr.util.设计模式.代理模式.main.java.cn.bugstack.design;


import org.javaboy.vhr.util.设计模式.代理模式.main.java.cn.bugstack.design.agent.Select;

public interface IUserDao {

    //添加自定义注解select，与mybatis组件是一样的
    @Select("select userName from user where id = #{uId}")
    String queryUserInfo(String uId);

}
