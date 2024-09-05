package task_4;

import java.lang.reflect.Constructor;

public class CustomClass {
    private String name;
    private int age;

    public CustomClass() {
        this.name = "Unknown";
        this.age = 0;
    }

    public CustomClass(String name) {
        this.name = name;
        this.age = 0;
    }

    public CustomClass(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static void main(String[] args) {
        try {
            // Get declared constructors
            Constructor<?>[] constructors = CustomClass.class.getDeclaredConstructors();
            for (Constructor<?> constructor : constructors) {
                System.out.println("Constructor: " + constructor);
                System.out.println("Parameter types:");
                for (Class<?> paramType : constructor.getParameterTypes()) {
                    System.out.println(paramType.getName());
                }
            }

            Constructor<CustomClass> constructor = CustomClass.class.getConstructor(String.class, int.class);
            CustomClass obj = constructor.newInstance("Vadym", 30);
            System.out.println("Created instance: " + obj.name + ", " + obj.age);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
