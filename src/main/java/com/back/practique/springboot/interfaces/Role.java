package com.back.practique.springboot.interfaces;

public interface Role<Id> {

    Id getId();

    String getName();

    void setId(Id id);

    void setName(String name);
    	
}