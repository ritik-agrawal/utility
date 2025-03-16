package rklab.utility.services;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public interface JwtConfiguration {

    String getSecretKey();

    Date getExpiration();

}
