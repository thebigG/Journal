package JournalApp;

public  class JournalThreadManager<E extends JournalThread> extends Thread 
{
private E thisThread;
private E[] ListOdfThreads;
/**
 * 
 * @param ThisThread The thread to be managed
 */
 public JournalThreadManager(E ThisThread)
 {
	 this.thisThread = ThisThread;
 }
 /**
  * DONT delete this constructor. It could be used to manage multiple threads.
  * @param ThisThread
  */
 public JournalThreadManager(E... ThisThread)
 { 
 }
 public synchronized void run()
 {
	 thisThread.runJournalThread();
 }
 public void DumpAllThreads()
 {
	 
 }
 public  void rest() throws InterruptedException
 {
	 synchronized(thisThread)
	 {
	 //thisThread.wait(timeout);(JournalKit.getMilliseconds(3));
	 }
 }
 public void setThisThread(E thread)
 {
	 thisThread = thread;
 }
 public JournalThread getThisThread()
 {
	 return thisThread;
 }
 
}
