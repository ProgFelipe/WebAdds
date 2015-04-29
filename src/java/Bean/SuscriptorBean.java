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
public class SuscriptorBean {
      private String suscriptor, idcanal, fecha;
      public boolean valid;
				

      public String getSuscriptor() {
         return suscriptor;
	}

      public void setSuscriptor(String newsuscriptor) {
         suscriptor = newsuscriptor;
	}
	
			
      public String getCanal() {
         return idcanal;
			}

      public void setCanal(String newidcanal) {
         idcanal = newidcanal;
			}
      public String getFecha() {
         return fecha;
			}
      public void setFecha(String newfecha) {
         fecha = newfecha;
			}

				
      public boolean isValid() {
         return valid;
	}

      public void setValid(boolean newValid) {
         valid = newValid;
	}	    
}
