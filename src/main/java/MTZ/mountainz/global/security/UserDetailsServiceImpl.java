package MTZ.mountainz.global.security;

import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import MTZ.mountainz.domain.member.entity.Member;
import MTZ.mountainz.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Member member = memberRepository.findByEmail(email).orElseThrow(
			() -> new RuntimeException("Not Found Account")
		);

		// Member member = memberRepository.findByEmail(email).orElse(new Member("guest@naver.com"));

		// UserDetailsImpl userDetails = new UserDetailsImpl();
		// userDetails.setMember(member);

		// return userDetails;
		return createUserDetails(member);
	}

	private UserDetails createUserDetails(MTZ.mountainz.domain.member.entity.Member member) {
		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("member");

		return new User(
			member.getEmail(),
			member.getPassword(),
			Collections.singleton(grantedAuthority)
		);
	}
}
