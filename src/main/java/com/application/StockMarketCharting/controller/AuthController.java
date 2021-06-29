package com.application.StockMarketCharting.controller;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.application.StockMarketCharting.dao.RoleRepository;
import com.application.StockMarketCharting.dao.UserRepository;
import com.application.StockMarketCharting.models.ERole;
import com.application.StockMarketCharting.models.Role;
import com.application.StockMarketCharting.models.User;
import com.application.StockMarketCharting.payload.request.LoginRequest;
import com.application.StockMarketCharting.payload.request.SignupRequest;
import com.application.StockMarketCharting.payload.response.JwtResponse;
import com.application.StockMarketCharting.payload.response.MessageResponse;
import com.application.StockMarketCharting.security.services.UserDetailsImpl;
import com.application.StockMarketCharting.security.services.jwt.JwtUtils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		User user =new User();
		user=userRepository.getById(userDetails.getId());
		if(user.isConfirmed())
		{
		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
		}
		return ResponseEntity.badRequest().body(new MessageResponse("Error: Email Account not confirmed!"));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) throws AddressException, MessagingException {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);
		HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "AuthController");
        sendemail(user.getId(),user.getEmail()) ;
        return ResponseEntity.accepted().headers(headers).body(new MessageResponse("Confirm Email Account!"));
	}
	
   



		public void sendemail(Long id,String email) throws AddressException, MessagingException {
	 
	        final String username = "khurana0699@gmail.com";
	        final String password = "password";

	 		Properties prop = new Properties();

	        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	        prop.put("mail.smtp.host", "smtp.gmail.com");
	        prop.put("mail.smtp.port", "587");
	        prop.put("mail.smtp.auth", "true");
	        prop.put("mail.smtp.starttls.enable", "true"); //TLS
	        
	        Session session = Session.getInstance(prop,
	                new javax.mail.Authenticator() {
	                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
	                        return new javax.mail.PasswordAuthentication(username, password);
	                    }
	                });

	        try {

	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress("khurana0699@gmail.com"));
	            message.setRecipients(
	                    Message.RecipientType.TO,
	                    InternetAddress.parse(email)
	            );
	            message.setSubject("User confirmation email");
	      
	            message.setContent(
	                    "<h1><a href =\"http://127.0.0.1:8080/api/auth/confirmuser/"+id+"/\"> Click to confirm </a></h1>",
	                   "text/html");
	            Transport.send(message);

	            System.out.println("sent");

	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	    }


		@RequestMapping(value="/confirmuser/{userid}", method=RequestMethod.GET)
		public String welcomepage(@PathVariable Long userid) {
			
			Optional<User> userlist=userRepository.findById(userid);
			
			User user =new User();
			user=userlist==null?null:userRepository.getById(userid);
			
			if(user!=null)
			{
				user.setConfirmed(true);
				userRepository.save(user);
				return "User confirmed "+user.getUsername();
			}
			return null;
		}
				

	
}