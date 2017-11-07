package tn.esprit.RefugeeCampProject.RefugeeCampProject.services.MembershipDemandManagment;
import java.util.List;

import javax.ejb.Remote;

import tn.esprit.RefugeeCampProject.Entities.RegistrationManagment.MembershipDemand;



@Remote
public interface MembershipDemandManagmentServiceRemote {

	public int addMembershipDemand(MembershipDemand membershipDemand);
	public int updateMembershipDemand(MembershipDemand membershipDemand);
	public void deleteMembershipDemand(MembershipDemand membershipDemand);
	public void deleteMembershipDemandById(int membershipDemandId);
	public MembershipDemand getMembershipDemandById(int membershipDemandId);
	public List<MembershipDemand> getAllMembershipDemands();
	
}
