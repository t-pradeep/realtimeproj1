package com.realtime.proj1.quizdomain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Quiz {
	
	private String quiz;
	private List<Questions> questions;

}
