package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientRequestDto;
import com.pm.patientservice.dto.PatientResponseDto;
import com.pm.patientservice.dto.mapper.PatientMapper;
import com.pm.patientservice.exception.EmailAlreadyExistsException;
import com.pm.patientservice.exception.PatientNotFoundException;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDto> getPatients(){
        List<Patient> patients = patientRepository.findAll();
        return patients.stream()
                .map(PatientMapper::toPatientResponseDto).toList();
    }

    public PatientResponseDto getPatient(UUID id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        return PatientMapper.toPatientResponseDto(patient);
    }

    public PatientResponseDto createPatient(PatientRequestDto patientRequestDto){
        if(patientRepository.existsByEmail(patientRequestDto.getEmail())){
            throw new EmailAlreadyExistsException("Patient email already exists: " + patientRequestDto.getEmail());
        }
        Patient newPatient = patientRepository.save(PatientMapper.toModel(patientRequestDto));
        return PatientMapper.toPatientResponseDto(newPatient);
    }

    public PatientResponseDto updatePatient(UUID id,PatientRequestDto patientRequestDto){
        Patient patient = patientRepository.findById(id).orElseThrow(
                () -> new PatientNotFoundException("Patient not found with id " + id));
        if(patientRepository.existsByEmailAndIdNot(patientRequestDto.getEmail(),id)){
            throw new EmailAlreadyExistsException("Patient email already exists: " + patientRequestDto.getEmail());
        }
        patient.setPatientName(patientRequestDto.getName());
        patient.setPatientAddress(patientRequestDto.getAddress());
        patient.setPatientEmail(patientRequestDto.getEmail());
        patient.setPatientDateOfBirth(LocalDate.parse(patientRequestDto.getDateOfBirth()));

        Patient updatedPatient = patientRepository.save(patient);
        return PatientMapper.toPatientResponseDto(updatedPatient);
    }

    public void deletePatient(UUID id){
        patientRepository.deleteById(id);
    }
}
