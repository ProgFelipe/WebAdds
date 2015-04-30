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
        amq.init({ 
            uri: 'AdCliente?channelName=Ciencia&username=sergio', 
            //uri: 'http://localhost:8161/amq',
            //uri: 'tcp://127.0.0.1:52125',
            //uri: 'amq',
            logging: true,
            timeout: 20
           });
        var myDestination='topic://Ciencia';
        //var myDestination='AdCliente?channelName=Ciencia&username=felipe&get=getMessage';
        var myMessage = '<message>foooooo barrrr</message>';
        var myId = 'felipe';
        
        
            var myHandler =
    { 
  rcvMessage: function(message)
  { 
      //var msg_data="<li>"+message.xml+"</li>";
      //$(msg_data).appendTo(".event-list");
     alert("received "+message);
  
  }
    };
    console.log("Lets create a listener");
     //amq.addListener(myId,myDestination,myHandler.rcvMessage,{Selector: "identificador = 'TEST'" });
    amq.addListener(myId,myDestination,myHandler.rcvMessage,  { selector: "property-name='property-value'" });
    console.log("Lets add a message");
    amq.sendMessage(myDestination, myMessage);
    
   /*try {
  //amq.sendMessage(myDestination, myMessage);
  amq.sendMessage(myDestination, myMessage);
  } catch (err) {
    alert(err);
  }*/
       }
               );
       </script>
<script type="text/javascript">
    
    /*
     function cliente(){
 $.get("AdCliente?username=felipe&channelName=Ciencia", function(data){
        alert("Data: " + data);
    });
       }
     */
</script>
<script type='text/javascript'>
function getMessages(){
   //if(currentUser != null){
   
$.ajax
({
type: "GET",
url: "AdCliente?username=felipe&channelName=Ciencia",
dataType:"json",
success: function(data)
{
    console.log(data.length);
if(data.length)
{
    
    $( ".event-list" ).empty();
$.each(data, function(i,data)
{
    console.log(data.Message);
var msg_data="<li>"+data.Message+"</li>";

$(msg_data).appendTo(".event-list");
});
}
else
{
$(".event-list").html("No Subscriptions");
}
}
});
} 
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
    <button onclick="getMessages();">Get Messages Ciencia</button>
    </div>
    <div class="container">
    <ul class="event-list" id="event-list">
    </ul>
        <ul class="ajax-response" id="ajax-response">
            
        </ul>
    </div>
</div>
</div>
        
    </body>
</html>
