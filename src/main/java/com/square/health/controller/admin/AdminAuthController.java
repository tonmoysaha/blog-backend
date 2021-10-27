package com.square.health.controller.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.square.health.dto.AdminDto;
import com.square.health.dto.AdminLoginDto;
import com.square.health.jwt.JwtTokenUtil;
import com.square.health.service.AdminService;
import com.square.health.service.impl.AdminUserDetailService;
import com.square.health.util.Utility;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminAuthController {

    @Autowired
    private AdminUserDetailService userDetailsService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private Utility utility;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @CrossOrigin
    @PostMapping("/signIn")
    public ResponseEntity<?> signIn(HttpServletRequest httpServletRequest,
                                         @Valid @RequestBody AdminLoginDto requestBodyDto, Errors errors) throws JsonProcessingException, JSONException {

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(requestBodyDto.getAdminEmail());
        String token = null;
        if (userDetails != null) {
            authenticate(requestBodyDto.getAdminEmail(), requestBodyDto.getAdminPassword());
            token = jwtTokenUtil.generateTokenForAdmin(userDetails);
        }
        Map<Object, Object> model = new HashMap<>();
        model.put("token", token);
        return ResponseEntity.ok(model);
    }


    @CrossOrigin
    @PostMapping("/create")
    public JsonNode createAdmin(HttpServletRequest httpServletRequest,
                                @Valid @RequestBody AdminDto requestBodyDto, Errors errors) throws JsonProcessingException, JSONException {
        if (errors.hasFieldErrors()) {
            List<String> collect = errors.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
            JSONObject missing_field = utility.createResponse(401, "Missing field", collect.toString());
            return objectMapper.readTree(missing_field.toString());
        }
        JSONObject admin = this.adminService.createAdmin(httpServletRequest, requestBodyDto);
        return objectMapper.readTree(admin.toString());
    }

    private void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new BadCredentialsException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("INVALID_CREDENTIALS", e);
        }
    }

    @CrossOrigin
    @GetMapping("/all")
    public ResponseEntity<List<AdminDto>> getAllAdmin(HttpServletRequest httpServletRequest) throws JsonProcessingException, JSONException {
        List<AdminDto> post = this.adminService.getAllAdmin(httpServletRequest);
        return  ResponseEntity.ok(post);
    }
}
