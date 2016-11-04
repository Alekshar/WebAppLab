package fr.alekshar.webapplab.classes;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ThreadsManager implements ServletContextListener {

	private static SocketsCountdownThread thread;

    public void contextInitialized(ServletContextEvent sce) {
    	if(thread == null || !thread.isAlive()){
			thread = new SocketsCountdownThread();
			thread.start();
		}
    }

    public void contextDestroyed(ServletContextEvent sce){
        try {
        	thread.interrupt();
        } catch (Exception ex) {
        }
    }
}