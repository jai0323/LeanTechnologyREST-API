package com.example.demo;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DAO.MeetingRepository;
import com.example.demo.beans.Meeting;


@RestController
public class MainController {

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
	DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	LocalDateTime now = LocalDateTime.now();
	LocalDate currentDate = LocalDate.now();
	
	@Autowired
	MeetingRepository repo;
	
	//To get all the meeting------------------------------------------------------------------------------
	@GetMapping("bookingall")
	public List<Meeting> booking() {
		List<Meeting> meetings = new ArrayList<>();
		for(Meeting meeting:repo.findAll()) {
			meetings.add(meeting);
		}
		return meetings;
	}

	// For booking the recurring session-----------------------------------------------------------------------------
		@PostMapping("bookMeeting")
		public String bookMeeting(@RequestBody Meeting meeting) {
			if(meeting.getRecurring().equals("no")) // for once only
			{
				repo.save(meeting);
			}
			else if(meeting.getRecurring().equals("1/7/30")) //for once per week for 1 month
			{
				ArrayList<String>dates=printDates(meeting.getDate(),1,7,4);
				int id=meeting.getMeetingId();
				for(String date:dates)
				{					
					meeting.setDate(date);
					meeting.setMeetingId(id);
					id++;
					repo.save(meeting);
				}
			}
			else if(meeting.getRecurring().equals("2/7/60")) //for twice per week for 2 month
			{
				ArrayList<String>dates=printDates(meeting.getDate(),2,3,10);
				int id=meeting.getMeetingId();
				for(String date:dates)
				{
					meeting.setDate(date);
					meeting.setMeetingId(id);
					id++;
					repo.save(meeting);
				}
			}
			return "booking done!";
		}
	
		
		// For cancelling the session----------------------------------------------------------------------------
		@DeleteMapping("cancelMeeting")
		public String deleteMeeting(@RequestBody Meeting meeting) {
			
			String time=formatter.format(now);
			String meetingtime = meeting.getDate()+" "+meeting.getTime();
			LocalDateTime userMeetingTime = LocalDateTime.parse(meetingtime, formatter);
			LocalDateTime currentTime = LocalDateTime.parse(time, formatter);
	        Duration duration = Duration.between(currentTime, userMeetingTime);
	        long hours = duration.toHours();
	        
	        if(hours>12) {
			repo.delete(meeting);
			return "booking Cancelled!";
	        }
	        return "Cannot Cancel the Meeting, Remaining time is less than 12 hours";
		}
		
		
		// For reshceduling the session------------------------------------------------------------------------
		@PutMapping("meetingReschedule")
		public String updateMeeting(@RequestBody Meeting meeting) {
			
			String time=formatter.format(now);
			String meetingtime = meeting.getDate()+" "+meeting.getTime();
			LocalDateTime userMeetingTime = LocalDateTime.parse(meetingtime, formatter);
			LocalDateTime currentTime = LocalDateTime.parse(time, formatter);
	        Duration duration = Duration.between(currentTime, userMeetingTime);
	        long hours = duration.toHours();
	        
	        if(hours>4) {
	        	repo.save(meeting);
	    		return "booking Reschedule!";
	        }
			return "Cannot Reschedule, Remaining time is less than 4 hours";
		}
		
		// return dates for recurring meetings------------------------------------------------------------------------
		    public ArrayList<String> printDates(String Date, int numberOfMonths,int numberOfDays,int noOfBooking) 
		    {   LocalDate startDate=LocalDate.parse(Date,formatter1);
		        ArrayList<String> recurringDate=new ArrayList<>();
		        for (int i = 0; i < numberOfMonths; i++) {
		            for (int j = 0; j < noOfBooking; j++) { 
		            	recurringDate.add(startDate.format(formatter1));
		                startDate = startDate.plusDays(numberOfDays);
		            }
		            // Move to the first day of the next month
		            startDate = startDate.plusMonths(1).withDayOfMonth(1);
		        }
		        return recurringDate;
	        }
		
}

