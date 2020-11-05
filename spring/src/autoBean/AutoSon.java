package autoBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xuyuc
 * @since 2020/10/25 20:55
 */
@Component
public class AutoSon {
    @Autowired
    AutoParent parent;
    private String name;        //姓名
    private int age;            //年龄

    public AutoSon() {
    }

    public AutoSon(AutoParent parent, String name, int age) {
        this.parent = parent;
        this.name = name;
        this.age = age;
    }

    public AutoParent getParent() {
        return parent;
    }

    public void setParent(AutoParent parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "son属性 { parent" + parent + ", name=" + name + ", age=" + age + "}";
    }
}
