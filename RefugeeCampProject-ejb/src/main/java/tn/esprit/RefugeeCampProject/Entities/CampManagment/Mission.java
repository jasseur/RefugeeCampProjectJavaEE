package tn.esprit.RefugeeCampProject.Entities.CampManagment;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import tn.esprit.RefugeeCampProject.Entities.RegistrationManagment.Member;
@Entity
public class Mission implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String type;
	private Date date;
	private Date startDate;
	private Date endDate;
	private String description;
	
	@ManyToOne
	Member member;
	
	@ManyToOne
	Camp camp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Camp getCamp() {
		return camp;
	}

	public void setCamp(Camp camp) {
		this.camp = camp;
	}

	public Mission(int id, String type, Date date, Date startDate, Date endDate, String description, Member member,
			Camp camp) {
		super();
		this.id = id;
		this.type = type;
		this.date = date;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.member = member;
		this.camp = camp;
	}

	public Mission(String type, Date date, Date startDate, Date endDate, String description, Member member, Camp camp) {
		super();
		this.type = type;
		this.date = date;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.member = member;
		this.camp = camp;
	}

	public Mission() {
		super();
	}
	
	
	
	
	
	
}
