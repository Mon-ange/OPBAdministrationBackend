package ca.openbox.shift.service;

import org.springframework.stereotype.Service;

@Service
public interface WorkRateService {
    double getRate();

    void setRate(double rate);
}
