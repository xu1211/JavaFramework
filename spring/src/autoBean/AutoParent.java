package autoBean;

import org.springframework.stereotype.Component;

/**
 * @author xuyuc
 * @since 2020/10/29 21:45
 */

@Component
public class AutoParent {
    private String name;        //姓名
    private int age;            //年龄

    public AutoParent() {
    }

    public AutoParent(String name, int age) {
        this.name = name;
        this.age = age;
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
        return "属性{ name=" + name + ", age=" + age + "}";
    }
}
