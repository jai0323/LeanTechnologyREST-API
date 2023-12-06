package com.example.demo.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="meeting")
public class Meeting {

	@Id
	int meetingId;
	
	String userId,mentorId,date,time,bookAt,recurring;

	
	
	@Override
	public String toString() {
		return "Meeting [meetingId=" + meetingId + ", userId=" + userId + ", mentorId=" + mentorId + ", date=" + date
				+ ", time=" + time + ", bookAt=" + bookAt + ", recurring=" + recurring + "]";
	}

	public int getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(int meetingId) {
		this.meetingId = meetingId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMentorId() {
		return mentorId;
	}

	public void setMentorId(String mentorId) {
		this.mentorId = mentorId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getBookAt() {
		return bookAt;
	}

	public void setBookAt(String bookAt) {
		this.bookAt = bookAt;
	}

	public String getRecurring() {
		return recurring;
	}

	public void setRecurring(String recurring) {
		this.recurring = recurring;
	}

	public Meeting(int meetingId, String userId, String mentorId, String date, String time, String bookAt,
			String recurring) {
	
		this.meetingId = meetingId;
		this.userId = userId;
		this.mentorId = mentorId;
		this.date = date;
		this.time = time;
		this.bookAt = bookAt;
		this.recurring = recurring;
	}

	public Meeting(String userId, String mentorId, String date, String time, String bookAt, String recurring) {
	
		this.userId = userId;
		this.mentorId = mentorId;
		this.date = date;
		this.time = time;
		this.bookAt = bookAt;
		this.recurring = recurring;
	}

	public Meeting() {
		
	}
	
	
}

