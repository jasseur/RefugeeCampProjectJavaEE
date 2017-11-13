package tn.esprit.RefugeeCampProject.services.AdoptionManagment;

import java.util.List;

import tn.esprit.RefugeeCampProject.Entities.AdoptionManagment.Orphan;

public interface OrphanServiceRemote {
	
	public int ajouterOrphan( Orphan orphan);
	public void removeOrphan(int orphanId);
	public void updateOrphan(Orphan orphan);
	public List<Orphan> getAllOrphans();
	
}
