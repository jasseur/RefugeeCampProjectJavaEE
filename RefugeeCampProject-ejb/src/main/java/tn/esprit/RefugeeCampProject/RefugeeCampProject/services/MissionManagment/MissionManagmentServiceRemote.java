package tn.esprit.RefugeeCampProject.RefugeeCampProject.services.MissionManagment;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.RefugeeCampProject.Entities.CampManagment.Mission;





@Remote
public interface MissionManagmentServiceRemote {

	public int addMission(Mission mission);
	public int updateMission(Mission mission);
	public void deleteMission(Mission mission);
	public void deleteMissionById(int MissionId);
	public Mission getMissionById(int missionId);
	public List<Mission> getAllMissions();
	public boolean checkStaffAccount(String staffLogin) throws Exception;
	public String checkStaffAccountStringReturn(String staffLogin) throws Exception;
	public String addNewStaff(Mission mission);
}
