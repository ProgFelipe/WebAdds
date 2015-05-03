<%-- 
    Document   : InChannel
    Created on : 27-abr-2015, 11:04:10
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
        <style type="text/css"><%@ include file="css/uicomments.css"%></style>
        <script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
        <script><%@include file="js/bootstrap.min.js" %></script>
        <%String channel = request.getParameter("channel");%>
        <%String subscribed = request.getParameter("subscribed");%>
        <title><%=channel%></title>
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
url: "GetJSON?username="+currentUser+"&&channel=<%=channel%>",
dataType:"json",
success: function(data)
{
if(data.length)
{
    $( ".event-list" ).empty();
$.each(data, function(i,data)
{
    var fecha = data.date;
    var dateParts = fecha.split(' ');
    dateParts = dateParts[0].split('-');
var msg_data="<li class='media'><div class='media-body'><div class='well well-lg'>\n\
<h4 class='media-heading text-uppercase reviews'>"+data.idauthor+"</h4>\n\
<ul class='media-date text-uppercase reviews list-inline'>\n\
    <li class='dd'>"+dateParts[2]+
    "</li><li class='mm'>"+dateParts[1]+"</li>\n\
    <li class='aaaa'>"+dateParts[0]+"</li></ul>\n\
<p class='media-comment'>"+data.message+"</p>\n\
<img style='width: 300px; height: auto;' src='"+data.urlMedia+"'/></div></div></li>";
$(msg_data).appendTo(".media-list");
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
    </head>
    <body>
        <div class="container-fluid" style="margin-top: -52px;">    
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
          <a class="navbar-brand" href="#">Channel <%=channel%></a>
        </div>
    
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="navbar-collapse-3">
          <ul class="nav navbar-nav navbar-right">
            <li><a href="manager.jsp?place=MyChannels">My Channels</a></li>
            <li><a href="manager.jsp?place=Suscriptions">Subscriptions</a></li>
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
            </form>
          </div>
        </div><!-- /.navbar-collapse -->
      </div><!-- /.container -->
    </nav><!-- /.navbar -->
</div><!-- /.container-fluid --> 
        
<!-- End Toolbar -->  
        <div class="container">
            <% if(subscribed.equalsIgnoreCase("Suscriptions") || subscribed.equalsIgnoreCase("MyChannels")){%>
            <div class="row">
                <a href="DeletSubscription?channel=<%=channel%>" style="width: 100%; margin-bottom: 20px;" >
                <div class="alert alert-danger"><strong>Delete </strong>subscription!</div>
                </a>
            </div>
            <div class="row">
                ${requestScope.pull}
            </div>
            <div class="row">
                <div class="alert alert-info"><a href="#" class="close" data-dismiss="alert">&times;</a><strong>You are </strong>Subscribed!</div>
            </div><%}else{%>
            <div class="row">
                <a href="Subscriptor?channel=<%=channel%>" style="width: 100%; margin-bottom: 20px;" class="btn btn-info" >Follow <%=channel%></a>
            </div><%}%>
  <div class="row">
        <div class="comment-tabs">
            <ul class="nav nav-tabs" role="tablist">
                <li class="active"><a href="#comments-logout" role="tab" data-toggle="tab"><h4 class="reviews text-capitalize">Comments</h4></a></li>
                <% if(subscribed.equalsIgnoreCase("Suscriptions") || subscribed.equalsIgnoreCase("MyChannels")){%><li><a href="#add-comment" role="tab" data-toggle="tab"><h4 class="reviews text-capitalize">Add comment</h4></a></li><%}%>
                <li><a href="#account-settings" role="tab" data-toggle="tab"><h4 class="reviews text-capitalize">Account settings</h4></a></li>
            </ul>            
            <div class="tab-content">
                <div class="tab-pane active" id="comments-logout">      
                                <div class="row">
                <ul class="media-list">
                </ul>
            </div>
                </div>
                <% if(subscribed.equalsIgnoreCase("Suscriptions")  || subscribed.equalsIgnoreCase("MyChannels")){%>
                <div class="tab-pane" id="add-comment">
                    <form action="addMessage" class="form-horizontal" id="commentForm" role="form"> 
                        <div class="form-group">
                            <label for="Message" class="col-sm-2 control-label">Comment</label>
                            <div class="col-sm-10">
                              <textarea class="form-control" name="Message" id="addComment" rows="5"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="channelName" class="col-sm-2 control-label">Channel</label>
                            <div class="col-sm-10">
                            <input type="text" readonly name="channelName" id="channelName" tabindex="1" 
                                   class="form-control" placeholder="channelName" value="<%=channel%>">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="url" class="col-sm-2 control-label">Upload media</label>
                            <div class="col-sm-10">                    
                                <div class="input-group">
                                  <div class="input-group-addon">http://</div>
                                  <input type="text" class="form-control" name="url" id="uploadMedia">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">                    
                                <button class="btn btn-success btn-circle text-uppercase" type="submit" id="submitComment"><span class="glyphicon glyphicon-send"></span> Summit comment</button>
                            </div>
                        </div>            
                    </form>                        
                </div>
                            <%}%>
                <div class="tab-pane" id="account-settings">
                    <form action="#" method="post" class="form-horizontal" id="accountSetForm" role="form">
                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label">Name</label>
                            <div class="col-sm-10">
                              <input type="text" class="form-control" name="name" id="name" placeholder="Vilma palma">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="email" class="col-sm-2 control-label">Email</label>
                            <div class="col-sm-10">
                              <input type="email" class="form-control" name="email" id="email" placeholder="vilma@mail.com">
                            </div>
                        </div>  
                        <div class="form-group">
                            <label for="newPassword" class="col-sm-2 control-label">New password</label>
                            <div class="col-sm-10">
                              <input type="password" class="form-control" name="newPassword" id="newPassword">
                            </div>
                        </div> 
                        <div class="form-group">
                            <label for="confirmPassword" class="col-sm-2 control-label">Confirm password</label>
                            <div class="col-sm-10">
                              <input type="password" class="form-control" name="confirmPassword" id="confirmPassword">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">                    
                                <button class="btn btn-primary btn-circle text-uppercase" type="submit" id="submit">Save changes</button>
                            </div>
                        </div>            
                    </form>
                </div>
            </div>
        </div>
  </div>
  <div class="row">
    <div class="col-sm-10 col-sm-offset-1" id="login">
        <div class="page-header">
            <h3 class="reviews">Leave your comment</h3>
            <div class="logout">
                <button class="btn btn-default btn-circle text-uppercase" type="button" onclick="$('#login').hide(); $('#logout').show()">
                    <span class="glyphicon glyphicon-off"></span> Login                    
                </button>                
            </div>
        </div>
        <div class="comment-tabs">
            <ul class="nav nav-tabs" role="tablist">
                <li class="active"><a href="#comments-login" role="tab" data-toggle="tab"><h4 class="reviews text-capitalize">Comments</h4></a></li>
                <li><a href="#add-comment-disabled" role="tab" data-toggle="tab"><h4 class="reviews text-capitalize">Add comment</h4></a></li>
                <li><a href="#new-account" role="tab" data-toggle="tab"><h4 class="reviews text-capitalize">Create an account</h4></a></li>
            </ul>            
            <div class="tab-content">
                          <div class="tab-pane" id="add-comment-disabled">
                    <form action="AdFuente" class="form-horizontal" id="commentForm" role="form"> 
                        <div class="form-group">
                            <label for="Message" class="col-sm-2 control-label">Comment</label>
                            <div class="col-sm-10">
                              <textarea class="form-control" name="Message" id="addComment" rows="5" ></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="url" class="col-sm-2 control-label">Upload media</label>
                            <div class="col-sm-10">                    
                                <div class="input-group">
                                  <div class="input-group-addon">http://</div>
                                  <input type="text" class="form-control" name="url" id="uploadMedia" disabled>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">                    
                                <button class="btn btn-success btn-circle text-uppercase disabled" type="submit" id="submitComment"><span class="glyphicon glyphicon-send"></span> Summit comment</button>
                            </div>
                        </div>            
                    </form>
                </div>
                
                <div class="tab-pane" id="new-account">
                    <form action="#" method="post" class="form-horizontal" id="commentForm" role="form">
                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label">Name</label>
                            <div class="col-sm-10">
                              <input type="text" class="form-control" name="name" id="name">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="email" class="col-sm-2 control-label">Email</label>
                            <div class="col-sm-10">
                              <input type="email" class="form-control" name="email" id="email" required>
                            </div>
                        </div>  
                        <div class="form-group">
                            <label for="password" class="col-sm-2 control-label">Password</label>
                            <div class="col-sm-10">
                              <input type="password" class="form-control" name="password" id="password">
                            </div>
                        </div>                         
                        <div class="form-group">
                            <div class="checkbox">                
                                <label for="agreeTerms" class="col-sm-offset-2 col-sm-10">
                                    <input type="checkbox" name="agreeTerms" id="agreeTerms"> I agree all <a href="#">Terms & Conditions</a>
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">                    
                                <button class="btn btn-primary btn-circle text-uppercase" type="submit" id="submit">Create an account</button>
                            </div>
                        </div>            
                    </form>
                </div>
            </div>
        </div>
    </div>
  </div>
</div>
    </body>
</html>