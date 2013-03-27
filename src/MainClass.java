import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Field;

import sun.org.mozilla.javascript.internal.ObjArray;


public class MainClass {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws Exception   {
		
		boolean succesfullyInjected = false;
		 Class<?> testClass = Class.forName("Sample");
		 Object object = testClass.newInstance();
		// Sample s = new Sample();
		
		 
	        for (Method m : testClass.getDeclaredMethods()) {
	        	if (m.isAnnotationPresent(DoubleInvoke.class)) {
	        		System.out.println(m.toString());
	        		m.invoke(null);
	        		m.invoke(null);
				} else if(m.isAnnotationPresent(ParamPass.class)){
					try {
					
	                    m.invoke(null);
	                    
	                } catch (Throwable wrappedExc) {
	                    Throwable exc = wrappedExc.getCause();
	                    Class<? extends Exception> excTypes =
	                        m.getAnnotation(ParamPass.class).value();
	                       System.out.println("catch arithmetc ex");  
	                    
	                   }
				}else if(m.isAnnotationPresent(Inject.class)){
					String nameOfClassToInject = m.getAnnotation(Inject.class).value();
					System.out.println(nameOfClassToInject);
					
					m.setAccessible(true);
					Class<?> classToInject = Class.forName(nameOfClassToInject);
					
					//if consturctor will be public
					//Object obj =  classToInject.newInstance();
					
					//with using singleton
					Method method = classToInject.getMethod("newInstance");
					Object o = method.invoke(null);
					
					System.out.println(o.getClass().getCanonicalName());
					
					
					m.invoke(object, o);
				
					succesfullyInjected=true;
				}
	        	
	        	else if(succesfullyInjected)
				{
	        		
					m.invoke(null);
				}
					
	        	
	        }
	        
	        Method method = testClass.getMethod("useInjectedClass");
	        method.invoke(object,null);
	        
	        
	}

}
