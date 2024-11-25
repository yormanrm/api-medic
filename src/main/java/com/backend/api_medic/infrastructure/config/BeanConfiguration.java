package com.backend.api_medic.infrastructure.config;

import com.backend.api_medic.application.*;
import com.backend.api_medic.domain.ports.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public PatientService patientService(IPatientRepository iPatientRepository) {
        return new PatientService(iPatientRepository);
    }

    @Bean
    public DoctorService doctorService(IDoctorRepository iDoctorRepository) {
        return new DoctorService(iDoctorRepository);
    }

    @Bean
    public AppointmentService appointmentService(IAppointmentRepository iAppointmentRepository) {
        return new AppointmentService(iAppointmentRepository);
    }

    @Bean
    public AppointmentDetailsService appointmentDetailsService(IAppointmentDetailsRepository iAppointmentDetailsRepository) {
        return new AppointmentDetailsService(iAppointmentDetailsRepository);
    }

    @Bean
    public CredentialService credentialService(ICredentialRepository iCredentialRepository) {
        return new CredentialService(iCredentialRepository);
    }
}