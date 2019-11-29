package com.cos.crud.utils;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class CommonUtil {
	
	
	public static Timestamp getCurrentDate() {
		LocalDateTime ldt = LocalDateTime.now();
		return Timestamp.valueOf(ldt);
	}
	
}
