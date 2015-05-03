<%-- 
    Document   : manager
    Created on : 25-abr-2015, 18:41:48
    Author     : Felipe
--%>

<%@page import="Bean.UserBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="http://getbootstrap.com/favicon.ico">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <style type="text/css"><%@ include file="css/bootstrap.css"%></style>
        <style type="text/css"><%@ include file="css/navbar.css"%></style>
        <style type="text/css"><%@ include file="css/manager.css"%></style>
        <style type="text/css"><%@ include file="css/androidbutton.css"%></style>
        <script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
        <script><%@include file="js/bootstrap.min.js" %></script>
                <script type='text/javascript'>
$(document).ready(function()
{
     <% UserBean currentUser = (UserBean) (session.getAttribute("currentSessionUser"));%>
    var currentUser = "<%out.print(currentUser.getUsername());%>";
    <%String place = request.getParameter("place");%>
   if(currentUser != null){
$.ajax
({
type: "GET",
url: "GetJSON?username="+currentUser+"&&place=<%=place%>",
dataType:"json",
success: function(data)
{
if(data.length)
{
    $( ".event-list" ).empty();
$.each(data, function(i,data)
{
    var fecha = data.fecha;
    var dateParts = fecha.split(' ');
    dateParts = dateParts[0].split('-');
var msg_data="<li class=\"hvr-bounce-in\" onclick=\"location.href='InChannel.jsp?channel="+data.nombre+"&subscribed=<%=place%>'\" id='"+data.id+"'><time datetime="+dateParts[0]+"><span class='day'>"+dateParts[2]+"</span><span class='month'>"+dateParts[1]+"</span><span class='year'>"+dateParts[0]+"</span>\n\
<span class='time'>ALL DAY</span></time>\n\
<img alt='Independence Day' src='"+data.url+"' /><div class='info'>\n\
<h2 class='title'>"+data.nombre+"</h2>\n\
<p class='desc'>"+data.descripcion+"</p><b style='padding-left: 10px;'>Author: "+data.autor+"</b></div><div class='social'><ul>\n\
<li class='facebook' style='width:33%;'><a href='#facebook'><span class='fa fa-facebook'></span></a></li>\n\
<li class='twitter' style='width:34%;'><a href='#twitter'><span class='fa fa-twitter'></span></a></li>\n\
<li class='google-plus' style='width:33%;'><a href='#google-plus'><span class='fa fa-google-plus'></span></a></li>\n\
</ul>\n\
</div>\n\
</li>";
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
   }else{ }
});
</script>
        <title>My Channels</title>
    </head>
    <body>
<div class="container-fluid">    
    <!-- Second navbar for search -->
    <nav class="navbar navbar-inverse">
      <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse-3">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <%if(place.equalsIgnoreCase("MyChannels")){%>
          <a class="navbar-brand" href="#manager.jsp?place=MyChannels">My Channels</a>
          <%}else if(place.equalsIgnoreCase("Suscriptions")){%>
          <a class="navbar-brand" href="#manager.jsp?place=Suscriptions">Suscriptions</a>
          <%}else if(place.equalsIgnoreCase("allchannels")){%>
          <a class="navbar-brand" href="#manager.jsp?place=Suscriptions">All Channels</a>
          <%}%>
        </div>
    
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="navbar-collapse-3">
          <ul class="nav navbar-nav navbar-right">
            <%if(place.equalsIgnoreCase("MyChannels")){%>
            <li><a href="manager.jsp?place=Suscriptions">Subscriptions</a></li>
            <%}else{%>
            <li><a href="manager.jsp?place=MyChannels">My Channels</a></li>
            <%}%>
            
            
            <li><a href="#">About</a></li>
            <li><a href="manager.jsp?place=allchannels">All Channels</a></li>
            <li><a href="#">News</a></li>
            <li><a href="#">Contact</a></li>
            <li><a href="LogOut">Log Out</a></li>
            <li>
              <a class="btn btn-default btn-outline btn-circle collapsed"  data-toggle="collapse" href="#nav-collapse3" aria-expanded="false" aria-controls="nav-collapse3">Search</a>
            </li>
          </ul>
          <div class="collapse nav navbar-nav nav-collapse slide-down" id="nav-collapse3">
            <form class="navbar-form navbar-right" role="search">
              <div class="form-group">
                <input type="text" class="form-control" placeholder="Search" />
              </div>
              <button type="submit" class="btn btn-danger"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
            </form>
          </div>
        </div><!-- /.navbar-collapse -->
      </div><!-- /.container -->
    </nav><!-- /.navbar -->
</div><!-- /.container-fluid --> 
        
<!-- End Toolbar -->        
            <div class="container">
                <div class="row">
                    ${requestScope.pull}
                </div>
		<div class="row">
			<div class="[ col-xs-12 col-sm-offset-2 col-sm-8 ]">
				<ul class="event-list">
					
				</ul>
			</div>
		</div>
	</div>
<%if(place.equalsIgnoreCase("allchannels") || place.equalsIgnoreCase("Suscriptions")){%><%}else{%>
<div class="container" id="channelCreator" style="display : none;">
    <div class="row">
        <div class="[ col-xs-12 col-sm-offset-2 col-sm-8 ]" style="background-color: white; border-radius: 20px;">
        <form id="login-form" action="ChannelCreator" role="form" style="padding-top: 10px;">
                <div class="form-group">
                    <label for="topicName">Name of Channel</label>
                    <input type="text" name="channelName" id="channelName" tabindex="1" class="form-control" placeholder="Topic" value="">
		</div>
		<div class="form-group">
                    <label for="description">What is Channel about?</label>
                    <input type="text" name="description" id="description"  tabindex="2" class="form-control" placeholder="Description">
		</div>
		<div class="form-group">
                    <label for="imgChannel">Image of Channel</label>
                    <input type="text" class="form-control" name="imgChannel" id="imgChannel" placeholder="http://Url">    
                </div>
		<div class="form-group">
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3">
                            <input style="background-color: black; color: white;" type="submit" name="create-submit" id="login-submit" tabindex="4" class="form-control btn btn-login" value="Create Channel">
			</div>
                    </div>
		</div>
        </form>
            </div>
        
        </div>
    </div>
<%}%>
<!--Like android L-->
<link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css'>
<div class="container">
	<div class="row">
		        <div id="inbox">
          <div class="fab btn-group show-on-hover dropup">
              <div data-toggle="tooltip" data-placement="left" title="Compose" style="margin-left: 42px;">
          <button type="button" class="btn btn-danger btn-io dropdown-toggle" data-toggle="dropdown">
            <span class="fa-stack fa-2x">
                <i class="fa fa-circle fa-stack-2x fab-backdrop"></i>
                <i class="fa fa-plus fa-stack-1x fa-inverse fab-primary"></i>
                <i class="fa fa-pencil fa-stack-1x fa-inverse fab-secondary"></i>
            </span>
          </button></div>
          <ul class="dropdown-menu dropdown-menu-right" role="menu">
            <li><a onclick="createChannel();" data-toggle="tooltip" data-placement="left" title="Create"><i class="fa fa-coffee"></i></a></li>
            <!--<li><a href="#" data-toggle="tooltip" data-placement="left" title="LiveChat"><i class="fa fa-comments-o"></i></a></li>
            <li><a href="#" data-toggle="tooltip" data-placement="left" title="Up"><i class="fa fa-hand-o-up"></i></a></li>
            <li><a href="#" data-toggle="tooltip" data-placement="left" title="Invites"><i class="fa fa-ticket"></i></a></li>-->
          </ul>
        </div>
        </div>
	</div>
    <div class="row">
        ${requestScope.message}
    </div>
</div>
        <script>
            $('.fab').hover(function () {
    $(this).toggleClass('active');
});
$(function () {
  $('[data-toggle="tooltip"]').tooltip()
})
        </script>
        <script type='text/javascript'>
            function createChannel(){
                if(document.getElementById("channelCreator").style.display == 'block'){
                    document.getElementById("channelCreator").style.display = 'none';
                }else{
                document.getElementById("channelCreator").style.display = 'block';
                }
            }
        </script>
    </body>
</html>
