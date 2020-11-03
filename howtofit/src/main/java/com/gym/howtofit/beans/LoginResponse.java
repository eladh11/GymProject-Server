package com.gym.howtofit.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginResponse {
	private String token;
	private String type;
}
