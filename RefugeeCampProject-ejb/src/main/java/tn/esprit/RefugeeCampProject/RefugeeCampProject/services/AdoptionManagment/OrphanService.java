package tn.esprit.RefugeeCampProject.services.AdoptionManagment;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.RefugeeCampProject.Entities.AdoptionManagment.Orphan;

@Stateless
@LocalBean
public class OrphanService implements OrphanServiceRemote{
	
	@PersistenceContext(unitName="RefugeeCampProject-ejb")
	EntityManager em;
	
	@Override
	public int ajouterOrphan( Orphan orphan){
		em.persist(orphan);
		return orphan.getId();
	}
	
	@Override
	public void removeOrphan(int orphanId) {
		System.out.println("l id est");
		Orphan o=em.find(Orphan.class, orphanId);
		em.remove(o);	
	}
	
	@Override 
	public void updateOrphan(Orphan orphan){
		em.merge(orphan);
	}
	
	@Override
	public List<Orphan> getAllOrphans(){
		Query query = em.createQuery("Select o from Orphan o");
		System.out.println("tryyyyyyy");
		return query.getResultList();
	}
	
	
	
}
