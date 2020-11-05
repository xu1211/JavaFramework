package xmlBean;

/**
 * @author xuyuc
 * @since 2020/10/25 20:55
 */
public class Son {
    Parent parent;
    private String name;        //姓名
    private int age;            //年龄

    public Son() {
    }

    public Son(Parent parent, String name, int age) {
        this.parent = parent;
        this.name = name;
        this.age = age;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
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
