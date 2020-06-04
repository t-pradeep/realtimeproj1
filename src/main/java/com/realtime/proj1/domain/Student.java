package com.realtime.proj1.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Student {
	private String name;
	private String batch;
	private CCStatus ccStatus;
	private PlacedStatus placedStatus;
	private String degree;
	private float score;

}
