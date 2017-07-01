///**
// * 
// */
//package com.twinsoft.security;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import org.springframework.security.oauth2.provider.OAuth2Authentication;
//import org.springframework.security.oauth2.provider.token.TokenEnhancer;
//
///**
// * @author Miodrag Pavkovic
// */
//public class CustomTokenEnhancer implements TokenEnhancer {
//	
//	private static final String TENANT_ID = "tenant_id";
//    private static final int TENANT_SYSTEM_ID = 1;
//	/* (non-Javadoc)
//	 * @see org.springframework.security.oauth2.provider.token.TokenEnhancer#enhance(org.springframework.security.oauth2.common.OAuth2AccessToken, org.springframework.security.oauth2.provider.OAuth2Authentication)
//	 */
//	  @Override
//	    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
//	        final Map<String, Object> additionalInfo = new HashMap<>(accessToken.getAdditionalInformation());
//	        // Adds the hard coded tenant ID 1 to the JWT access token
//	        additionalInfo.put(TENANT_ID, authentication.getName() + TENANT_SYSTEM_ID);
//	        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
//	        return accessToken;
//	    }
////	  @Override public OAuth2AccessToken enhance(final OAuth2AccessToken accessToken, final OAuth2Authentication authentication) {
////	        final Map<String, Object> additionalInformation = new HashMap<>(accessToken.getAdditionalInformation());
////	        additionalInformation.put(TENANT_ID, TENANT_SYSTEM_ID);
////	        final DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(accessToken);
////	        customAccessToken.setAdditionalInformation(additionalInformation);
////
////	        return super.enhance(customAccessToken, authentication);
////	    }
//
//}
