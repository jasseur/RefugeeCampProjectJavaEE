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
import tn.esprit.RefugeeCampProject.RefugeeCampProject.services.MissionManagment.MissionManagmentService;

import javax.faces.context.FacesContext;
@ManagedBean
@SessionScoped
public class CampDetailsBean  implements Serializable{
	

	private Camp camp = new Camp();
	private Camp newCamp = new Camp();
	private List<Camp> camps;
	

	// details camp map
	private MapModel detailsModel= new DefaultMapModel();

	// info selected camp
	private Camp selectedCamp = new Camp();
	
	private List<Mission> missions;

	@EJB
	CampManagmentService cms;
	@EJB
	MissionManagmentService mms;
	
	@ManagedProperty(value="#{loginBean}")
	LoginBean loginBean;
	
	@ManagedProperty(value="#{missionManagmentBean}")
	MissionManagmentBean missionManagmentBean;


	
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
	
	

    public MissionManagmentBean getMissionManagmentBean() {
		return missionManagmentBean;
	}

	public void setMissionManagmentBean(MissionManagmentBean missionManagmentBean) {
		this.missionManagmentBean = missionManagmentBean;
	}
	
	
	public void setMissions(List<Mission> missions) {
		this.missions = missions;
	}

	@PostConstruct
	public void init()
	{
		missionManagmentBean.setSelectedCamp(selectedCamp);
	}

	// details camp view
    public String detailsCamp(){
    	  this.camp = selectedCamp;
    	  String icon = "http://localhost:18080/RefugeeCampProject-web/ressources/images/login/logohome.png";
          LatLng coord1 = new LatLng(selectedCamp.getLatitude(), selectedCamp.getLongitude());
		   Marker marker = new Marker(coord1, camp.getName(),camp,icon);
		   marker.setDraggable(true);
		   detailsModel= new DefaultMapModel();
         detailsModel.addOverlay(marker);
         return "detailsCamp?faces-redirect=true";
    }
    
    // darg to update
    public void updateCampPosition(MarkerDragEvent event)
    {
    	
    	Marker  marker = event.getMarker();
    	selectedCamp.setLatitude(marker.getLatlng().getLat() );
    	selectedCamp.setLongitude(marker.getLatlng().getLng());
    	cms.updateCamp(selectedCamp);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Camp position updated", ""));
        
    }
    
   public String updateCamp(){
	   cms.updateCamp(selectedCamp);
	   return "detailsCamp?faces-redirect=true";
    }
   public String updateCampView(){
	   this.camp = selectedCamp;
	   return "updateCamp";
    }
   
   public String assignMissionRedirect(){
   	missionManagmentBean.setSelectedCamp(selectedCamp);
   	return "assignMission?faces-redirect=true";
   }
   
   public List<Mission> getMissions() {
		this.missions=mms.getAllMissions(selectedCamp);
		return missions;
	}
   
   public String deleteCamp(){
		  
		  cms.deleteCamp(selectedCamp);
		  return "listCamps?faces-redirect=true";
	  }
   
   public void updateMission(){
	   
   }
}
