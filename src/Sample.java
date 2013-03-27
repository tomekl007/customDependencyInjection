import java.util.*;

public class Sample {

	@DoubleInvoke
	public static void twoTimesInvoke(){
		System.out.println("towTimesInvoke");
	}
	
	
	
	ClassToInject classToInject;
	@Inject("ClassToInject")
	public void setClassToInject(ClassToInject classToInject) {
		System.out.println("injecting " + classToInject);
		this.classToInject = classToInject;
	}

	public static void oneTimeInvoke(){
		System.out.println("oneTimeInvoke");
	
		
	}
	
	@ParamPass(ArithmeticException.class)
	public static void throwException(){
		int i = 2/0;
	}
	
	public void useInjectedClass(){
		System.out.println(" get value form injected class : "  + classToInject.getVar());
		
	}
	
}
