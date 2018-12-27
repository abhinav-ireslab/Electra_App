package com.ireslab.electraapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ireslab.electraapp.entity.Notification;

public interface NotificationRepository extends CrudRepository<Notification, Integer>{
	
	@Query(value = "SELECT c FROM Notification c WHERE c.status =:status AND c.mobileNumber =:mobileNumber ORDER BY c.createdDate DESC")
	public List<Notification> findAllBymobileNumber(@Param ("status") boolean status,@Param ("mobileNumber") String mobileNumber); 
	
	public Notification findByNotificationId(Integer notificationId);

}
