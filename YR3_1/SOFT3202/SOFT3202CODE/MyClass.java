

public class MyClass {
    private MyDependency dependency;
    
    public MyClass(MyDependency dependency) {
        this.dependency = dependency;
    }
    
    public String doSomething() {
        // you don't know what is in here
    	dependency.getSomeResult("123", 123);
    	dependency.getSomeResult("123", 123);
    	dependency.getSomeResult("123", 123);
    	dependency.getSomeResult("123", 123);
    	return null;
    }
}
