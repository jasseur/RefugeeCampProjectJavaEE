	package tn.esprit.RefugeeCampProject.RefugeeCampProject.presentation.mbeans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import tn.esprit.RefugeeCampProject.Entities.CampManagment.Camp;
import tn.esprit.RefugeeCampProject.Entities.CampManagment.Mission;
import tn.esprit.RefugeeCampProject.RefugeeCampProject.services.CampManagment.CampManagmentService;
import javax.faces.context.FacesContext;
@ManagedBean
@ViewScoped
public class CampManagmentBean  implements Serializable{
	

	private Camp camp = new Camp();
	private Camp newCamp = new Camp();
	private List<Camp> camps;
	
	// list camps map
	private MapModel allcampsModel= new DefaultMapModel();
	// details camp map
	private MapModel detailsModel= new DefaultMapModel();

	// info selected camp
	private Camp selectedCamp = new Camp();
	
	

	@EJB
	CampManagmentService cms;
	
	@ManagedProperty(value="#{loginBean}")
	LoginBean loginBean;
	
	@ManagedProperty(value="#{campDetailsBean}")
	CampDetailsBean campDetailsBean;
	

 
	@PostConstruct
	private void init(){
		camps= cms.getAllCamps();
		 String icon = "http://localhost:18080/RefugeeCampProject-web/ressources/images/login/logohome.png";
        
		for (Camp camp : camps) {
			   LatLng coord1 = new LatLng(camp.getLatitude(), camp.getLongitude());
			   Marker marker = new Marker(coord1, camp.getName(),camp,icon);
			//   marker.setDraggable(true);
		       allcampsModel.addOverlay(marker);
		}
		
		//selectedCamp= new Camp();
	}
	
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

	public CampDetailsBean getCampDetailsBean() {
		return campDetailsBean;
	}

	public void setCampDetailsBean(CampDetailsBean campDetailsBean) {
		this.campDetailsBean = campDetailsBean;
	}

	public MapModel getAllcampsModel() {
	

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
     newCamp = new Camp();
    }
	
    // select info 
    public void onMarkerSelect(OverlaySelectEvent event) {
    	// i added camp to markers 
    	campDetailsBean.setSelectedCamp(selectedCamp =(Camp)( (Marker) event.getOverlay()).getData());
    }


    

    

}
