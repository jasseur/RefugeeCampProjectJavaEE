package tn.esprit.RefugeeCampProject.RefugeeCampProject.services.RegistrationManagment;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.RefugeeCampProject.Entities.RegistrationManagment.Member;

@Stateless
@LocalBean
public  class MemberManagmentService implements RegistrationServiceRemote {

	@PersistenceContext(unitName="RefugeeCampProject-ejb")
	EntityManager em; 
	
	@Override
	public int addMember(Member member) {
		em.persist(member);
		return member.getId();
	}

	@Override
	public int updateMember(Member member) {
		em.merge(member);
		return member.getId();
	}

	@Override
	public void deleteMember(Member member) {
		em.merge(member);
		em.remove(member);
	}

	@Override
	public void deleteMemberById(int memberId) {
		Member memberManaged = em.find(Member.class, memberId);
		em.remove(memberManaged);
	}

	@Override
	public Member getMemberById(int memberId) {
		return  em.find(Member.class, memberId);
	}

	@Override
	public Member getMemberByLoginAndPasswod(String login, String password) {
		TypedQuery<Member> query = em.createQuery("Select m from Member m"
				+" where (m.login=:login or m.email=:email) and "
				+ "m.password=:password "
				,Member.class);
					query.setParameter("login",login);
					query.setParameter("email",login);
					query.setParameter("password",password);
				
					Member member = null; 
					try{
						member = query.getSingleResult();
					}catch(NoResultException e){
						System.out.println("Aucun info");	
					}

					return member;
	}

	@Override
	public List<Member> getAllMembers() {
		TypedQuery<Member> query = em.createQuery("Select m from Member m",Member.class);
		return query.getResultList();
	}

}
