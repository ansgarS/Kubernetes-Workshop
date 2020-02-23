package de.ansgarsachs.patientmonolith;

import de.ansgarsachs.patientmonolith.control.PatientRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PatientMonolithApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientMonolithApplication.class, args);
    }

    @Bean(name = "patientregistry")
    public PatientRegistry customerAccount(){
        return new PatientRegistry();
    }

}
