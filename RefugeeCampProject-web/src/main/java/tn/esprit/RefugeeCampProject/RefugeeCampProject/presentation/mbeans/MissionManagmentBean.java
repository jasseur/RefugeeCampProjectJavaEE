	package tn.esprit.RefugeeCampProject.RefugeeCampProject.presentation.mbeans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import tn.esprit.RefugeeCampProject.Entities.CampManagment.Camp;
import tn.esprit.RefugeeCampProject.Entities.CampManagment.Mission;
import tn.esprit.RefugeeCampProject.Entities.RegistrationManagment.Member;
import tn.esprit.RefugeeCampProject.RefugeeCampProject.services.CampManagment.CampManagmentService;
import tn.esprit.RefugeeCampProject.RefugeeCampProject.services.MissionManagment.MissionManagmentService;
import tn.esprit.RefugeeCampProject.Types.Gender;
import tn.esprit.RefugeeCampProject.Types.Role;

import javax.faces.context.FacesContext;
@ManagedBean
@SessionScoped
public class MissionManagmentBean  implements Serializable{
	

	private Mission mission;
	private Mission newMission;
	private List<Mission> missions;
	
	private String login;

	private boolean staffHasAccount;
	private String staffHasAccountString;
	
	
	@EJB
	MissionManagmentService mms;

	
	
	//getters and setters
	public Mission getMission() {
		return mission;
	}

	public void setMission(Mission mission) {
		this.mission = mission;
	}

	public Mission getNewMission() {
		return newMission;
	}

	public void setNewMission(Mission newMission) {
		this.newMission = newMission;
	}

	public List<Mission> getMissions() {
		return missions;
	}

	public void setMissions(List<Mission> missions) {
		this.missions = missions;
	}
	
	public boolean isStaffHasAccount() {
		return staffHasAccount;
	}

	public void setStaffHasAccount(boolean staffHasAccount) {
		this.staffHasAccount = staffHasAccount;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getStaffHasAccountString() {
		return staffHasAccountString;
	}

	public void setStaffHasAccountString(String staffHasAccountString) {
		this.staffHasAccountString = staffHasAccountString;
	}

	//end getters and setters
	@PostConstruct
	private void init(){
		  mission = new Mission();
		  newMission = new Mission();
		  
		  newMission.setCamp(new Camp());
		  newMission.setMember(new Member());
		  
		  mission.setCamp(new Camp());
		  mission.setMember(new Member());
	}
	// web REST check if Satff exist GET method  http://localhost:62754/api/Staffs/{login}
	public void checkStaff() throws Exception
	{
		this.staffHasAccount = mms.checkStaffAccount(this.login);
		this.staffHasAccountString=mms.checkStaffAccountStringReturn(this.login);
	}
	
	
	public void addSatff(){
		Member member = new Member();
		member.setFirstName("jassertest");
		member.setLastName("jassertest");
		member.setBirthDate(new Date());
		member.setGender(Gender.male);
		member.setEmail("jassertest");
		member.setRole(Role.CampManager);
		member.setLogin("jassertest");
		member.setPassword("jassertest");
		mission.setMember(member);
	this.staffHasAccountString=	mms.addNewStaff(mission);
		
	}
}
