package net.cofares.demo_security.controler;

import java.security.Principal;
import javax.annotation.security.RolesAllowed;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Acer
 */
@RestController
public class LoginControler {
    
    @RequestMapping("/*")
    @RolesAllowed("USER")
    public String getUser(Principal principal) {
        return "Bonjour utilisateur " + principal.getName();
    }
    
    @RequestMapping("/admin")
    @RolesAllowed("ADMIN")
    public String getAdmin(Principal principal) {
        return "Bonjour Admin " + principal.getName() ;
    }
    
    
}
