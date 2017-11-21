package tn.esprit.RefugeeCampProject.RefugeeCampProject.services.CampManagment;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import tn.esprit.RefugeeCampProject.Entities.CampManagment.Camp;


@Stateless
@LocalBean
public  class CampManagmentService implements CampManagmentServiceRemote {

	@PersistenceContext(unitName="RefugeeCampProject-ejb")
	EntityManager em; 
	
	@Override
	public int addCamp(Camp camp) {
		em.persist(camp);
		return camp.getId();
	}

	@Override
	public int updateCamp(Camp camp) {
		em.merge(camp);
		return camp.getId();
	}

	@Override
	public void deleteCamp(Camp camp) {
		camp=em.merge(camp);
		em.remove(camp);
	}

	@Override
	public void deleteCampById(int campId) {
		Camp campManaged = em.find(Camp.class, campId);
		em.remove(campManaged);
	}

	@Override
	public Camp getCampById(int campId) {
		return  em.find(Camp.class, campId);
	}

	@Override
	public List<Camp> getAllCamps() {
		TypedQuery<Camp> query = em.createQuery("Select c from Camp c",Camp.class);
		return query.getResultList();
	}

}
