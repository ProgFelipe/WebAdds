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
    $(document).ready(
       function(){
        var amq = org.activemq.Amq;
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
  var myDestination='AdFuente';
  var myMessage = '<message>Ciencia Sains!</message>';
  var myId = 'felipe';
    //amq.addListener(myId,myDestination,myHandler.rcvMessage);
    amq.addListener(myId,myDestination,function(message){alert(message);});
    /*try {
  amq.sendMessage(myDestination, myMessage);
  } catch (err) {
    alert(err);
  }*/
       }
               );
       </script>
       <script type='text/javascript'>
/*$(document).ready(function(){
//setInterval(function() {
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
var msg_data="<li>"+data.Message+"</li>"

$(msg_data).appendTo(".event-list");
});
}
else
{
$(".event-list").html("No Subscriptions");
}
}
});
$('#UpdateButton').click(function() 
{
// Previous Post
});
return false;
  // }else{ }
  //}, 5000); //5 seconds   
});
 */
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
    <ul class="event-list">
    </ul>
    </div>
</div>
</div>
        <!--<button onclick="cliente();">Create Cliente</button>-->
    </body>
</html>
