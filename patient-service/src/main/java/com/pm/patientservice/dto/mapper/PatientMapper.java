package com.pm.patientservice.dto.mapper;

import com.pm.patientservice.dto.PatientRequestDto;
import com.pm.patientservice.dto.PatientResponseDto;
import com.pm.patientservice.model.Patient;

import java.time.LocalDate;

public class PatientMapper {
    public static PatientResponseDto toPatientResponseDto(Patient patient) {
        PatientResponseDto patientResponseDto = new PatientResponseDto();
        patientResponseDto.setId(patient.getPatientId().toString());
        patientResponseDto.setEmail(patient.getPatientEmail().toString());
        patientResponseDto.setName(patient.getPatientName().toString());
        patientResponseDto.setAddress(patient.getPatientAddress().toString());
        patientResponseDto.setDateOfBirth(patient.getPatientDateOfBirth().toString());
        return patientResponseDto;
    }

    public static Patient toModel(PatientRequestDto patientRequestDto) {
        Patient patient = new Patient();
        patient.setPatientName(patientRequestDto.getName());
        patient.setPatientAddress(patientRequestDto.getAddress());
        patient.setPatientEmail(patientRequestDto.getEmail());
        patient.setPatientDateOfBirth(LocalDate.parse(patientRequestDto.getDateOfBirth()));
        patient.setRegisterDate(LocalDate.parse(patientRequestDto.getRegisteredDate()));
        return patient;
    }
}
