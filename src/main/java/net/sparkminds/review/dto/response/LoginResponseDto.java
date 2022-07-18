package net.sparkminds.review.dto.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private String token;
}
