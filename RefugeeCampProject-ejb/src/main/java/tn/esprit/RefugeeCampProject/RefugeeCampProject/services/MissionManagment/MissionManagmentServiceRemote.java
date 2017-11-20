package tn.esprit.RefugeeCampProject.RefugeeCampProject.services.MissionManagment;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import tn.esprit.RefugeeCampProject.Entities.CampManagment.Camp;
import tn.esprit.RefugeeCampProject.Entities.CampManagment.Mission;
import tn.esprit.RefugeeCampProject.Entities.RegistrationManagment.Member;





@Remote
public interface MissionManagmentServiceRemote {

	public int addMission(Mission mission);
	public int updateMission(Mission mission);
	public void deleteMission(Mission mission);
	public void deleteMissionById(int MissionId);
	public Mission getMissionById(int missionId);
	public List<Mission> getAllMissions(Camp camp);
	public boolean checkStaffAccount(String staffLogin) throws Exception;
	public String checkStaffAccountStringReturn(String staffLogin) throws Exception;
	public String addNewStaff(Mission mission);
	public List<Member> getAvailableMembers(Date startDate,Date endDate , String type);
}
