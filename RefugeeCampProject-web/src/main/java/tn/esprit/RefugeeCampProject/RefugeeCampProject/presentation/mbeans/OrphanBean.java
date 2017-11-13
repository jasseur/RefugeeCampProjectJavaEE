package tn.esprit.RefugeeCampProject.RefugeeCampProject.presentation.mbeans;

import java.util.List;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


import tn.esprit.RefugeeCampProject.Entities.AdoptionManagment.Orphan;
import tn.esprit.RefugeeCampProject.services.AdoptionManagment.OrphanService;

@ManagedBean
@SessionScoped
public class OrphanBean {
	
	private String firstName;
	private String lastName;
	private String gender;
	private int age;
	private String nationality;
	private String educationLevel;
	private String activities;
	private int orphanIdToBeUpdated;
	
	
	
public int getOrphanIdToBeUpdated() {
		return orphanIdToBeUpdated;
	}

	public void setOrphanIdToBeUpdated(int orphanIdToBeUpdated) {
		this.orphanIdToBeUpdated = orphanIdToBeUpdated;
	}

private Orphan orphan;
	
	private List<Orphan> Orphans;
	
	@EJB
	OrphanService orphanService;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getEducationLevel() {
		return educationLevel;
	}

	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
	}

	public String getActivities() {
		return activities;
	}

	public void setActivities(String activities) {
		this.activities = activities;
	}

	public Orphan getOrphan() {
		return orphan;
	}

//	public void setOrphan(Orphan orphan) {
//		this.orphan = orphan;
//	}


//	public void setOrphans(List<Orphan> orphans) {
//		Orphans = orphans;
//	}

//	public OrphanService getOrphanService() {
//		return orphanService;
//	}
//
//	public void setOrphanService(OrphanService orphanService) {
//		this.orphanService = orphanService;
//	}
	
	
	public List<Orphan> getOrphans() {
		Orphans=orphanService.getAllOrphans();
		System.out.println("try222222");
		return Orphans;
	}
	
	
public String modifier (Orphan orphan){
		
		String navigateTo="null";
		this.setFirstName(orphan.getFirstName());
		this.setLastName(orphan.getLastName());
		this.setGender(orphan.getGender());
		this.setAge(orphan.getAge());
		this.setNationality(orphan.getNationality());
		this.setEducationLevel(orphan.getEducationLevel());
		this.setActivities(orphan.getActivities());
		
		navigateTo="/ajout?faces-redirect=true";
		return navigateTo;
		
	}


	public void mettreAjourOrphan(){
		orphanService.updateOrphan(new Orphan(orphanIdToBeUpdated ,firstName, lastName, gender, age, nationality, educationLevel, activities));
	}
	
	
	public void addOrphan(){
		orphanService.ajouterOrphan(new Orphan(firstName, lastName, gender, age, nationality, educationLevel, activities));
		System.out.println("le nom est"+firstName);
		
	}
	
	public void supprimer(int id){
		orphanService.removeOrphan(id);
		System.out.println("orphan"+id+"est supprimé");
	}
	

	
	

}
