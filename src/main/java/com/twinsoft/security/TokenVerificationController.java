/**
 *
 */
package com.twinsoft.security;

import java.security.Principal;

import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A dummy implementation to verify if the user data (embedded in the token) is valid.
 * The sent data is actually the use info embedded in the token, and it is just returned.
 * For a production environment the /verify_token oauth enpoint should be used
 *
 * @author Zolt Egete z.egete@levi9.com
 * @since Aug 17, 2015
 */
@RestController
@EnableResourceServer
public class TokenVerificationController {
    @RequestMapping("/userinfo")
    public Principal verifyUserToken(final Principal userToken) {
        return userToken;
    }
}
