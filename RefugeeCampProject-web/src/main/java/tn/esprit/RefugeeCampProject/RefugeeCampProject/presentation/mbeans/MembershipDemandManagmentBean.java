package tn.esprit.RefugeeCampProject.RefugeeCampProject.presentation.mbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;


import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.servlet.http.Part;


import tn.esprit.RefugeeCampProject.Entities.RegistrationManagment.Member;
import tn.esprit.RefugeeCampProject.Entities.RegistrationManagment.MembershipDemand;
import tn.esprit.RefugeeCampProject.RefugeeCampProject.services.MembershipDemandManagment.MembershipDemandManagmentService;
import tn.esprit.RefugeeCampProject.RefugeeCampProject.services.RegistrationManagment.MemberManagmentService;
import tn.esprit.RefugeeCampProject.Types.Gender;
import tn.esprit.RefugeeCampProject.Types.Role;
import tn.esprit.RefugeeCampProject.Types.RoleSelectList;

@ManagedBean
@SessionScoped
public class MembershipDemandManagmentBean {
	
	
	private int idMembershipDemand;
	private MembershipDemand membershipDemand = new MembershipDemand() ;
	private List<MembershipDemand> membershipDemands;
	private List<MembershipDemand> allMembershipDemandsFiltred;
	private String nameFilter;
	private Part  file;
	@EJB
	MembershipDemandManagmentService mdms;
	@EJB
	MemberManagmentService mmb;
	
	@ManagedProperty(value="#{loginBean}")
	LoginBean loginBean;
	
	//check attributes
	private String loginCheckMessage;
	private String emailCheckMessage;
	
	private String login;
	private String email;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private Date registrationDate;
	@Enumerated(EnumType.STRING)
	private Gender gender;
	@Enumerated(EnumType.STRING)
	private RoleSelectList role;
	private String description;

	
	
	@EJB
	MemberManagmentService mms;

	@PostConstruct
	private void init(){
		membershipDemand = new MembershipDemand();
		
		
	}
	
	
	
	public String getLogin() {
		return login;
	}



	public void setLogin(String login) {
		this.login = login;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getLoginCheckMessage() {
		return loginCheckMessage;
	}

	public void setLoginCheckMessage(String loginCheckMessage) {
		this.loginCheckMessage = loginCheckMessage;
	}

	public String getEmailCheckMessage() {
		return emailCheckMessage;
	}

	public void setEmailCheckMessage(String emailCheckMessage) {
		this.emailCheckMessage = emailCheckMessage;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public Part  getFile() {
		return file;
	}

	public void setFile(Part  file) {
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
		this.allMembershipDemandsFiltred = membershipDemandsFiltred;
	}

	//membership demands viewed by manager only
	public List<MembershipDemand> getMembershipDemands() {
		this .membershipDemands = mdms.getAllNotAcceptedMembershipDemands();
		return membershipDemands;
	}
	
	//membership demands by member viewed by member
	public List<MembershipDemand> getMembershipDemandsByMemberId() {
		
		return mdms.getAllMembershipDemandsByMemberId(loginBean.getMember());
	}

	public void setMembershipDemands(List<MembershipDemand> membershipDemands) {
		this.membershipDemands = membershipDemands;
	}
	
	//membership demand : all members can use it
	public String addNewMember(){
		membershipDemand= new MembershipDemand();
		
		membershipDemand.setLogin(login);
		membershipDemand.setEmail(email);
		membershipDemand.setFirstName(firstName);
		membershipDemand.setLastName(lastName);
		membershipDemand.setRole(role);
		membershipDemand.setBirthDate(birthDate);
		membershipDemand.setGender(gender);
		membershipDemand.setDescription(description);
		membershipDemand.setPassword("changeme");
		membershipDemand.setRegistrationDate(new Date());
		membershipDemand.setMember(loginBean.getMember());
		mdms.addMembershipDemand(membershipDemand);
	
		  login="";
		  email="";
		  firstName="";
		  lastName="";
		  birthDate=null;
		  registrationDate=null;
		  description="";

	return "listSendedMembershipDemands?faces-redirect = true";
	}
	
	
	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public Date getBirthDate() {
		return birthDate;
	}



	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}



	public Date getRegistrationDate() {
		return registrationDate;
	}



	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}



