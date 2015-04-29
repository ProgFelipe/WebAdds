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
public class CanalBean {
    private String nombre, autor, descripcion, fecha, id, url;
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setId(String id){
        this.id = id;
    }
    public void setAutor(String autor){
        this.autor = autor;
    }
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    public void setFecha(String fecha){
        this.fecha = fecha;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    public String getAutor(){
        return this.autor;
    }
    public String getDescripcion(){
        return this.descripcion;
    }
    public String getFecha(){
        return this.fecha;
    }
    public String getId(){
        return this.id;
    }
    public void setImageUrl(String url){
        this.url = url;
    }
    public String getImg_Url(){
        return this.url;
    }
}
