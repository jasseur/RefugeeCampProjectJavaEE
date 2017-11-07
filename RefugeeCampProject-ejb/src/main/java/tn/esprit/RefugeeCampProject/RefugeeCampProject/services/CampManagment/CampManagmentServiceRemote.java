package tn.esprit.RefugeeCampProject.RefugeeCampProject.services.CampManagment;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.RefugeeCampProject.Entities.CampManagment.Camp;



@Remote
public interface CampManagmentServiceRemote {

	public int addCamp(Camp camp);
	public int updateCamp(Camp camp);
	public void deleteCamp(Camp camp);
	public void deleteCampById(int campId);
	public Camp getCampById(int campId);
	public List<Camp> getAllCamps();
	
}
