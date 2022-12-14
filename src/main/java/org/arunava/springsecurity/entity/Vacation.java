package org.arunava.springsecurity.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;


@Entity
@Data
public class Vacation {
	
	@javax.persistence.Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;
	
	@NotNull(message = "please enter hotel name")
	private String hotelName;
	
	@NotNull(message = "please enter discription")
	@Size(min = 10 ,max = 50, message ="Description  should be atleast 10 charecters max 50" )
	private String description;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "please set valid type 'resort','cruise','hotel'")
	private Type type;
	
	@NotNull(message = "please set city")
	private String city;
	
	@NotNull(message = "please set price")
	private Number price;
	
	@NotNull(message = "select valid date")
	private Date validTill;
	
	private boolean soldout;
	
	private String image;
	
	

}
