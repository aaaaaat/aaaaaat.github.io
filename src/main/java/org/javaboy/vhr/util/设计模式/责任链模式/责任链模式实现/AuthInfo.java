package org.javaboy.vhr.util.设计模式.责任链模式.责任链模式实现;

/**
 * 责任链中返回对象定义
 * 作用：包装责任链处理过程中返回结果的类，方便处理每个责任链的返回信息
 */
public class AuthInfo {

    private String code;
    private String info = "";

    // String…是java5新加入的功能，表示的是一个可变长度的参数列表。
    // 其语法就是类型后跟…，表示此处接受的参数为0到多个Object类型的对象
    // 或者是一个Object[]
    // 例如我们有一个方法叫做test(String…strings)
    public AuthInfo(String code, String ...infos) {
        this.code = code;
        for (String str:infos){
            this.info = this.info.concat(str);
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
