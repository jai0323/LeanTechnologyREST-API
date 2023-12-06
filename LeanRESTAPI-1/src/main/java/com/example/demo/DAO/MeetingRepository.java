package com.example.demo.DAO;
import java.util.*;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.beans.Meeting;


public interface MeetingRepository extends CrudRepository<Meeting, Integer> {

}
