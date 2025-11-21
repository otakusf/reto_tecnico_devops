package com.tcs.retotecnico.controller;

import com.auth0.jwt.JWTCreator;
import com.tcs.retotecnico.jwt.OneTimeTokenService;
import com.tcs.retotecnico.models.Dto.RequestDto;
import com.tcs.retotecnico.models.Dto.ResponseDto;
import com.tcs.retotecnico.services.IDevOpsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jwt")
public class JwtController {

    private final OneTimeTokenService oneTimeTokenService;

    public JwtController(OneTimeTokenService oneTimeTokenService) {
        this.oneTimeTokenService = oneTimeTokenService;
    }

    @PostMapping
    public ResponseDto jwt() {

        return ResponseDto
                .builder()
                .message(oneTimeTokenService.generateToken(30000L))
                .build();
    }
}
