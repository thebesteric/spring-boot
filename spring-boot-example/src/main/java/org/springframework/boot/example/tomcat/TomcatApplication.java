package org.springframework.boot.example.tomcat;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletRegistration;

public class TomcatApplication {

	public static int PORT = 8080;
	public static String CONTEXT_PATH = "/test";

	public static void run() {
		Tomcat tomcat = new Tomcat();
		String baseDir = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		tomcat.setPort(PORT);
		tomcat.getConnector().setURIEncoding("UTF-8");

		try {
			Context context = tomcat.addWebapp(CONTEXT_PATH, baseDir);

			context.addServletContainerInitializer((c, servletContext) -> {
				// 注册 servlet
				ServletRegistration.Dynamic testServlet = servletContext.addServlet("testServlet", new TestServlet());
				testServlet.addMapping("/hello");
			}, null);

			// 启动 tomcat
			tomcat.start();
		} catch (LifecycleException e) {
			e.printStackTrace();
		}

		System.out.printf("Tomcat run in %d\n", PORT);

		// 阻塞
		tomcat.getServer().await();
	}
}
