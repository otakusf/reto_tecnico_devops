package com.tcs.retotecnico.services;

import com.tcs.retotecnico.models.Dto.RequestDto;
import org.springframework.stereotype.Service;

@Service
public class DevOpsService implements IDevOpsService {
    @Override
    public String getDevOpsInfo(RequestDto request) {
        return String.format("Hello %s, your message will be send", request.getTo());
    }
}
