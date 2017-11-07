package tn.esprit.RefugeeCampProject.Entities.CampManagment;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import tn.esprit.RefugeeCampProject.Types.Location;
@Entity
public class Camp implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private long capacity;
	private String latitude ;
	private String longitude; 
		
	@OneToMany (mappedBy="camp")
	 List<Mission> missions;
	
	public Camp() {
		super();
	}

	public Camp(String name, long capacity, String latitude, String longitude) {
		super();
		this.name = name;
		this.capacity = capacity;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Camp(int id, String name, long capacity, String latitude, String longitude) {
		super();
		this.id = id;
		this.name = name;
		this.capacity = capacity;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getCapacity() {
		return capacity;
	}
	public void setCapacity(long capacity) {
		this.capacity = capacity;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}


	
	
	
}
