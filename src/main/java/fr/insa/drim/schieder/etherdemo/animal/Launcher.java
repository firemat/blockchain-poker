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

        PokerDeployer deployer = new PokerDeployer();
        Poker poker = deployer.transferContract();

        System.out.println("Contract transmitted to blockchain -> Game ready for interaction.");

        // Block program until enter key was hit.
        Scanner scanner = new Scanner(System.in);
        System.out.println("[Press enter to join a table (enter fee: 1 ETH)]");
        scanner.nextLine();

        // Join the game and listen to reply
        String reaction = poker.join().send();
        if(reaction.equals("0")){
            System.out.println("You cannot join the game, there is too many players !");
            return;
        }
        System.out.println(String.format("Your table has now %s players!",reaction));

        //TODO implemet J2
        //TODO handle quit, kickPlayer, getToken, victoryGame, distributeGains, victoryRound
    }
}
