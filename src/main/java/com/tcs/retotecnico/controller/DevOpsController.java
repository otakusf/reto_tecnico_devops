package com.tcs.retotecnico.controller;

import com.tcs.retotecnico.models.Dto.RequestDto;
import com.tcs.retotecnico.models.Dto.ResponseDto;
import com.tcs.retotecnico.services.IDevOpsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/DevOps")
public class DevOpsController {

    private final IDevOpsService iDevOpsService;

    public DevOpsController(IDevOpsService iDevOpsService) {
        this.iDevOpsService = iDevOpsService;
    }

    @PostMapping
    public ResponseDto devOps(
            @RequestBody RequestDto request) {

        return ResponseDto
                .builder()
                .message(iDevOpsService.getDevOpsInfo(request))
                .build();
    }
}
