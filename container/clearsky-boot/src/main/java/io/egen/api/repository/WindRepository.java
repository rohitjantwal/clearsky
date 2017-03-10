package io.egen.api.repository;

import org.springframework.data.repository.Repository;

import io.egen.api.entity.Wind;

public interface WindRepository extends Repository<Wind, String>{
	
	public Wind save(Wind wind); 

}