	public Gender getGender() {
		return gender;
	}



	public void setGender(Gender gender) {
		this.gender = gender;
	}



	public RoleSelectList getRole() {
		return role;
	}



	public void setRole(RoleSelectList role) {
		this.role = role;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	// listener ajax input file
	public void save(){
		String destPath="C:/wamp64/www/RefugeeCampProjectJEE/profilepics";
		 try (InputStream input = file.getInputStream()) {
		        Files.copy(input, new File(destPath,this.login+ ".jpg").toPath(),
		        		StandardCopyOption.REPLACE_EXISTING);
		    }
		    catch (IOException e) {
		        // Show faces message?
		    } 	
			
		
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
		Role roleMember;
		if(membershipDemand.getRole() == RoleSelectList.Doctor)
			roleMember=Role.Doctor;
		else if(membershipDemand.getRole() == RoleSelectList.Teacher)
			roleMember=Role.Teacher;
		else 
			roleMember=Role.Member;
		Member member = new Member(membershipDemand.getLogin(),
				membershipDemand.getEmail(),
				"changeme",
				membershipDemand.getFirstName(),
				membershipDemand.getLastName(),
				membershipDemand.getBirthDate(),
				new Date(),
				membershipDemand.getGender(),
				roleMember
				);
		mmb.addMember(member);
		
		//send mail to member
		
		
//		Properties props = new Properties();
//		props.put("mail.smtp.host", "smtp.gmail.com");
//		props.put("mail.smtp.socketFactory.port", "465");
//		props.put("mail.smtp.socketFactory.class",
//				"javax.net.ssl.SSLSocketFactory");
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.port", "465");
//		try {
//		
//			Session session = Session.getInstance(props,
//			new javax.mail.Authenticator() {
//				protected PasswordAuthentication getPasswordAuthentication() {
//					return new PasswordAuthentication("jasserbenabdallah@gmail.com","jo@26252400");
//				}
//			});
//
//
//			Message message = new MimeMessage(session);
//			message.setFrom(new InternetAddress("jasserbenabdallah@gmail.com"));
//			message.setRecipients(Message.RecipientType.TO,
//					InternetAddress.parse(member.getEmail()));
//			message.setSubject("ICRC Account");
//			message.setText("Dear member," +
//					"\n\n Confirm your password with the link bellow :"+
//					"\n\n http://localhost:18080/RefugeeCampProject-web/");
//
//			Transport.send(message);
//
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		}
//		
		
		
		return "listMembershipDemands?faces-redirect = true";
		
	}
	
// deny demand
	public String denyDemand (){
		this.membershipDemand.setAccepted(false);
		
		return "listMembershipDemands";	
	}
	
	public List<MembershipDemand> getAllMembershipDemandsFiltred() {
		this .membershipDemands = mdms.getAllMembershipDemandsByMembeNameLike(this.nameFilter );
		this.allMembershipDemandsFiltred = membershipDemands;
		return membershipDemands;
	}
	
	
	
	public void setAllMembershipDemandsFiltred(List<MembershipDemand> allMembershipDemandsFiltred) {
		this.allMembershipDemandsFiltred = allMembershipDemandsFiltred;
	}

	public List<MembershipDemand> getMembershipDemandsFiltred() {
		this .membershipDemands = mdms.getAllMembershipDemandsByMembeNameLike(this.nameFilter ,loginBean.getMember());
		this.allMembershipDemandsFiltred = membershipDemands;
		return membershipDemands;
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
}
