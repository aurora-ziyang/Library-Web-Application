The first method of Jsp¡¢Servlet¡¢JavaBean configuration:
(1) Modify the web.xml in Tomcat/conf
    Made <!-- The mapping for the invoker servlet --> into valid

    <servlet-mapping>
        <servlet-name>invoker</servlet-name>
        <url-pattern>/servlet/*</url-pattern>
    </servlet-mapping>
(2) Use servlet/ in *.html or *.jsp when call servlet
    examples:
    <form action="servlet/SearchController" method="post">
(3) Config the web.xml in WEB-INF
    examples:
	
<?xml version="1.0" encoding="ISO-8859-1"?> 

<!DOCTYPE web-app
	PUBLIC "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN" 
	"http://java.sun.com/dtd/web-app_2_3.dtd"> 

<web-app>

	<display-name>My Web Application</display-name> 
	<description> 
		A Library System. 
	</description>  

</web-app>
(4) Use the name of application to redirect use *.jsp in servlet
    examples:
    res.sendRedirect("/Library/index.jsp");
(5) Use JavaBean in *.jsp
    examples:
    <jsp:useBean id="tags" scope="page" class="library.CommonTags" />


The second method of Jsp¡¢Servlet¡¢JavaBean configuration:
(1) Use the name of application in *.html or *.jsp when call servlet
    examples:
    <form action="/libraryWithMySQL/SearchController" method="post">
(2) Config the web.xml in WEB-INF
    examples:
	
<?xml version="1.0" encoding="ISO-8859-1"?> 

<!DOCTYPE web-app
	PUBLIC "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN" 
	"http://java.sun.com/dtd/web-app_2_3.dtd"> 

<web-app>

	<display-name>My Web Application</display-name> 
	<description> 
		A Library System. 
	</description> 
	
	<!-- Define servlets that are included in the WebDatabase application -->
	<servlet> 
		<servlet-name>SearchController</servlet-name> 
		<display-name>SearchController</display-name> 
		<description>A Servlet controller</description> 
		<servlet-class>SearchController</servlet-class> 
	</servlet> 

	...
	
	<servlet-mapping>
		<servlet-name>invoker</servlet-name>
		<url-pattern>/servlet/*</url-pattern>
	</servlet-mapping>
	
	<servlet-mapping> 
		<servlet-name>SearchController</servlet-name> 
		<url-pattern>/SearchController</url-pattern> 
	</servlet-mapping> 

	...
</web-app>
(3) Redirect use *.jsp in servlet
    examples:
    response.sendRedirect("index.jsp");
(4) Use JavaBean in *.jsp
    examples:
    <jsp:useBean id="member" scope="session" class="library.Member" />

