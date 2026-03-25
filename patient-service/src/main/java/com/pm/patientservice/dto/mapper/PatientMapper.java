package com.pm.patientservice.dto.mapper;

import com.pm.patientservice.dto.PatientResponseDto;
import com.pm.patientservice.model.Patient;

public class PatientMapper {
    public static PatientResponseDto toPatientResponseDto(Patient patient) {
        PatientResponseDto patientResponseDto = new PatientResponseDto();
        patientResponseDto.setId(patient.getPatientId().toString());
        patientResponseDto.setName(patient.getPatientName().toString());
        patientResponseDto.setAddress(patient.getPatientAddress().toString());
        patientResponseDto.setDateOfBirth(patient.getPatientDateOfBirth().toString());
        return patientResponseDto;
    }
}
