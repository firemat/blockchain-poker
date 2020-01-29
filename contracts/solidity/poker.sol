pragma solidity ^0.4.17;

contract poker {
    enum State {UNCERTAIN, VALID, ALLIN, FOLD}
    string[52] deck = ['p7', 'h6', 't14', 'h12', 'c10', 'p9', 'c3', 't2', 'p11', 'c14', 'c4', 'h5', 'p12', 'c2', 'h13', 't5', 't13', 'p2', 'c5', 'h2', 'h10', 'h7','p4', 'c12', 'h8', 'c7', 'p14', 'c8', 't8', 't11', 't12', 'h14', 'h4','c11', 't4', 'c6', 'p10', 't9', 'p6', 't3', 't6', 'p13', 'c9', 'h11', 'p5', 't10', 'p8', 'h3', 't7', 'p3', 'h9', 'c13'];
    uint8 deck_i = 0;
    uint8 current_turn = 1;
    uint16 tokens_pot = 0;

    struct Player {
        string[2] hand;
        uint16 tokens;
        State playerState;
        address id;
    }

    uint8 constant NB_PLAYER = 2;
    Player[] players;
    mapping (address => uint256) ad2Index;
    uint128 constant FEE = 10**3; // change to 10**18
    uint128 gain = 0; 

    function join() public payable{
        if(players.length < NB_PLAYER) {
            require (msg.value == FEE);
            ad2Index[msg.sender] = players.length;
            string[2] memory hand = [deck[deck_i++], deck[deck_i++]];
            players.push(Player(hand, 100, State.UNCERTAIN, msg.sender));
            gain += FEE;
        }
    }

    /*function quit() public {
        
    }*/

    function kickPlayer(address id) private {

    }

    function get() public view returns (uint128) {
        return gain;
    }

    function victoryGame() public {
        if(players.length < 2) {
            players[0].id.transfer(gain*95/100); // What if all player quit ?
            kickPlayer(players[0].id);
        }
    }

    function getToken() public view returns (uint16) {
        return players[ad2Index[msg.sender]].tokens;
    }

    function getWinningPlayer() public view returns (uint) {
        //TODO get winner
        return ad2Index[msg.sender];
    }

    function distributeGains() public returns (bool) {
        uint idWinner = getWinningPlayer();
        players[idWinner].tokens = tokens_pot;
        tokens_pot = 0;
        return true;
    }

    function victoryRound() public returns (uint8) {
        //Il faut avoir déjà mis les jetons dans le pot
        distributeGains();      //Distribuer les gains
        return ++current_turn;
    }
}

