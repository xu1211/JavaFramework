源码 https://gitee.com/mirrors/Spring-Framework

- [IOC/DI 源码解析](#IOC/DI源码解析)
  - [BeanFactory接口](#BeanFactory接口)
  - [ApplicationContext接口](#BeanFactory接口)
    - ClassPathXmlApplicationContext启动过程

---
# IOC/DI源码解析
>把所有的对象都被当成Bean处理, IOC/DI是管理Bean实例的工厂

## BeanFactory接口
https://gitee.com/mirrors/Spring-Framework/blob/master/spring-beans/src/main/java/org/springframework/beans/factory/BeanFactory.java

一切从BeanFactory接口说起

![BeanFactory继承关系](https://www.javadoop.com/blogimages/spring-context/2.png)
- BeanFactory 获取单个 Bean
  - ListableBeanFactory 获取多个 Bean
  - HierarchicalBeanFactory 将多个 Bean 设置为父子关系, 提供父容器的访问功能
  - AutowireCapableBeanFactory  自动装配 Bean 


## ApplicationContext接口

https://gitee.com/mirrors/Spring-Framework/blob/master/spring-context/src/main/java/org/springframework/context/ApplicationContext.java

继承ListableBeanFactory与HierarchicalBeanFactory,而且接口定义中有个getAutowireCapableBeanFactory()方法


![ApplicationContext](https://www.javadoop.com/blogimages/spring-context/1.png)

- ApplicationContext常用实现类:
  1. **ClassPathXmlApplicationContext**
从类路径下的xml配置文件中加载
  2. FileSystemXmlApplicationContext
从系统盘符中的xml配置文件中加载
  3. AnnotationConfigApplicationContext
从java的配置类中的java注解加载
  4. AnnotationConfigWebApplicationContext
从java的配置类中的java注解加载，专门为web应用准备的
  5. XmlWebApplicationContext
从web应用下的xml配置文件加载

---

>spring从ApplicationContext的实现类启动
ApplicationContext启动过程中，从配置源获取信息，实例化为bean，往各个 Bean 中注入依赖
- 配置源
    1. 在XMl中进行显示配置
    2. 在Java中进行显示配置
    3. 隐式的bean发现机制和自动装配
       1. *组件扫描（component scanning）：Spring会自动发现应用上下文中所创建的bean。
       2. *自动装配（autowiring）：Spring自动满足bean之间的依赖。

### 注释方式

#### Bean定义
  - @Component ：通用的注解，可标注任意类为 Spring 组件。如果一个Bean不知道属于哪个层，可以使用@Component 注解标注。
  - @Component衍生注解
    1. @Controller : 对应 Spring MVC 控制层，主要用于接受用户请求并调用 Service 层返回数据给前端页面。
    2. @Service : 对应服务层，主要涉及一些复杂的逻辑，需要用到 Dao层。
    3. @Repository : 对应持久层即 Dao 层，主要用于数据库相关操作。
    
#### Bean注入
  - @Autowired 默认按类型装配（byType）自动装配
  - @Resource(Bean名称) 按名称装配（byName）
  - @Inject


#### Bean作用范围、生命周期
- @PostConstruct初始化
- @PreDestroy销毁
- @Scope作用范围设置

#### 配置类
- @Configuration 
- @Bean
#### 配置读取
@Value("${property}")
@ConfigurationProperties

#### sringMVC
读取 Request 请求
@RestController = @Controller + @ResponseBody
- @GetMapping()
- @PostMapping()
- @PutMapping
- @DeleteMapping
- 掌握前后端传值
   - @PathVariable用于获取路径参数，@RequestParam用于获取查询参数。
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
