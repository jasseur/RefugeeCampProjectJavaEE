package tn.esprit.RefugeeCampProject.RefugeeCampProject.services.MissionManagment;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.RefugeeCampProject.Entities.CampManagment.Camp;
import tn.esprit.RefugeeCampProject.Entities.CampManagment.Mission;
import tn.esprit.RefugeeCampProject.Entities.CampManagment.Staff;
import tn.esprit.RefugeeCampProject.Entities.RegistrationManagment.Member;
import tn.esprit.RefugeeCampProject.Entities.RegistrationManagment.MembershipDemand;
import tn.esprit.RefugeeCampProject.Types.Role;


@Stateless
@LocalBean
public  class MissionManagmentService implements MissionManagmentServiceRemote {

	@PersistenceContext(unitName="RefugeeCampProject-ejb")
	EntityManager em; 
	
	// download the URL as a text (json in our case)
	private  String readUrl(String urlString) throws Exception {
	    BufferedReader reader = null;
	    try {
	        URL url = new URL(urlString);
	        reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        StringBuffer buffer = new StringBuffer();
	        int read;
	        char[] chars = new char[1024];
	        while ((read = reader.read(chars)) != -1)
	            buffer.append(chars, 0, read); 

	        return buffer.toString();
	    } finally {
	        if (reader != null)
	            reader.close();
	    }
	}
	

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
		mission=em.merge(mission);
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
	public List<Mission> getAllMissions(Camp camp) {
		TypedQuery<Mission> query = em.createQuery("Select c from Mission c where c.camp=:camp",Mission.class)
				.setParameter("camp", camp);
		System.out.println("camp " + camp.getId());
		return query.getResultList();
	}
	
	@Override
	public List<Mission> getAllMissions(Member member) {
		TypedQuery<Mission> query = em.createQuery("Select c from Mission c where c.member=:member",Mission.class)
				.setParameter("member", member);

		return query.getResultList();
	}



	@Override
	public boolean checkStaffAccount(String staffLogin) throws Exception {
		try{
		String jsonGetStaffByLogin = readUrl("http://localhost:62754/api/Staffs/"+staffLogin);

		 JsonReader reader = Json.createReader(new StringReader(jsonGetStaffByLogin));
	        JsonObject staffObject = reader.readObject();
	        reader.close();
	        
	        Staff staff = new Staff();
	        staff.setLogin(staffObject.getString("Login"));
		
		return jsonGetStaffByLogin == "" ? false : true;
		}catch(Exception e){
			return false;
		}
	}
		
	// web REST check if Satff exist GET method  http://localhost:62754/api/Staffs/{login}
		@Override
		public String checkStaffAccountStringReturn(String staffLogin) throws Exception {
			try{
			String jsonGetStaffByLogin = readUrl("http://localhost:62754/api/Staffs/"+staffLogin);

			 JsonReader reader = Json.createReader(new StringReader(jsonGetStaffByLogin));
		        JsonObject staffObject = reader.readObject();
		        reader.close();
		        
		        Staff staff = new Staff();
		        staff.setLogin(staffObject.getString("Login"));
			
			return staffObject.getString("Login");
			}catch(Exception e){
				return "non";
			}
		
	}
		
