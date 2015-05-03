/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

/**
 *
 * @author Felipe
 */
public class MessageBean {
    private String channel,message, autor, urlMedia, fecha, id;
    
    public void setChannel(String channel){
        this.channel = channel;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public void setId(String id){
        this.id = id;
    }
    public void setAutor(String autor){
        this.autor = autor;
    }
    public void setUrl(String urlMedia){
        this.urlMedia = urlMedia;
    }
    public void setFecha(String fecha){
        this.fecha = fecha;
    }
    public String getChannel(){
        return this.channel;
    }
    public String getMessage(){
        return this.message;
    }
    public String getAutor(){
        return this.autor;
    }
    public String getUrl(){
        return this.urlMedia;
    }
    public String getFecha(){
        return this.fecha;
    }
    public String getId(){
        return this.id;
    }
}
