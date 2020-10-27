https://github.com/spring-projects/spring-framework

## BeanFactory
spring-beans-5.2.3.RELEASE.jar!\org\springframework\beans\factory\BeanFactory.class

一切从BeanFactory接口说起
>把所有的组件都被当成Bean处理, 是生成Bean实例的工厂

![BeanFactory继承关系](https://www.javadoop.com/blogimages/spring-context/2.png)
- BeanFactory 获取单个 Bean
  - ListableBeanFactory 获取多个 Bean
  - HierarchicalBeanFactory 将多个 Bean 设置为父子关系, 提供父容器的访问功能
  - AutowireCapableBeanFactory  自动装配 Bean 




## ApplicationContext
spring-context-5.2.3.RELEASE.jar!\org\springframework\context\ApplicationContext.class

继承ListableBeanFactory与HierarchicalBeanFactory,而且接口定义中有个getAutowireCapableBeanFactory()方法

spring从ApplicationContext的实现类启动

启动过程中，会负责创建实例 Bean，往各个 Bean 中注入依赖
![ApplicationContext](https://www.javadoop.com/blogimages/spring-context/1.png)

ApplicationContext从配置源获取信息，实例化为bean
1. 在XMl中进行显示配置
2. 在Java中进行显示配置
3. 隐式的bean发现机制和自动装配
   1. *组件扫描（component scanning）：Spring会自动发现应用上下文中所创建的bean。
   2. *自动装配（autowiring）：Spring自动满足bean之间的依赖。

ApplicationContext常用实现类:
- ClassPathXmlApplicationContext

从类路径下的xml配置文件中加载
- FileSystemXmlApplicationContext

从系统盘符中的xml配置文件中加载
- AnnotationConfigApplicationContext

从基于java的配置类中的java注解加载
- AnnotationConfigWebApplicationContext

专门为web应用准备的，从基于java的配置类中的java注解加载
- XmlWebApplicationContext

从web应用下的xml配置文件加载上下文定义，适用于xml配置方式。



### ClassPathXmlApplicationContext 启动过程分析

```java
public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationfile.xml");
    context.getBean(MessageService.class);
}
```


主要是 refresh()
1. prepareRefresh() 创建 Bean 容器前的准备工作
   1. 记录启动时间
   2. getEnvironment().validateRequiredProperties();校验 xml 配置文件
2. 创建 Bean 容器，加载并注册 Bean
   1.  refreshBeanFactory(); 当前ApplicationContext 如果有旧的 BeanFactory 关闭，创建新的 BeanFactory，加载 Bean 定义、注册 Bean 等等
   2. getBeanFactory(); 返回刚刚创建的 BeanFactory




