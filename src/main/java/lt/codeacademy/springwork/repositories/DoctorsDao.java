package lt.codeacademy.springwork.repositories;

import lt.codeacademy.springwork.models.Doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorsDao {
    Optional<Doctor> getDoctor(Long id);

    boolean deleteDoctor(Long id);

    List<Doctor> getDoctors();

    Doctor updateDoctor(Doctor doctor);

    Doctor createDoctor(Doctor doctor);
}
