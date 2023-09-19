package com.sp.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "micro_ratings")
public class Rating {
	@MongoId
	@Field(name="RATING_ID")
	private String ratingId;
	@Field(name="RATING_USERID")
	private String userId;
	@Field(name="RATING_HOTELID")
	private String hotelId;
	@Field(name="RATING")
	private int rating;
	@Field(name="RATING_FEEDBACK")
	private String feedback;
}
