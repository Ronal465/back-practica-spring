package com.back.practique.springboot.interfaces;

import java.util.Date;

public interface User <Id,R extends Role<?>>{

    Id getId();

    String getName();

    R getRole();
	
    Date getCreationDate();     

    void setId(Id id);

    void setName(String name);

    void setRole(R role);

    void setCreationDate(Date date);

}