package com.realtime.employeestat;

import java.util.OptionalDouble;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalStat {
	private double  max;
	private  double  min;
	private double  avg;
	private long count;

}
