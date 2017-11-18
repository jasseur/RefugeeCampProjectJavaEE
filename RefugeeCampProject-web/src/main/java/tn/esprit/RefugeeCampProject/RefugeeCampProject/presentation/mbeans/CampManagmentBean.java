	package tn.esprit.RefugeeCampProject.RefugeeCampProject.presentation.mbeans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;

import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import tn.esprit.RefugeeCampProject.Entities.CampManagment.Camp;
import tn.esprit.RefugeeCampProject.RefugeeCampProject.services.CampManagment.CampManagmentService;
import javax.faces.context.FacesContext;
@ManagedBean
@SessionScoped
public class CampManagmentBean  implements Serializable{
	

	private Camp camp = new Camp();
	private Camp newCamp = new Camp();
	private List<Camp> camps;
	
	// list camps map
	private MapModel allcampsModel= new DefaultMapModel();
	// add camp map
	private MapModel detailsModel= new DefaultMapModel();

	// info selected camp
	private Camp selectedCamp;
	
	

	@EJB
	CampManagmentService cms;
	
	@ManagedProperty(value="#{loginBean}")
	LoginBean loginBean;

	
	//getters and setters
	public Camp getCamp() {
		return camp;
	}

	public void setCamp(Camp camp) {
		this.camp = camp;
	}

	public List<Camp> getCamps() {
		
		return cms.getAllCamps();
	}

	public void setCamps(List<Camp> camps) {
		this.camps = camps;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}
	
	// get all camps to map
	public MapModel getAllcampsModel() {
		camps= cms.getAllCamps();
		 String icon = "http://localhost:18080/RefugeeCampProject-web/ressources/images/login/logohome.png";
         
		for (Camp camp : camps) {
			   LatLng coord1 = new LatLng(camp.getLatitude(), camp.getLongitude());
		       allcampsModel.addOverlay(new Marker(coord1, camp.getName(),camp,icon));
		}

		return allcampsModel;
	}
	
	public void setAllcampsModel(MapModel allcampsModel) {
		this.allcampsModel = allcampsModel;
	}
	
	

	public MapModel getDetailsModel() {
		return detailsModel;
	}

	public void setDetailsModel(MapModel detailsModel) {
		this.detailsModel = detailsModel;
	}

	public Camp getSelectedCamp() {
		return selectedCamp;
	}

	public void setSelectedCamp(Camp selectedCamp) {
		this.selectedCamp = selectedCamp;
	}
	

	public Camp getNewCamp() {
		return newCamp;
	}

	public void setNewCamp(Camp newCamp) {
		this.newCamp = newCamp;
	}

	//add marker and camp
    public void addNewCamp() {
    	
        cms.addCamp(newCamp);  
        String icon = "http://localhost:18080/RefugeeCampProject-web/ressources/images/login/logohome.png";
        LatLng coord1 = new LatLng(newCamp.getLatitude(), newCamp.getLongitude());
        allcampsModel.addOverlay(new Marker(coord1, newCamp.getName(),newCamp,icon));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Camp "+ newCamp.getName() +" Added", "Lat:" + newCamp.getLatitude() + ", Lng:" + newCamp.getLongitude()));
     
    }
	
    // select info 
    public void onMarkerSelect(OverlaySelectEvent event) {
    	// i added camp to markers 
        selectedCamp =(Camp)( (Marker) event.getOverlay()).getData();
    }
  
    
    public String detailsCamp(){
    	  this.camp = selectedCamp;
    	  String icon = "http://localhost:18080/RefugeeCampProject-web/ressources/images/login/logohome.png";
          LatLng coord1 = new LatLng(selectedCamp.getLatitude(), selectedCamp.getLongitude());
         detailsModel.addOverlay(new Marker(coord1, selectedCamp.getName(),selectedCamp,icon));
         return "detailsCamp?faces-redirect=true";
    }
}
