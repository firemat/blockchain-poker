package fr.insa.drim.schieder.etherdemo.animal;

import java.util.Scanner;

/**
 * Demo program to deposit and call a smart contract. The used contract simply returns the received input string. Geth
 * must powered up and mining, first:
 geth --datadir="elephantchain" --rpcapi personal,db,eth,net,web3 --rpc --nodiscover --mine --minerthreads=4 console
 [*] runs the silent miner for
 * an uninvolved third account. [*] operates on the elephant chain
 */
public class Launcher {

    public static void main(String[] args) throws Exception {

        System.out.println("Trying to deposit a smart contract in the blockchain using java and web3j...");

        AnimalDeployer deployer = new AnimalDeployer();
        Broutille broutille = deployer.transferContract();

        System.out.println("Contract transmitted to blockchain -> Broutille is alive and ready for interaction.");

        // Block program until enter key was hit.
        Scanner scanner = new Scanner(System.in);
        System.out.println("[Press enter to caress Broutille]");
        scanner.nextLine();

        // Caress the animal and listen to reply
        String reaction = broutille.caress().send();
        System.out.println("Caressed the cat and got reaction: " + reaction);

        // Block program until a key was hit.
        System.out.println("[Press enter to offer a yoghurt and caress Broutille again]");
        scanner.nextLine();

        // Offer a yoghurt to the animal
        broutille.offerYoghurt().send();
        reaction = broutille.caress().send();
        System.out.println("Offered a virtual yoghurt, caressed and got reaction: " + reaction);

        // Block program until a key was hit.
        System.out.println("[Press enter to offer another yoghurt and caress Broutille again]");
        scanner.nextLine();

        // Offer a yoghurt to the animal
        broutille.offerYoghurt().send();
        reaction = broutille.caress().send();
        System.out.println("Offered a virtual yoghurt, caressed and got reaction: " + reaction);

        // Block program until a key was hit.
        System.out.println("[Press enter to offer another yoghurt and caress Broutille again]");
        scanner.nextLine();

        // Offer a yoghurt to the animal
        broutille.offerYoghurt().send();
        reaction = broutille.caress().send();
        System.out.println("Offered a virtual yoghurt, caressed and got reaction: " + reaction);

        // Block program until a key was hit.
        System.out.println("[Press enter to kill the cat]");
        scanner.nextLine();

        broutille.kill().send();
        System.out.println("The cat is dead :-(");
    }
}
