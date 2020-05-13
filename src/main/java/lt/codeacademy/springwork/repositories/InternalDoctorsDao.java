package lt.codeacademy.springwork.repositories;

import lt.codeacademy.springwork.models.Doctor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
public class InternalDoctorsDao implements DoctorsDao {
    private List<Doctor> doctors;

    public InternalDoctorsDao(){
        this.doctors = buildDoctors();
    }

    private List<Doctor> buildDoctors() {
        Doctor doctor1 = new Doctor();
        doctor1.setId(1L);
        doctor1.setDocname("Rimas");
        doctor1.setRegion("Vilnius");
        doctor1.setSpec("Daktaras");

        Doctor doctor2 = new Doctor();
        doctor2.setId(2L);
        doctor2.setDocname("Jonas");
        doctor2.setRegion("Klaipeda");
        doctor2.setSpec("Daktaras");

        Doctor doctor3 = new Doctor();
        doctor3.setId(3L);
        doctor3.setDocname("Irena");
        doctor3.setRegion("Kaunas");
        doctor3.setSpec("Sesele");

        List<Doctor> doctors = new ArrayList<>();
        doctors.add(doctor1);
        doctors.add(doctor2);
        doctors.add(doctor3);

        return doctors;
    }

    @Override
    public Optional<Doctor> getDoctor(Long id) {
        return doctors.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    @Override
    public boolean deleteDoctor(Long id) {
        List<Doctor> newList = doctors.stream()
                .filter(p -> !p.getId().equals(id))
                .collect(Collectors.toList());

        boolean deleted = newList.size() != doctors.size();
        doctors = newList;

        return deleted;
    }

    @Override
    public List<Doctor> getDoctors() {
        return doctors;
    }

    @Override
    public Doctor updateDoctor(Doctor doctor) {
        getDoctor(doctor.getId()).ifPresent(existingProduct -> {
            existingProduct.setRegion(doctor.getRegion());
            existingProduct.setDocname(doctor.getDocname());
            existingProduct.setSpec(doctor.getSpec());
        });

        return doctor;
    }

    @Override
    public Doctor createDoctor(Doctor doctor) {
        Long maxId = doctors.stream()
                .mapToLong(Doctor::getId)
                .max().orElse(0L);

        doctor.setId(maxId + 1);
        doctors.add(doctor);

        return doctor;
    }
}
