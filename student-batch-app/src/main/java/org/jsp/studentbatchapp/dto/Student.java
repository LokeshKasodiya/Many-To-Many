package org.jsp.studentbatchapp.dto;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false, unique = true)
	private long phone;
	@Column(nullable = false)
	private int age;
	@Column(nullable = false)
	private float percentage;
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "student_batch", 
        joinColumns = { @JoinColumn(name = "student_id",unique = true) }, 
        inverseJoinColumns = { @JoinColumn(name = "batch_id",unique = true) }
    )
	private List<Batch> batch;

	

	public List<Batch> getBatches() {
		return batch;
	}

	public void setBatches(List<Batch> batch) {
		this.batch = batch;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

}
