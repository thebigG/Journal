package journalUtilities;
import java.io.*;
import java.util.*;


/**
 * This class is meant to be FUNCTIONAL. It simulates the console of the machine. It also has a collection 
 * of methods/functions that command the console of the OS.
 * @author LorenzoGomez
 *
 */
public class Console 
{

static Runtime myRunTime;
private static Console thisConsole;
//private static Executor thisExecutor;
static final String javaMachinesVersionsCommand = "/usr/libexec/java_home -V";
static final String changeJavaVersionCommand  = "export JAVA_HOME=`/usr/libexec/java_home -v 1.7.0_45`";
static ProcessBuilder thisProcessBuilder;
	private Console()
	{
		myRunTime = Runtime.getRuntime();
		//thisExecutor = new DefaultExecutor();
	}
	public static void main(String[] args) throws IOException
	{
		instiateThis();
	//	commandExecutor("export JAVA_HOME=`/usr/libexec/java_home -v 1.7.0_45`" ,"src/");
	//	commandConsole("export JAVA_HOME=`/usr/libexec/java_home -v 1.7.0_45`");
		changeEnvironmentVariable("JAVA_HOME", "/usr/libexec/java_home -v 1.7.0_45");
		//commandExecutor("java -version", "src/");
		//commandExecutor("ls" ,"/");journ
		//commandExecutor("source Hello.sh","src/journalUtilities");
		//commandConsole("javac /Users/lorenzogomez/Documents/Mac Projects/Journal/src/journalUtilities/HelloWorld.java");
//		 ArrayList<ArrayList<String>> x = getConsoleOutputFrom("ls");
//		for(int i = 0; i<x.get(0).size();i++)
//		{
//			System.out.println(x.get(0).get(i));
//		}
		//commandConsole("java HelloWorld");
		//selectJDKOnMac(Console_Type.JDK7);
	}
/**
 * This method changes the current JDK that the machine is using. 
 * @param javaVersion The desired java version.
 * @throws IOException 
 */
public static void instiateThis()
{
	thisConsole  = new Console();
}
public static Console getThisConsole()
{
	return thisConsole;
}
public static Runtime getThisRunTime()
{
	return myRunTime;
}
public static void selectJDKOnMac(Console_Type javaVersion) throws IOException
{
	 ArrayList<ArrayList<String>> Output = new ArrayList<ArrayList<String>>(2);
	 Output = getConsoleOutputFrom(javaMachinesVersionsCommand);
	 String[] javaVersions = new String[Output.get(1).size()];
	 String javaVersionTxt  = "";
	 @SuppressWarnings("unused")
	String changeVersionCommand = "";
	 switch(javaVersion)
	 {
	 case JDK1:
		 javaVersionTxt = "1.1";
		 break;
	 case JDK2:
		 javaVersionTxt = "1.2";
		 break;
	 case JDK3:
		 javaVersionTxt = "1.3";
		 break;
	 case JDK4:
		 javaVersionTxt = "1.4";
		 break;
	 case JDK5:
		 javaVersionTxt = "1.5";
		 break;
	 case JDK6:
		 javaVersionTxt = "1.6";
		 break;
	 case JDK7:
		 javaVersionTxt = "1.7";
		 break;
	 case JDK8:
		 javaVersionTxt = "1.8";
		 break;
		 default:
			 break;
	 }
	 for(int i = 0;i<javaVersions.length;i++)
	 {
		 javaVersions[i] = Output.get(1).get(i).split(",")[0];
		 if(javaVersions[i].contains(javaVersionTxt))
		 { 
			 System.out.println("this is  the java version : " + javaVersions[i]);
			 System.out.println(getConsoleOutputFrom(javaMachinesVersionsCommand).get(0).get(0));
			 changeVersionCommand = changeJavaVersionCommand +  javaVersions[i] + "`";
			// String[] command  ="export JAVA_HOME=`/usr/libexec/java_home -v 1.7.0_45`".split(" ") ;
			// System.out.println(getConsoleOutputFrom("/Journal/src/Scripts/ChangeJDK.sh").get(0).get(0));
			// System.out.println(getConsoleOutputFrom(javaMachinesVersionsCommand).get(1));
			 System.out.println(getConsoleOutputFrom("bash").get(1).get(0));
			 System.out.println(getConsoleOutputFrom(changeJavaVersionCommand + javaVersions[i]).get(0).get(0) + "`");
			// System.out.println(getConsoleOutputFrom(javaMachinesVersionsCommand).get(0).get(0));
//			 ProcessBuilder pb = new ProcessBuilder("source", "ChangeJDK.sh");
//			 Map<String, String> env = pb.environment();
//			 pb.directory(new File("/Journal/src/Scripts/"));
//			 Process p = pb.start();
			 break;
		 }
	 }
	 
	 
	 
}
/**
 *  This method executes a command specified by the String command and will return the output
 * displayed on the command line after this command is executed 
 * @param command the command to be executed by the OS's command line
 * @return An ArrayListt with two rows  of 2 types of strings. The first row is the standard output that the command line
 * will return. And the second one is the error output that the command line returns.
 * @Note In some cases the "error output(the second String of the array)" will return 
 * output that is not part of an error. So "error" and "standard" are just labels that don't necessarily 
 * mean what they say. It is recommended that both strings are checked. 
 * @Note: This function Java's standard execute method from the Runtime class
 * @throws IOException 
 */
public static ArrayList<ArrayList<String>>  getConsoleOutputFrom(String command) throws IOException
{
	 ArrayList<ArrayList<String>> Output = new ArrayList<ArrayList<String>>(2);
	 Output.add( 0,new ArrayList<String>());
	 Output.add(1 ,new ArrayList<String>());
	Process proc = myRunTime.exec(command);
	BufferedReader stdInput = new BufferedReader(new 
	     InputStreamReader(proc.getInputStream()));
	BufferedReader stdError = new BufferedReader(new 
	     InputStreamReader(proc.getErrorStream()));
	// read the output from the command
	//System.out.println("Here is the standard output of the command:\n");
	String s = null;
	 while ((s = stdInput.readLine()) != null)
	{
	    //System.out.println(s);
		Output.get(0).add(s);
	}

	// read any errors from the attempted command
//	System.out.println("Here is the standard error of the command (if any):\n");
	while ((s = stdError.readLine()) != null) {
	   // System.out.println(s);
		Output.get(1).add(s);
	}
	return Output;
}
/**
 * This method commands the OS's command line. 
 * @param command the command to send to the OS's command line.
 * @Note: This method Java's standard execute method from the Runtime class
 * @throws IOException 
 */
public static void commandConsole(String command) throws IOException
{
	myRunTime.exec(command);
}

/**
 * This method commands the OS's command line
 * @param instruction the command to send the OS's command line.
 * @param dir The directory where you would like this command to be executed on.
 * @throws ExecuteException
 * @throws IOException
 * @Note: This method uses Apache's executor 
 */
//public static void commandExecutor(String instruction, String dir) throws ExecuteException, IOException
//{
//	CommandLine command = CommandLine.parse(instruction);
//	thisExecutor.setWorkingDirectory(new File(dir));
//	CommandLine cl = new CommandLine(command);
//	thisExecutor.execute(cl);
//} 
public static void changeEnvironmentVariable(String var, String dir) throws IOException
{
	thisProcessBuilder = new ProcessBuilder("java -version", "src/");
	thisProcessBuilder.environment().put(var, dir);
	thisProcessBuilder.start();
}
}
