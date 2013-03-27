
public class ClassToInject {
	
	 final int var = 10;
 
	public int getVar() {
		return var;
	}

	private static final ClassToInject CLASS_TO_INJECT = new ClassToInject(); 
	private ClassToInject(){}
	
	public static ClassToInject newInstance(){
		return CLASS_TO_INJECT;
	}
}
