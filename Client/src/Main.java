

import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.glassfish.internal.api.Globals;

import com.sun.enterprise.module.ModulesRegistry;
import com.sun.enterprise.module.bootstrap.StartupContext;
import com.sun.enterprise.module.single.StaticModulesRegistry;

import session.StatelessEjbRemote;

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Properties properties = new Properties();
		properties.setProperty("AS_DERBY_INSTALL",
		"C:\\Users\\maria\\Downloads\\glassfish-5.0.1\\glassfish5\\javadb");
		ModulesRegistry modulesRegistry = new
		StaticModulesRegistry(Globals.class.getClassLoader(), new
		StartupContext(properties));
		Globals.setDefaultHabitat(modulesRegistry.createServiceLocator("default"));
		
		try {
			InitialContext context = new InitialContext();
			StatelessEjbRemote firstEjb = (StatelessEjbRemote) context
					.lookup("java:global/EAR/EJB/StatelessEjb!session.StatelessEjbRemote");
			System.out.println("AAA");
			System.out.println(firstEjb.getClass().getName());
			firstEjb.insert();
		}catch(NamingException ex) {
			System.out.println(ex.toString());
		}
		
//		java:global/EAR/EJB/StatelessEjb!session.StatelessEjbRemote		
	}

}