<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <link rel="stylesheet" href="WEB-STYLE/login.css"/>
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Login</title>
    </head>
    <jsp:include page="/Views/Shared/headerBody.jsp" /> 
    <body>
        <main class="container">   
            <form action="Usuario?accion=login" method="post">
                <h5>Login</h5>
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">                
                <div class="row">
                    <div class="input-field col 19 s12">                                             
                        <i class="material-icons prefix">account_circle</i>
                        <input  id="txtLogin" type="text" name="login" required class="validate" maxlength="25">  
                        <label for="txtLogin">Login</label>
                    </div>                                       
                </div>
                <div class="row">
                    <div class="input-field col 19 s12">                                             
                        <i class="material-icons prefix">enhanced_encryption</i>
                        <input  id="txtPassword" type="password" name="password" required class="validate" minlength="5" maxlength="32">  
                        <label for="txtPassword">Password</label>
                    </div>                                       
                </div>
                <div class="row">
                    <div class="col l12 s12">
                        <button type="sutmit" class="waves-effect waves-light btn red"><i class="material-icons right">send</i>Login</button>                                               
                    </div>
                </div>
                <% if (request.getAttribute("error") != null) {%>
                <div class="row">
                    <div class="col l12 s12">
                        <span style="color:red"><%= request.getAttribute("error")%></span>                                              
                    </div>
                </div>
                <%}%>
            </form>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />      
    </body>
</html>

