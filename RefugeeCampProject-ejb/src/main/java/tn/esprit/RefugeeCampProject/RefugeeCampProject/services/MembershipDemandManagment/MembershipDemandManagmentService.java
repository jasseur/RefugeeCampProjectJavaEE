package tn.esprit.RefugeeCampProject.RefugeeCampProject.services.MembershipDemandManagment;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.RefugeeCampProject.Entities.RegistrationManagment.MembershipDemand;

@Stateless
@LocalBean
public  class MembershipDemandManagmentService implements MembershipDemandManagmentServiceRemote {

	@PersistenceContext(unitName="RefugeeCampProject-ejb")
	EntityManager em; 
	
	@Override
	public int addMembershipDemand(MembershipDemand membershipDemand) {
		em.persist(membershipDemand);
		return membershipDemand.getId();
	}

	@Override
	public int updateMembershipDemand(MembershipDemand membershipDemand) {
		em.merge(membershipDemand);
		return membershipDemand.getId();
	}

	@Override
	public void deleteMembershipDemand(MembershipDemand membershipDemand) {
		em.merge(membershipDemand);
		em.remove(membershipDemand);
	}

	@Override
	public void deleteMembershipDemandById(int membershipDemandId) {
		MembershipDemand membershipDemandManaged = em.find(MembershipDemand.class, membershipDemandId);
		em.remove(membershipDemandManaged);
	}

	@Override
	public MembershipDemand getMembershipDemandById(int membershipDemandId) {
		return  em.find(MembershipDemand.class, membershipDemandId);
	}

	@Override
	public List<MembershipDemand> getAllMembershipDemands() {
		TypedQuery<MembershipDemand> query = em.createQuery("Select c from MembershipDemand c",MembershipDemand.class);
		return query.getResultList();
	}

}
