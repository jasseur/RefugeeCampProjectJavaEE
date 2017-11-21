package tn.esprit.RefugeeCampProject.RefugeeCampProject.services.MembershipDemandManagment;
import java.util.List;

import javax.ejb.Remote;

import tn.esprit.RefugeeCampProject.Entities.RegistrationManagment.Member;
import tn.esprit.RefugeeCampProject.Entities.RegistrationManagment.MembershipDemand;



@Remote
public interface MembershipDemandManagmentServiceRemote {

	public int addMembershipDemand(MembershipDemand membershipDemand);
	public int updateMembershipDemand(MembershipDemand membershipDemand);
	public void deleteMembershipDemand(MembershipDemand membershipDemand);
	public void deleteMembershipDemandById(int membershipDemandId);
	public MembershipDemand getMembershipDemandById(int membershipDemandId);
	public List<MembershipDemand> getAllMembershipDemands();
	public List<MembershipDemand> getAllAcceptedMembershipDemands();
	public List<MembershipDemand> getAllNotAcceptedMembershipDemands();
	public List<MembershipDemand> getAllMembershipDemandsByMemberId(Member member);
	public List<MembershipDemand> getAllMembershipDemandsByMembeNameLike(String name ,Member member) ;
	public List<MembershipDemand> getAllMembershipDemandsByMembeNameLike(String name );
	public boolean checkLogin(String login);
	public boolean checkEmail(String email);
}
