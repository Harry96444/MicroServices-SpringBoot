package com.sp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sp.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String> {

}
