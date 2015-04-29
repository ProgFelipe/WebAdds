package Services;

import Bean.UserBean;
import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AdFuente")
public class AdFuente  extends HttpServlet implements ExceptionListener{

    public void doGet(HttpServletRequest request, HttpServletResponse response) 
			           throws ServletException, java.io.IOException {
        System.err.println("DO GET AdFuente");
            String channelName = request.getParameter("channelName");
            String Message = request.getParameter("myMessage");
            String urlMedia = request.getParameter("url");
            System.out.println("AdFuente channel: "+channelName+", Message: "+Message+", UrlMedia:"+urlMedia);
            //HttpSession s=request.getSession();
//                UserBean currentUser = (UserBean) (s.getAttribute("currentSessionUser"));
            //String userName = currentUser.getUsername().toString();
            String userName = "felipe";
            processProducer(channelName, Message, urlMedia, userName);
        }
    
	void processProducer(String topicName, String text, String url, String userName) {//Nombre del canal
		try {
			// Create a ConnectionFactory
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
					"tcp://localhost:61616");

			// Create a Connection
			Connection connection = connectionFactory.createConnection();
			connection.start();

			// Create a Session
			Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

			// Create the destination (Topic or Queue)
			//Destination destination = session.createQueue("MyQUEUE");
                        Destination destination = session.createTopic(topicName);

			// Create a MessageProducer from the Session to the Topic or Queue
			MessageProducer producer = session.createProducer(destination);

                        TextMessage message = session.createTextMessage(text);
				// Tell the producer to send the message
        		System.out.println("Mensaje Enviado: " + text);
			producer.send(message);			
			// Clean up
			session.close();
			connection.close();
		} catch (Exception e) {
			System.out.println("Caught: " + e);
			e.printStackTrace();
		}
	}

	public synchronized void onException(JMSException ex) {
		System.out.println("JMS Exception occured.  Shutting down client.");
	}

	/*public static void main(String[] args) throws Exception {
		AdFuente p = new AdFuente();
		System.out.println("Running Producer...");
                p.processProducer();
	}*/
        
}
