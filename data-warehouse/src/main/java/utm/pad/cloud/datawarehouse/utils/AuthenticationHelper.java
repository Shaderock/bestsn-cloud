package utm.pad.cloud.datawarehouse.utils;

import utm.pad.cloud.datawarehouse.models.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationHelper {
    public static User getAuthenticatedUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
    }
}
