package test;

import autoBean.AutoSon;
import xmlBean.Son;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author xuyuc
 * @since 2020/10/25 20:58
 */
public class TestSpring {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Son xmlSon = context.getBean(Son.class);
        System.out.println(xmlSon.toString());

        AutoSon autoSon = context.getBean(AutoSon.class);
        System.out.println(autoSon.toString());

    }
}
