package Services;


import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet("/AdCliente")
public class AdCliente  extends HttpServlet implements ExceptionListener {
    ArrayList Mensajes;
  //Pide los mensajes
 public void doGet(HttpServletRequest request, HttpServletResponse response) 
			           throws ServletException, java.io.IOException {
          String username = request.getParameter("username");
          String channelName = request.getParameter("channelName");
          //System.err.println("DO GET AdCliente UserName "+username);            
          //System.err.println("DO GET AdCliente CH "+channelName);      
            
            JSONArray jArray = new JSONArray();
            JSONObject obj = new JSONObject();
            for(int c = 0; c < Mensajes.size(); c++){
                obj.put("Message", Mensajes.get(c));
            }
            obj.put("Message", "The servlet has received a GET. This is the reply.");
            jArray.add(0,obj);
            String messages = jArray.toJSONString();
            System.out.println(messages);
            //String messages = "<p>The servlet has received a GET. This is the reply.</p>";
            //response.setHeader(messages, messages);
            PrintWriter out = response.getWriter();
            out.print(messages);
            out.close();
            
            //out.print("<ajax-response> respuesta </ajax-response>");
            //out.close();                  
 }
//Crea el Cliente o consumidor
public void doPost(HttpServletRequest request, HttpServletResponse response) 
			           throws ServletException, java.io.IOException {
    System.err.println("DO POST AdCliente");
            String channelName = request.getParameter("channelName");
            String username = request.getParameter("username");
            AdCliente c = new AdCliente();
            Mensajes = new ArrayList();
            System.out.println("Do Post "+username+", "+channelName);
            c.processConsumer(channelName, username);    
        }
    
    void processConsumer(String Canal, String username) {
        System.err.println("CONSUMIDOR");
        try {
            // Create a ConnectionFactory
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                    "tcp://localhost:61616");

            // Create a Connection
            Connection connection = connectionFactory.createConnection();
            //connection.start();
            connection.setClientID(username);
            connection.setExceptionListener(this);

            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// Create the destination (Topic or Queue)
            //Destination destination = session.createQueue("MyQUEUE");
            //Destination destination = session.createTopic("MyTOPIC");
            Destination destination = session.createTopic(Canal);
            // Create a MessageConsumer from the Session to the Topic or Queue
            //MessageConsumer consumer = session.createConsumer(destination);
            MessageConsumer consumer = session.createDurableSubscriber((Topic) destination, username);
            //consumer.setMessageListener(listener);
            System.out.println("Running Consumer...");
            connection.start();

	    // Wait for a message
            Message message = consumer.receive(1000);
            
             while (message != null) {
             Thread.sleep(5000);

             
             if (message instanceof TextMessage) {
             TextMessage textMessage = (TextMessage) message;
             String text = textMessage.getText();
             System.out.println("Received: " + text);
             Mensajes.add(text);
             
             } else {
             System.out.println("Received: " + message);
             Mensajes.add(message);
             }
             message = consumer.receiveNoWait();
             }
            
            //System.out.println("{\"Messages\":"+messages+"}");
            
             //consumer.close();
             //session.close();
             //connection.close();
                         
        } catch (Exception e) {
            System.out.println("Caught: " + e);
            e.printStackTrace();
        }
    }

    /*MessageListener listener = new MessageListener() {
        public void onMessage(Message msg) {
            if (msg instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) msg;
                String text = null;
                try {
                    text = textMessage.getText();
                } catch (JMSException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("Recibido: " + text);
                
            } else {
                System.out.println("Recibido: " + msg);
            }
        }

    };*/

    public synchronized void onException(JMSException ex) {
        System.out.println("JMS Exception occured.  Shutting down client.");
    }

    /*public static void main(String[] args) throws Exception {
        AdCliente c = new AdCliente();
        System.out.println("Running Consumer...");
        c.processConsumer();
    }*/

}
