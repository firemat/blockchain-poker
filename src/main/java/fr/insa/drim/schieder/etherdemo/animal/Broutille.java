package fr.insa.drim.schieder.etherdemo.animal;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.5.0.
 */
public class Broutille extends Contract {
    private static final String BINARY = "608060405260008054600160a060020a03191633179055610248806100256000396000f3006080604052600436106100565763ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166301288f3c811461005b57806341c0e1b5146100e557806362cfc8a0146100fc575b600080fd5b34801561006757600080fd5b50610070610111565b6040805160208082528351818301528351919283929083019185019080838360005b838110156100aa578181015183820152602001610092565b50505050905090810190601f1680156100d75780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b3480156100f157600080fd5b506100fa6101d5565b005b34801561010857600080fd5b506100fa610212565b6060600180541015610157575060408051808201909152600b81527f486d6d6d7250757272213f00000000000000000000000000000000000000000060208201526101d2565b6003600154101561019c575060408051808201909152601581527f4d696175486d6d6d72727221203c33203c33203c33000000000000000000000060208201526101d2565b5060408051808201909152600c81527f424555524b212121203a2d58000000000000000000000000000000000000000060208201525b90565b60005473ffffffffffffffffffffffffffffffffffffffff163314156102105760005473ffffffffffffffffffffffffffffffffffffffff16ff5b565b60018054810190555600a165627a7a723058208e88c96ad3cf1978de70579ec332c90b6b2af8886f0d59c8d16a6b5c7cca2f9c0029";

    public static final String FUNC_CARESS = "caress";

    public static final String FUNC_KILL = "kill";

    public static final String FUNC_OFFERYOGHURT = "offerYoghurt";

    protected Broutille(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Broutille(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<String> caress() {
        final Function function = new Function(FUNC_CARESS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> kill() {
        final Function function = new Function(
                FUNC_KILL, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> offerYoghurt() {
        final Function function = new Function(
                FUNC_OFFERYOGHURT, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<Broutille> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Broutille.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Broutille> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Broutille.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static Broutille load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Broutille(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Broutille load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Broutille(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
