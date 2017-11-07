package tn.esprit.RefugeeCampProject.RefugeeCampProject.services.MissionManagment;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import tn.esprit.RefugeeCampProject.Entities.CampManagment.Mission;


@Stateless
@LocalBean
public  class MissionManagmentService implements MissionManagmentServiceRemote {

	@PersistenceContext(unitName="RefugeeCampProject-ejb")
	EntityManager em; 
	
	@Override
	public int addMission(Mission mission) {
		em.persist(mission);
		return mission.getId();
	}

	@Override
	public int updateMission(Mission mission) {
		em.merge(mission);
		return mission.getId();
	}

	@Override
	public void deleteMission(Mission mission) {
		em.merge(mission);
		em.remove(mission);
	}

	@Override
	public void deleteMissionById(int missionId) {
		Mission missionManaged = em.find(Mission.class, missionId);
		em.remove(missionManaged);
	}

	@Override
	public Mission getMissionById(int missionId) {
		return  em.find(Mission.class, missionId);
	}

	@Override
	public List<Mission> getAllMissions() {
		TypedQuery<Mission> query = em.createQuery("Select c from Mission c",Mission.class);
		return query.getResultList();
	}

}
