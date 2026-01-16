package com.skyview.scheduler.service;

import com.skyview.scheduler.dto.RegisterRequest;
import com.skyview.scheduler.model.*;
import com.skyview.scheduler.repository.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final StaffRepository staffRepository;
    private final AccountRepository accountRepository;
    private final RegistrationCodeRepository registrationCodeRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthService(
            StaffRepository staffRepository,
            AccountRepository accountRepository,
            RegistrationCodeRepository registrationCodeRepository
    ) {
        this.staffRepository = staffRepository;
        this.accountRepository = accountRepository;
        this.registrationCodeRepository = registrationCodeRepository;
    }

    public Staff registerWithCode(RegisterRequest req) {

        // 1) Basic validation
        if (req.inviteCode == null || req.inviteCode.isBlank()) {
            throw new IllegalArgumentException("Invite code is required");
        }
        if (req.username == null || req.username.isBlank()) {
            throw new IllegalArgumentException("Username is required");
        }
        if (req.password == null || req.password.isBlank()) {
            throw new IllegalArgumentException("Password is required");
        }

        // 2) Username must be unique
        if (accountRepository.findByUsername(req.username).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        // 3) Validate registration code
        RegistrationCode code = registrationCodeRepository
                .findByCodeValue(req.inviteCode)
                .orElseThrow(() -> new IllegalArgumentException("Invalid invite code"));

        if (!code.isValidNow()) {
            throw new IllegalArgumentException("Invite code is expired or already used");
        }

        // 4) Create Staff
        Staff staff = new Staff();
        staff.setFirstName(req.firstName);
        staff.setLastName(req.lastName);
        staff.setEmail(req.email);
        staff.setPhoneNumber(req.phoneNumber);
        if (req.age != null) staff.setAge(req.age);

        staff = staffRepository.save(staff);

        // 5) Create Account linked to Staff
        Account account = new Account();
        account.setUsername(req.username);
        account.setPasswordHash(encoder.encode(req.password));
        account.setStatus(AccountStatus.ACTIVE);
        account.setStaff(staff);

        accountRepository.save(account);

        // 6) Mark code as used
        code.setUsed(true);
        code.setUsedBy(staff);
        registrationCodeRepository.save(code);

        return staff;
    }
}
