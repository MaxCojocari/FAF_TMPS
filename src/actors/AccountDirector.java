package actors;

import actors.builders.AccountBuilder;
import cryptography.HashGenerator;

public class AccountDirector {
    public void constructExternallyOwnerAccount(AccountBuilder builder) {
        builder.setAddress(HashGenerator.getRandomAddress(40));
        builder.setBalanceETH(2.33);
        builder.setBalanceUSDT(10.3333);
        builder.setPrivateKey("0x72d51c039a1a67556db8b2d8a723ad8e9835554f5e63b69eba25251d231ed595");
        builder.setPublicKey("0xfbb5f4b56ff8c67b9c16bb67b69d6ab6378c3b6b5f10d5f3f5507f4f7d349f94");
    }

    public void constructContractAccount(AccountBuilder builder) {
        builder.setAddress(HashGenerator.getRandomAddress(40));
        builder.setBalanceETH(2.33);
        builder.setPrivateKey("0xd74e52311f877638b8661d1d7989e17645a1d2c367f13ad0fab61b262e1d158c");
        builder.setPublicKey("0x04839d49c3d1a73e3b64fa34eb0532632ad14b87f8a4ef8a3199c3719bb1cb2f");
    }
}
