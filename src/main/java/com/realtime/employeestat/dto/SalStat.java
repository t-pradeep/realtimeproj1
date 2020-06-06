package com.lwl.ems.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalStat {
	 private double  max;
	 private  double  min;
	 private double  avg;
	 private long count;	
}
