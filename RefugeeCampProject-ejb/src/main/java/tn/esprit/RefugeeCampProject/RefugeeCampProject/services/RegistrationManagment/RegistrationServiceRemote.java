package tn.esprit.RefugeeCampProject.RefugeeCampProject.services.RegistrationManagment;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.RefugeeCampProject.Entities.RegistrationManagment.Member;

@Remote
public interface RegistrationServiceRemote {

	public int addMember(Member member);
	public int updateMember(Member member);
	public void deleteMember(Member member);
	public void deleteMemberById(int memberId);
	public Member getMemberById(int memberId);
	public Member getMemberByLoginAndPasswod(String login,String password);
	public List<Member> getAllMembers();
	
}
