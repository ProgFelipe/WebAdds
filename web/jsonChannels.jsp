<%-- 
    Document   : jsonChannels
    Created on : 26-abr-2015, 0:02:30
    Author     : Felipe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
        <script type='text/javascript'>
$(document).ready(function()
{
$.ajax
({
type: "GET",
//url: "GetJSON?username=felipe&place=allchannels",
url: "GetJSON?username=felipe&&channel=Ciencia",
dataType:"json",
success: function(data)
{   
if(data.length)
{
$.each(data, function(i,data)
{
    var msg_data="<li>"+data.idauthor+"\n"+data.message+"\n"+data.urlMedia+"</li>";
/*var msg_data="<li><time datetime="+data.fecha+"><span class='day'>4</span><span class='month'>Jul</span><span class='year'>2014</span>\n\
<span class='time'>ALL DAY</span></time>\n\
<img alt='Independence Day' src='"+data.url+"' /><div class='info'>\n\
<h2 class='title'>"+data.nombre+"</h2>\n\
<p class='desc'>"+data.descripcion+"</p></div><div class='social'><ul>\n\
<li class='facebook' style='width:33%;'><a href='#facebook'><span class='fa fa-facebook'></span></a></li>\n\
<li class='twitter' style='width:34%;'><a href='#twitter'><span class='fa fa-twitter'></span></a></li>\n\
<li class='google-plus' style='width:33%;'><a href='#google-plus'><span class='fa fa-google-plus'></span></a></li>\n\
</ul>\n\
</div>\n\
</li>";*/


$(msg_data).appendTo("#content");
});
}
else
{
$("#content").html("No Subscriptions");
}
}
});
$('#UpdateButton').click(function() 
{
// Previous Post
});
return false;
});
</script>
    </head>
    <body>
        <div id="content"></div>
    </body>
</html>
