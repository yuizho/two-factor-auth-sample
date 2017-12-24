/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.yuizho.twofactorauth;

import com.warrenstrange.googleauth.ICredentialRepository;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author yuizho
 */
public class MyCredentialRepository implements ICredentialRepository {

    /**
     * This method retrieves the Base32-encoded private key of the given user.
     *
     * @param userName the user whose private key shall be retrieved.
     * @return the private key of the specified user.
     */
    @Override
    public String getSecretKey(String userName) {
        return CredentialsHolder.INSTANCE.getCredential(userName);
    }

    /**
     * This method saves the user credentials.
     *
     * @param userName the user whose data shall be saved.
     * @param secretKey the generated key.
     * @param validationCode the validation code.
     * @param scratchCodes the list of scratch codes.
     */
    @Override
    public void saveUserCredentials(String userName,
            String secretKey,
            int validationCode,
            List<Integer> scratchCodes) {
        CredentialsHolder.INSTANCE.putCredential(userName, secretKey);
    }

    enum CredentialsHolder {
        INSTANCE;
        private final Map<String, String> credentialMap = new ConcurrentHashMap<>();

        public String getCredential(String userName) {
            System.out.println(credentialMap);
            return credentialMap.get(userName);
        }

        public void putCredential(String userName, String secretKey) {
            credentialMap.put(userName, secretKey);
            System.out.println(credentialMap);
        }
    }
}
