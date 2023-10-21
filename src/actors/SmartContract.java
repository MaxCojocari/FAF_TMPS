package actors;

import actors.accounts.Account;
import cryptography.HashGenerator;

public class SmartContract implements ISmartContract {
    private Account deployer;
    private String abi;
    private String compiledBytecode;
    private String deployedBytecode;
    private String network;
    private String inputData;

    public SmartContract(Account deployer, String abi, String compiledBytecode, String network, String inputData) {
        this.deployer = deployer;
        this.abi = abi;
        this.compiledBytecode = compiledBytecode;
        this.network = network;
        this.inputData = inputData;
    }

    public String getDeployInfo() {
        String s = "Smart-Contract Deployment Info \n";
        s += "Deployer:\t\t" + deployer.getAddress() + "\n";
        s += "Bytecode:\t\t" + deployedBytecode + "\n";
        return s;
    }

    public String deploy() {
        String payload = deployer + abi + compiledBytecode;
        payload += HashGenerator.computeSha256Hash(payload) + inputData;
        deployedBytecode = HashGenerator.computeSha256Hash(payload);
        return deployedBytecode;
    }

    public String getDeployer() {
        return deployer.getAddress();
    }

    public String getAbi() {
        return abi;
    }

    public String getBytecode() {
        return deployedBytecode;
    }

    public String getNetwork() {
        return network;
    }

    public String getInputData() {
        return inputData;
    }

}
