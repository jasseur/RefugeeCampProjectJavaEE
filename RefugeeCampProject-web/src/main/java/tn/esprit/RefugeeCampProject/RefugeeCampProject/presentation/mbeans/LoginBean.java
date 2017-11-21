package tn.esprit.RefugeeCampProject.RefugeeCampProject.presentation.mbeans;

import javax.faces.bean.ManagedBean;
import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import tn.esprit.RefugeeCampProject.Entities.RegistrationManagment.Member;
import tn.esprit.RefugeeCampProject.RefugeeCampProject.services.RegistrationManagment.MemberManagmentService;
import tn.esprit.RefugeeCampProject.Types.Role;

@ManagedBean
@SessionScoped
public class LoginBean {
	private String login;
	private String password;
	private Member member;
	private boolean loggedIn; 
	private String message;
	@EJB
	MemberManagmentService mms;
	

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	
	
	public String  doLogin(){
		String 	nvigateTo ="/login?faces-redirect=true";
		member = mms.getMemberByLoginAndPasswod(login, password);
		if(member != null  && member.getRole() == Role.CampSettingUpManager ){
			nvigateTo = "/campManager/listCamps";
			loggedIn = true;
	}
		
		else if( member != null && member.getRole() == Role.MembershipManager){
				nvigateTo = "/membershipManager/listMembershipDemands";
				loggedIn = true;
		}else if( member != null && member.getRole() == Role.Member){
			nvigateTo = "/member/myProfile";
			loggedIn = true;
	}
		else{
			   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "check Login and password!!",""));
			    this.message="Check Login and password!!";
		}
	return nvigateTo;
		
	}

	public String  doLogout(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/login?faces-redirect=true";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
