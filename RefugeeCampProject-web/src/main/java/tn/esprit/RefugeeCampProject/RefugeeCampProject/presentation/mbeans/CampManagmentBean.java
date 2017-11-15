package tn.esprit.RefugeeCampProject.RefugeeCampProject.presentation.mbeans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import java.util.List;

import javax.ejb.EJB;

import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import tn.esprit.RefugeeCampProject.Entities.CampManagment.Camp;
import tn.esprit.RefugeeCampProject.RefugeeCampProject.services.CampManagment.CampManagmentService;
import javax.faces.context.FacesContext;
@ManagedBean
@ViewScoped
public class CampManagmentBean {
	

	private Camp camp = new Camp();
	private List<Camp> camps;
	
	private MapModel emptyModel;


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
	
	


	public MapModel getEmptyModel() {
		return emptyModel;
	}

	public void setEmptyModel(MapModel emptyModel) {
		this.emptyModel = emptyModel;
	}


	//add marker and camp
    public String addNewCamp() {
    	
        cms.addCamp(camp);
          
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Camp "+ camp.getName() +" Added", "Lat:" + camp.getLatitude() + ", Lng:" + camp.getLongitude()));
      return "listCamps";
    }
	
}
