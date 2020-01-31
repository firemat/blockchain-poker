package fr.insa.drim.schieder.etherdemo.poker;


import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigInteger;
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
        System.out.println("[J1 - Press enter to join a table (enter fee: 1 ETH)]");
        scanner.nextLine();

        // Join the game and listen to reply
        TransactionReceipt reaction = poker.join(new BigInteger("1")).send();
        if(reaction.equals("0")){
            System.out.println("You cannot join the game, there is too many players !");
            return;
        }
        System.out.println(String.format("Your table has now %s players!",reaction));
        //TODO check how to change player address
        System.out.println("[J2 - Press enter to join a table (enter fee: 1 ETH)]");
        scanner.nextLine();

        reaction = poker.join(new BigInteger("1")).send();
        if(reaction.equals("0")){
            System.out.println("You cannot join the game, there is too many players !");
            return;
        }
        System.out.println(String.format("Your table has now %s players!",reaction));

        System.out.println("Start the game ?");
        scanner.nextLine();
        //Tour 1
        System.out.println("[Everyone - Turn is beginning]");

        //TODO generify this to use a while loop
        System.out.println("[J1 - Your turn]");
        System.out.println("[J1 - You want to wager how many tokens ?]");
        int token = Integer.parseInt(scanner.nextLine());
        reaction = poker.play(new BigInteger(String.valueOf(token))).send();


        System.out.println("[J2 - Your turn]");
        System.out.println("[J2 - You want to wager how many tokens ?]");
        token = Integer.parseInt(scanner.nextLine());
        reaction = poker.play(new BigInteger(String.valueOf(token))).send();

        System.out.println("[Everyone - Turn 1 is over]");
        poker.victoryRound().send();
        BigInteger nbJeton = poker.getToken().send();
        System.out.println(String.format("[J1 - You have now %s tokens!]",nbJeton.toString()));
        nbJeton = poker.getToken().send();
        System.out.println(String.format("[J2 - You have now %s tokens!]",nbJeton.toString()));
        System.out.println("[Everyone - Turn 2 is beginning]");
        //TODO Turn 2
        System.out.println("[Everyone - Turn 2 is over]");
        poker.victoryRound().send();
        System.out.println("[Everyone - Gains will be distributed]");
        poker.victoryGame().send();
        //TODO implement new round
    }
}
