package tn.esprit.RefugeeCampProject.RefugeeCampProject.presentation.mbeans;

import javax.faces.bean.ManagedBean;

import java.util.List;

import javax.ejb.EJB;

import javax.faces.bean.SessionScoped;


import tn.esprit.RefugeeCampProject.Entities.RegistrationManagment.Member;
import tn.esprit.RefugeeCampProject.RefugeeCampProject.services.RegistrationManagment.MemberManagmentService;

@ManagedBean
@SessionScoped
public class MemberManagmentBean {
	
	private int idMemberToUpdate;
	private Member member;
	private List<Member> members;
	@EJB
	MemberManagmentService mms;
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
	
}
