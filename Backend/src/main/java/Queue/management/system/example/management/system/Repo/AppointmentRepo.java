package Queue.management.system.example.management.system.Repo;

import Queue.management.system.example.management.system.beans.Appointment;
import Queue.management.system.example.management.system.beans.DoctorType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepo extends JpaRepository<Appointment,Integer> {
    List<Appointment> findAllByDoctorType (DoctorType doctorType);

}
