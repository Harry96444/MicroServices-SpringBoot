package com.sp.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="micro_hotels")
public class Hotel {
	@Id
	@Column(name="HOTEL_ID")
	private String id;
	@Column(name="HOTEL_NAME",length = 20)
	private String name;
	@Column(name="HOTEL_LOCATION")
	private String location;
	@Column(name="HOTEL_ABOUT")
	private String about;
}
