package tn.esprit.RefugeeCampProject.RefugeeCampProject.presentation.mbeans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.ejb.EJB;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import tn.esprit.RefugeeCampProject.Entities.RegistrationManagment.Member;
import tn.esprit.RefugeeCampProject.RefugeeCampProject.services.RegistrationManagment.MemberManagmentService;
import tn.esprit.RefugeeCampProject.Types.Role;

@ManagedBean
@SessionScoped
public class MemberManagmentBean {
	
	private int idMemberToUpdate;
	private Member member = new Member();
	private List<Member> members;
	@EJB
	MemberManagmentService mms;
	private Part  file;
	
	@ManagedProperty(value="#{loginBean}")
	LoginBean loginBean;
	
	//check attributes
	private String loginCheckMessage;
	private String emailCheckMessage;
	
	
	
	public LoginBean getLoginBean() {
		return loginBean;
	}
	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}
	public int getIdMemberToUpdate() {
		return idMemberToUpdate;
	}
	public void setIdMemberToUpdate(int idMemberToUpdate) {
		this.idMemberToUpdate = idMemberToUpdate;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public List<Member> getMembers() {
		return mms.getAllMembers();
	}
	public void setMembers(List<Member> members) {
		this.members = members;
	}
	
	public void detailsMember(Member member) {
		this.member = member;
	}

	
	public String getLoginCheckMessage() {
		return loginCheckMessage;
	}
	public void setLoginCheckMessage(String loginCheckMessage) {
		this.loginCheckMessage = loginCheckMessage;
	}

	
	//check login used or not
	
	public Part getFile() {
		return file;
	}
	public void setFile(Part file) {
		this.file = file;
	}
	public String getEmailCheckMessage() {
		return emailCheckMessage;
	}
	public void setEmailCheckMessage(String emailCheckMessage) {
		this.emailCheckMessage = emailCheckMessage;
	}
	public void checkLogin(String login){
		if(mms.checkLogin(login))
			loginCheckMessage= "*Login has been used";
		else loginCheckMessage= " ";
	}
	
	// check mail used or not
	public void checkEmail(String email){
		if(mms.checkEmail(email))
			emailCheckMessage= "*Email has been used";
		else emailCheckMessage= " ";
	}
	
	//register Visitor view submit
	public String addNewVisitor()
	{
		member.setRole(Role.Visitor);
		mms.addMember(member);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome "+member.getLogin() +" To", "ICIR Family"));

		return "login?faces-redirect=true";
	}
	
	
	// submit update
	public String editMember(){
		this.member=loginBean.getMember();
		return "myProfileEdit";
	}
	//redirect to update view
	public String updateMember(){
		
		mms.updateMember(member);
		loginBean.setMember(member);
		return "myProfile";
	}
	
	
	public void save()
	{
		String destPath="C:/wamp64/www/RefugeeCampProjectJEE/profilepics";
		 try (InputStream input = file.getInputStream()) {
		        Files.copy(input, new File(destPath,member.getLogin()+ ".jpg").toPath(),
		        		StandardCopyOption.REPLACE_EXISTING);
		    }
		    catch (IOException e) {
		        // Show faces message?
		    } 
	}
	
	
}