		// web REST add new Satff  POST method  http://localhost:62754/api/Staffs
		@Override
		public String addNewStaff(Mission mission){
			String returnString = "ffffff";
			// create new Staff entity
			Staff staff = new Staff();
			staff.setFirstName(mission.getMember().getFirstName());
			staff.setLastName(mission.getMember().getLastName());
			staff.setBirthDate(mission.getMember().getBirthDate());
			staff.setHireDate(new Date());
			staff.setGender(mission.getMember().getGender().toString());
			staff.setEmail(mission.getMember().getEmail());
			staff.setRole(mission.getType());
			staff.setLogin(mission.getMember().getLogin());
			staff.setPassword(mission.getMember().getPassword());
			staff.setIdCamp(mission.getCamp().getId());
			
			
			URL url;
			try {
				url = new URL("http://localhost:62754/api/Staffs");
		
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			returnString="inputStaff";
			
			SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
			String input = "";
			 input = "{\"Id\": 0,";
			 input += "\"FirstName\": \""+staff.getFirstName()+"\",";
			 input += "\"LastName\": \""+staff.getLastName()+"\",";
			 input += "\"BirthDate\": \""+dateFormat.format(staff.getBirthDate())+"\",";
			 input += "\"HireDate\": \""+dateFormat.format(staff.getHireDate())+"\",";
			 input += "\"Gender\": \""+staff.getGender()+"\",";
			 input += "\"Email\": \""+staff.getEmail()+"\",";
			 input += "\"Role\": \""+staff.getRole()+"\",";
			 input += "\"Login\": \""+staff.getLogin()+"\",";
			 input += "\"Password\": \""+staff.getPassword()+"\",";
			 input += "\"CampId\": "+staff.getIdCamp()+"}";
			 
			 
//			 String input = "";
//			 input = "{\"Id\": 0,";
//			 input += "\"FirstName\": \"test\",";
//			 input += "\"LastName\": \"test\",";
//			 input += "\"BirthDate\": \"2017-11-1\",";
//			 input += "\"HireDate\": \"2017-11-1\",";
//			 input += "\"Gender\": \"test\",";
//			 input += "\"Email\": \"test\",";
//			 input += "\"Role\": \"test\",";
//			 input += "\"Login\": \"test\",";
//			 input += "\"Password\": \"test\"}";


				returnString="afterInputStaff";
	
			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			returnString="afterFlush";
			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode() +" date " + staff.getBirthDate());		
			}
			returnString="beforeBufferedReader";
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
			
			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
			returnString="beforedisconnect";
			conn.disconnect();
			return returnString;
			} catch (Exception e) {
				e.printStackTrace();
				return returnString;
			}
		}

		// add new mission if staff free
		public boolean addNewMission(Mission mission)
		{
			try {
				if(checkStaffAccount(mission.getMember().getLogin()))
				return false;
				
				//add new mission
				this.addMission(mission);

				// add POST new Staff to .Net Project 
				this.addNewStaff(mission);
				
				return true;
			} catch (Exception e) {
				
				e.printStackTrace();
				return false;
			}
		}


		@Override
		public List<Member> getAvailableMembers(Date startDate, Date endDate , String type) {
			TypedQuery<Member> query = em.createQuery("Select m from Member m where m.role=:role",Member.class)
					.setParameter("role", Role.Member);
			
			List<Member> members = query.getResultList();
			List<Member> membersResult = new ArrayList<Member>();
			for (Member member : members) {
			boolean notAvailable=false;
				for(Mission mission : member.getMissions()){
					if(mission.getEndDate().after(startDate) && mission.getEndDate().before(endDate)||
							mission.getStartDate().after(startDate) && mission.getStartDate().before(endDate)
							){
						notAvailable= true;
						
					}
				}
				if(!notAvailable) membersResult.add(member);
			}
			return membersResult;

		}
		
		@Override
		public List<Member> getAvailableDoctors(Date startDate, Date endDate , String type) {
			TypedQuery<Member> query = em.createQuery("Select m from Member m where m.role=:role",Member.class)
					.setParameter("role", Role.Doctor);
			List<Member> members = query.getResultList();
			List<Member> membersResult = new ArrayList<Member>();
			for (Member member : members) {
			boolean notAvailable=false;
				for(Mission mission : member.getMissions()){
					if(mission.getEndDate().after(startDate) && mission.getEndDate().before(endDate)||
							mission.getStartDate().after(startDate) && mission.getStartDate().before(endDate)
							){
						notAvailable= true;
						
					}
				}
				if(!notAvailable) membersResult.add(member);
			}
			return membersResult;
			
		}
		
		@Override
		public List<Member> getAvailableTeachers(Date startDate, Date endDate , String type) {
			TypedQuery<Member> query = em.createQuery("Select m from Member m where m.role=:role",Member.class)
					.setParameter("role", Role.Teacher);
			List<Member> members = query.getResultList();
			List<Member> membersResult = new ArrayList<Member>();
			for (Member member : members) {
			boolean notAvailable=false;
				for(Mission mission : member.getMissions()){
					if(mission.getEndDate().after(startDate) && mission.getEndDate().before(endDate)||
							mission.getStartDate().after(startDate) && mission.getStartDate().before(endDate)
							){
						notAvailable= true;
						
					}
				}
				if(!notAvailable) membersResult.add(member);
			}
			return membersResult;

		}
}
