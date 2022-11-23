package MTZ.mountainz.global.nginx;

import java.util.Arrays;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class NginxController {
	private final String HEALTH = "up";

	private final Environment env;

	@GetMapping("/api/nginx/profile")
	public String getProfile() {
		return Arrays.stream(env.getActiveProfiles()).findFirst().orElse("");
	}

	@GetMapping("/api/nginx/health")
	public String getHealth() {
		return HEALTH;
	}
}
