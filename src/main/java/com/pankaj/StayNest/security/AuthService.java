package com.pankaj.StayNest.security;


import com.pankaj.StayNest.dto.LoginRequestDto;
import com.pankaj.StayNest.dto.LoginResponseDto;
import com.pankaj.StayNest.dto.SignUpReqestDto;
import com.pankaj.StayNest.dto.SignUpResponseDto;
import com.pankaj.StayNest.entity.Guest;
import com.pankaj.StayNest.entity.Host;
import com.pankaj.StayNest.entity.User;
import com.pankaj.StayNest.entity.type.Authprovidertype;
import com.pankaj.StayNest.entity.type.RoleType;
import com.pankaj.StayNest.repository.GuestRepository;
import com.pankaj.StayNest.repository.HostRepository;
import com.pankaj.StayNest.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final Authprovidertype authprovidertype;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final HostRepository hostRepository;
  private final GuestRepository guestRepository;
  private final AuthUtil authUtil;


  public LoginResponseDto login(LoginRequestDto loginRequestDto){
      Authentication authentication= authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(),loginRequestDto.getPassword())
      );
      User user= (User) authentication.getPrincipal();
      String accessToken= authUtil.genrateAccesstoken(user);
      return new LoginResponseDto(accessToken,user.getId());
  }


    public SignUpResponseDto signup(SignUpReqestDto signUpReqestDto){
        User user= signUpInternal(signUpReqestDto,authprovidertype.EMAIL,null);
        return modelMapper.map(user,SignUpResponseDto.class);
    }


    @Transactional
    public User signUpInternal(SignUpReqestDto dto, Authprovidertype type, String providerId) {

        if (userRepository.findByUsernam(dto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("User already exists");
        }

        User user = User.builder()
                .providerId(providerId)
                .providertype(type)
                .username(dto.getUsername())
                .role(dto.getRoles())
                .build();

        if (type == Authprovidertype.EMAIL) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        user = userRepository.save(user);

        for (RoleType role : dto.getRoles()) {

            if (role == RoleType.GUEST) {
                Guest guest = Guest.builder()
                        .user(user)
                        .email(dto.getEmail())
                        .build();
                guestRepository.save(guest);
            }

            if (role == RoleType.HOST) {
                Host host = Host.builder()
                        .user(user)
                        .email(dto.getEmail())
                        .payoutAccountNumber(dto.getPayoutAccountNumber())
                        .build();
                hostRepository.save(host);
            }
        }

        return user;
    }


    public ResponseEntity<LoginResponseDto> oauthlogin(OAuth2User oAuth2User,String registrationId){
        Authprovidertype providerType = authUtil.getAuthProviderTypeById(registrationId);
        String providerId = authUtil.getProvidedIdByOAuth2User(oAuth2User, registrationId);

        User user = userRepository.findByProviderIdAndProviderType(providerId, providerType).orElse(null);
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        User emailUser = userRepository.findByUsernam(email).orElse(null);

        if(user == null && emailUser == null) {
            // signup flow:
            String username = authUtil.determineUsernameFromOAuth2User(oAuth2User, registrationId, providerId);
            user =signUpInternal(new SignUpReqestDto(username, null, name, email,null,Set.of(RoleType.GUEST)),
                    authprovidertype, providerId);
        } else if(user != null) {
            if(email != null && !email.isBlank() && !email.equals(user.getUsername())) {
                user.setUsername(email);
                userRepository.save(user);
            }
        } else {
            throw new BadCredentialsException("This email is already registered with provider "+emailUser.getProvidertype());
        }

        LoginResponseDto loginResponseDto = new LoginResponseDto(authUtil.genrateAccesstoken(user), user.getId());
        return ResponseEntity.ok(loginResponseDto);
    }

    }
}
