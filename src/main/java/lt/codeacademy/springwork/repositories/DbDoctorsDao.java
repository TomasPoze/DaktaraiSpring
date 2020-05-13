package lt.codeacademy.springwork.repositories;

import lt.codeacademy.springwork.models.Doctor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Primary
@Repository
public class DbDoctorsDao implements DoctorsDao {

    private JdbcTemplate jdbcTemplate;

    public DbDoctorsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Doctor> getDoctor(Long id) {
        return Optional.ofNullable(
                jdbcTemplate.queryForObject("SELECT * FROM Doctors WHERE doctor_id = ?",
                        new Object[]{id},
                        (resultSet, rowNum) -> mapToDoctor(resultSet, rowNum))
        );
    }

    @Override
    public boolean deleteDoctor(Long id) {
        return false;
    }

    @Override
    public List<Doctor> getDoctors() {
        return jdbcTemplate.query("SELECT * FROM Doctors;",((resultSet, rowNum) -> mapToDoctor(resultSet,rowNum)));
    }

    private Doctor mapToDoctor(ResultSet resultSet, int rowNum) throws SQLException{
        Doctor doctor = new Doctor();
        doctor.setId(resultSet.getLong("doctor_id"));
        doctor.setDocname(resultSet.getString("docname"));
        doctor.setRegion(resultSet.getString("region"));
        doctor.setSpec(resultSet.getString("spec"));

        return doctor;
    }

    @Override
    public Doctor updateDoctor(Doctor doctor) {
        return null;
    }

    @Override
    public Doctor createDoctor(Doctor doctor) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Doctors(docname,region,spec) VALUES(?,?,?)",
                    new String[] {"doctor_id"}
                    );
            ps.setString(1,doctor.getDocname());
            ps.setString(2,doctor.getRegion());
            ps.setString(3,doctor.getSpec());
            return ps;
        }, keyHolder);
        return getDoctor(keyHolder.getKey().longValue()).get();
    }


}
