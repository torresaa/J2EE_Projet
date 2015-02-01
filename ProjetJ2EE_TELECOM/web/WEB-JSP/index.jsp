<%-- 
    Document   : newjsp
    Created on : Feb 1, 2015, 2:45:46 PM
    Author     : aquilest
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://java.sun.com/jsf/html"
      xmlns:ui="http://java.sun.com/jsf/facelets">
    <h:head>
            <title>JSP Page</title>
            <link rel="stylesheet" type="text/css" href="css/stylesheet.css" />
            <link href="fonts.googleapis.com/css?family=Lato:300,400,700,900" rel="stylesheet" type="text/css" />
    </h:head>
    <h:body>
        <section id="header" class="header">
            <div id="title" >
                <h1>e-commerce</h1>
            </div>
                <nav>
                    <ul id="menu">
                        <li>
                            <a id="search" onclick="">
                                <h4> <f:insert name="title" >SEARCH</h4>
                            </a>
                        </li>
                        <li>
                            <a id="login">
                                <h4>LOGIN</h4>
                            </a>
                        </li>
                        <li>
                            <a id="cady">
                                <h4>CHARRIOT</h4>
                            </a>
                        </li>
                    </ul>
                </nav>
        </section>
        <div class="tituloSecciones">Hola</div>
        <h1>Hello World!</h1>
        <section class="" ></section>
    </h:body>
</html>
