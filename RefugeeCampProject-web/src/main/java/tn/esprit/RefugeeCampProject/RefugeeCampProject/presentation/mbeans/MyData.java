package tn.esprit.RefugeeCampProject.RefugeeCampProject.presentation.mbeans;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import tn.esprit.RefugeeCampProject.Types.Gender;
import tn.esprit.RefugeeCampProject.Types.Role;


@ManagedBean
@ApplicationScoped
public class MyData {
	
public Role[] getRoles(){
	return Role.values();
}

public Gender[] getGenders(){
	return Gender.values();
}
}
