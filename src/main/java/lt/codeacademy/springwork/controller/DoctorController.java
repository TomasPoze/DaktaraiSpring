package lt.codeacademy.springwork.controller;

import lt.codeacademy.springwork.models.Doctor;
import lt.codeacademy.springwork.services.DoctorsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/doctors")
public class DoctorController {

    private DoctorsService doctorsService;

    public DoctorController(DoctorsService doctorsService) {
        this.doctorsService = doctorsService;
    }

    @GetMapping("/{id}")
    public String getDoctor(@PathVariable Long id, Model model){
        Doctor doctor = doctorsService.getDoctor(id);
        model.addAttribute("doctor", doctor);
        return "doctorpage";
    }


    @GetMapping("/doctor/{id}")
    public String getUpdateDoctorForm(@PathVariable Long id, Model model) {
        Doctor doctor = doctorsService.getDoctor(id);
        model.addAttribute("doctor", doctor);
        return "doctorform";
    }

    @GetMapping("/doctor")
    public String createDoctorForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "doctorform";
    }

    @GetMapping("/doctor/{id}/delete")
    public String deleteDoctor(@PathVariable Long id, Model model) {
        doctorsService.deleteDoctor(id);
        List<Doctor> doctors = doctorsService.getAllDoctors();
        model.addAttribute("doctors", doctors);
        return "doctorlist";
    }

    @PostMapping("/doctor")
    public String submitDoctor(@ModelAttribute Doctor doctor, Model model) {

        model.addAttribute("doctor", doctorsService.createOrUpdateDoctor(doctor));
        return "doctorpage";
    }

    @GetMapping
    public String getAllDoctors(Model model){
        List<Doctor> doctors = doctorsService.getAllDoctors();
        model.addAttribute("doctors", doctors);
        return "doctorlist";
    }
}
