package com.api.rest.RestService.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

import com.api.rest.RestService.enums.Methods;
import com.api.rest.RestService.repository.AuditRespository;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "audit")
public class Audit {
	
	private static LocalDateTime currentDateTime = LocalDateTime.now();
	private static  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String method;
	
	private String date;
	
	public Audit() {
		this.date = currentDateTime.format(formatter);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, id, method);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Audit other = (Audit) obj;
		return Objects.equals(date, other.date) && Objects.equals(id, other.id) && method == other.method;
	}

	@Override
	public String toString() {
		return "Audit [id=" + id + ", method=" + method + ", date=" + date + "]";
	}
	
	
}
