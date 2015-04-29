<%-- 
    Document   : fuentetest
    Created on : 27-abr-2015, 22:06:10
    Author     : Felipe
--%>

<%@page import="Bean.UserBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
        <script><%@include file="js/amq_jquery_adapter.js" %></script>
        <script><%@include file="js/amq.js" %></script>
        <script type="text/javascript">
            /*
             * 
             * @type String
             * Tomado de: http://activemq.apache.org/ajax.html
             * js: https://code.google.com/p/cxldemo/source/browse/trunk/activemq/src/main/webapp/js/amq_jquery_adapter.js?r=1376
             * https://svn.apache.org/repos/asf/activemq/trunk/activemq-web-demo/src/main/webapp/js/amq.js
             */
    $(document).ready(
       function(){
        var amq = org.activemq.Amq;
        $("<p>Mensajes:</p>").appendTo(".event-list");
        amq.init({ 
            uri: 'AdCliente?channelName=Ciencia&username=felipe', 
            logging: true,
            timeout: 20
           });
            var myHandler =
    { 
  rcvMessage: function(message)
  {  
      var msg_data="<li>"+message+"</li>";
      $(msg_data).appendTo(".event-list");
     alert("received "+message);
  }
    };
 
 //var myDestination='queue://com.broadworks.dms.client';
  var myDestination='AdCliente?channelName=Ciencia&username=felipe';
  var myMessage = '<message>Ciencia Sains!</message>';
  var myId = 'felipe';
    amq.addListener(myId,myDestination,myHandler.rcvMessage);
    //amq.addListener(myId,myDestination,function(message){alert(message);});
    /*try {
  amq.sendMessage(myDestination, myMessage);
  } catch (err) {
    alert(err);
  }*/
       }
               );
       </script>
<script type="text/javascript">
    function cliente(){
    $.get("AdCliente?username=felipe&channelName=Ciencia", function(data){
        alert("Data: " + data);
    });}
</script>
    </head>
    <body>
        <h1>Hello World!</h1>
        <div class="container">
<div class="row">
<form id="login-form" action="AdFuente" role="form" style="display: block;">
    <div class="form-group">
        <input type="text" readonly name="channelName" id="channelName" tabindex="1" class="form-control" placeholder="channelName" value="Ciencia">
    </div>
    <div class="form-group">
        <input type="text" name="myMessage" id="myMessage" tabindex="1" class="form-control" placeholder="Message" value="Mensaje">
    </div>    
    <div class="form-group">
        <input type="text" name="url" id="url" tabindex="1" class="form-control" placeholder="Media url" value="http://url">
    </div>    
    <div class="form-group">
    <div class="row">
    <div class="col-sm-6 col-sm-offset-3">
    <input type="submit"  id="submit" tabindex="4"  value="Send Message">
        </div>
        </div>
    </div>
</form>
    <div class="container">
    <ul class="event-list" id="event-list">
    </ul>
    </div>
</div>
</div>
        <!--<button onclick="cliente();">Create Cliente</button>-->
    </body>
</html>
