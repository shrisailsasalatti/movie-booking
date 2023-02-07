package com.merin.moviebooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.merin.moviebooking.entity.Screen;

@Repository
public interface IScreenRepository extends JpaRepository<Screen, Integer>
{
	

}
