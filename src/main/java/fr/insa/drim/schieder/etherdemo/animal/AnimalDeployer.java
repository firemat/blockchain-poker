package fr.insa.drim.schieder.etherdemo.animal;


import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

/**
 * Demo program to deposit a helloworld smart contract in the blockchain using geth via web3j access.
 * Geth must powered up and mining, first:
 geth --datadir="elephantchain" --rpcapi personal,db,eth,net,web3 --rpc --nodiscover --mine --minerthreads=4 console
 * [*] runs the silent miner for an uninvolved third account.
 * [*] operates on the elephant chain
 */
public class AnimalDeployer
    {
        // Path to ethereum base dir (This account will be debited)
        private final String LOCATION_SOURCE_ACCOUNT = "/Users/maex/Library/Ethereum/elephantchain/keystore/UTC--2018-10-19T10-20-31.367861046Z--bfad57d3d231a3545537c8cff5bcc39f842ed65e";

        // Password of the source account
        private final String SOURCE_ACCOUNT_PASSWORD = "abc123";

        public Broutille transferContract () throws Exception {

        // Connect to local node
        Web3j web3 = Web3j.build(new HttpService());  // defaults to http://localhost:8545/

        // Load credentials for accessing wallet of source account
        Credentials credentials = WalletUtils.loadCredentials(SOURCE_ACCOUNT_PASSWORD, LOCATION_SOURCE_ACCOUNT);

        // Deploy the contract in the blockchain
        return Broutille.deploy(web3, credentials, DefaultGasProvider.GAS_PRICE, DefaultGasProvider.GAS_LIMIT).send();
    }
    }


