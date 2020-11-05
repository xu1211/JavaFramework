package xmlBean;

/**
 * @author xuyuc
 * @since 2020/10/29 21:45
 */
public class Parent {
    private String name;        //姓名
    private int age;            //年龄

    //不加构造方法会报错：No matching constructor found in class
    public Parent() {
    }

    public Parent(String name, int age) {
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
