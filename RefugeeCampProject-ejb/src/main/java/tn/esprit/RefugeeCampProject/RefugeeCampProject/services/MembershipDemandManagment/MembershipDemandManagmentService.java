package tn.esprit.RefugeeCampProject.RefugeeCampProject.services.MembershipDemandManagment;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.RefugeeCampProject.Entities.RegistrationManagment.Member;
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

	@Override
	public List<MembershipDemand> getAllAcceptedMembershipDemands() {
		TypedQuery<MembershipDemand> query = em.createQuery("Select c from MembershipDemand c where c.accepted=true",MembershipDemand.class);
		return query.getResultList();
	}

	@Override
	public List<MembershipDemand> getAllNotAcceptedMembershipDemands() {
		TypedQuery<MembershipDemand> query = em.createQuery("Select c from MembershipDemand c where c.accepted=false",MembershipDemand.class);
		return query.getResultList();
	}

	@Override
	public List<MembershipDemand> getAllMembershipDemandsByMemberId(Member member) {
		TypedQuery<MembershipDemand> query = em.createQuery("Select c from MembershipDemand c where c.member=:member",MembershipDemand.class)
				.setParameter("member", member);
		return query.getResultList();
	}
	
	public List<MembershipDemand> getAllMembershipDemandsByMembeNameLike(String name) {
		TypedQuery<MembershipDemand> query = em.createQuery("Select c from MembershipDemand c where c.firstName like :name",MembershipDemand.class)
				.setParameter("name", "%"+name+"%");
		return query.getResultList();
	}

}
