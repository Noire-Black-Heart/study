

public class MyClass {
    private MyDependency dependency;
    
    public MyClass(MyDependency dependency) {
        this.dependency = dependency;
    }
    
    public String doSomething() {
        // you don't know what is in here
    	return null;
    }
}
