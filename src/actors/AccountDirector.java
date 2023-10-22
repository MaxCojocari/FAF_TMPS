package actors;

import actors.builders.AccountBuilder;
import cryptography.HashGenerator;

public class AccountDirector {
    public void constructExternallyOwnedAccount(AccountBuilder builder) {
        builder.setAddress(HashGenerator.getRandomAddress(40));
        builder.setPrivateKey(HashGenerator.getRandomAddress(64));
        builder.setPublicKey(HashGenerator.getRandomAddress(64));
    }

    public void constructContractAccount(AccountBuilder builder) {
        builder.setAddress(HashGenerator.getRandomAddress(40));
        builder.setPrivateKey(HashGenerator.getRandomAddress(60));
        builder.setPublicKey(HashGenerator.getRandomAddress(60));
    }
}
