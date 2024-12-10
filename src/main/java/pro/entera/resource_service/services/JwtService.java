package pro.entera.resource_service.services;

public interface JwtService {

    String extractUserName(String token);

    boolean isExpiredToken(String token);
}
