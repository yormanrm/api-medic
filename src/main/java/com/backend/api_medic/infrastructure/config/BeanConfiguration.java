package com.backend.api_medic.infrastructure.config;

import com.backend.api_medic.application.AppointmentDetailsService;
import com.backend.api_medic.application.AppointmentService;
import com.backend.api_medic.application.DoctorService;
import com.backend.api_medic.application.PatientService;
import com.backend.api_medic.domain.ports.IAppointmentDetailsRepository;
import com.backend.api_medic.domain.ports.IAppointmentRepository;
import com.backend.api_medic.domain.ports.IDoctorRepository;
import com.backend.api_medic.domain.ports.IPatientRepository;
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
}