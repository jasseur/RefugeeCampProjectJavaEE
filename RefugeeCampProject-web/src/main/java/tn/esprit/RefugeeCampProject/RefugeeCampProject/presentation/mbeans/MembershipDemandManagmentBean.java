package tn.esprit.RefugeeCampProject.RefugeeCampProject.presentation.mbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;


import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;

import tn.esprit.RefugeeCampProject.Entities.RegistrationManagment.Member;
import tn.esprit.RefugeeCampProject.Entities.RegistrationManagment.MembershipDemand;
import tn.esprit.RefugeeCampProject.RefugeeCampProject.services.MembershipDemandManagment.MembershipDemandManagmentService;
import tn.esprit.RefugeeCampProject.RefugeeCampProject.services.RegistrationManagment.MemberManagmentService;

@ManagedBean
@SessionScoped
public class MembershipDemandManagmentBean {
	
	
	private int idMembershipDemand;
	private MembershipDemand membershipDemand = new MembershipDemand();
	private List<MembershipDemand> membershipDemands;
	private List<MembershipDemand> membershipDemandsFiltred;
	private String nameFilter;
	private Part file;
	@EJB
	MembershipDemandManagmentService mdms;
	@EJB
	MemberManagmentService mmb;
	
	@ManagedProperty(value="#{loginBean}")
	LoginBean loginBean;
	
	
	
	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

	public int getIdMembershipDemand() {
		return idMembershipDemand;
	}

	public void setIdMembershipDemand(int idMembershipDemand) {
		this.idMembershipDemand = idMembershipDemand;
	}

	public MembershipDemand getMembershipDemand() {
		return membershipDemand;
	}

	public void setMembershipDemand(MembershipDemand membershipDemand) {
		this.membershipDemand = membershipDemand;
	}
	
	
	

	public String getNameFilter() {
		return nameFilter;
	}

	public void setNameFilter(String nameFilter) {
		this.nameFilter = nameFilter;
	}

	public void setMembershipDemandsFiltred(List<MembershipDemand> membershipDemandsFiltred) {
		this.membershipDemandsFiltred = membershipDemandsFiltred;
	}

	//membership demands viewed by manager only
	public List<MembershipDemand> getMembershipDemands() {
		this .membershipDemands = mdms.getAllNotAcceptedMembershipDemands();
		return membershipDemands;
	}
	
	//membership demands by mamber viewed by member
	public List<MembershipDemand> getMembershipDemandsByMemberId() {
		
		return mdms.getAllMembershipDemandsByMemberId(loginBean.getMember());
	}

	public void setMembershipDemands(List<MembershipDemand> membershipDemands) {
		this.membershipDemands = membershipDemands;
	}
	
	//membership demand : all members can use it
	public String addNewMember(){
		membershipDemand.setRegistrationDate(new Date());
		membershipDemand.setMember(loginBean.getMember());
		mdms.addMembershipDemand(membershipDemand);
		  try (InputStream input = file.getInputStream()) {
		        Files.copy(input, new File("P:/Esprit 4BI/PI Sprint Two/RefugeeCampProject/RefugeeCampProject-web/src/main/webapp/profilepics",membershipDemand.getLogin()+ ".jpg").toPath());
		    }
		    catch (IOException e) {
		        // Show faces message?
		    } 
	return "listSendedMembershipDemands?faces-redirect = true";
	}

	//details membership demand viewed by manager only
	public String detailsMembership(MembershipDemand demand){
		this.membershipDemand=demand;
		return "membershipDemandDetails?faces-redirect = true";		
	}
	
	//details membership demand viewed by manager only
		public String detailsSendedMembership(MembershipDemand demand){
			this.membershipDemand=demand;
			return "membershipSendedDemandDetails?faces-redirect = true";		
		}
	//accept demand
	public String acceptDemand (){
		this.membershipDemand.setAccepted(true);
		mdms.updateMembershipDemand(membershipDemand);
		Member member = new Member(membershipDemand.getLogin(),
				membershipDemand.getEmail(),
				"changeme",
				membershipDemand.getFirstName(),
				membershipDemand.getLastName(),
				membershipDemand.getBirthDate(),
				new Date(),
				membershipDemand.getGender()
				);
		mmb.addMember(member);
		
		return "listMembershipDemands?faces-redirect = true";
		
	}
	
// deny demand
	public String denyDemand (){
		this.membershipDemand.setAccepted(false);
		
		return "listMembershipDemands";	
	}
	

	
	public List<MembershipDemand> getMembershipDemandsFiltred() {
		this .membershipDemands = mdms.getAllMembershipDemandsByMembeNameLike(this.nameFilter);
		this.membershipDemandsFiltred = membershipDemands;
		return membershipDemands;
	}
	
}
