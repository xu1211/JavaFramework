# spring
- [测试类](./src/test/TestSpring.java)
- [配置文件](./src/applicationContext.xml)
- [xml方式 bean](./src/xmlBean)
- [注解方式 bean](./src/autoBean)

---

官方源码 https://gitee.com/mirrors/Spring-Framework

个人博客 https://blog.csdn.net/xyc1211/article/details/109514123

---
- [IOC/DI 源码解析](#IOC/DI源码解析)
  - [BeanFactory接口](#BeanFactory接口)
  - [ApplicationContext接口](#BeanFactory接口)
    - ClassPathXmlApplicationContext启动过程

---
# IOC/DI源码解析

## BeanFactory接口
https://gitee.com/mirrors/Spring-Framework/blob/master/spring-beans/src/main/java/org/springframework/beans/factory/BeanFactory.java


## ApplicationContext接口

https://gitee.com/mirrors/Spring-Framework/blob/master/spring-context/src/main/java/org/springframework/context/ApplicationContext.java

继承ListableBeanFactory与HierarchicalBeanFactory,而且接口定义中有个getAutowireCapableBeanFactory()方法

### ClassPathXmlApplicationContext 启动过程分析
```java
public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationfile.xml");
    Son user = context.getBean(Son.class);
    user.toString();
}
```

https://gitee.com/mirrors/Spring-Framework/blob/master/spring-context/src/main/java/org/springframework/context/support/ClassPathXmlApplicationContext.java

主要是继承自 [AbstractApplicationContext 抽象类的 refresh() 方法](https://gitee.com/mirrors/Spring-Framework/blob/master/spring-context/src/main/java/org/springframework/context/support/AbstractApplicationContext.java
)
