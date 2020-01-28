pragma solidity ^0.4.17;

contract poker {
    enum State {UNCERTAIN, VALID, ALLIN, FOLD}
    string[52] deck = ['p7', 'h6', 't14', 'h12', 'c10', 'p9', 'c3', 't2', 'p11', 'c14', 'c4', 'h5', 'p12', 'c2', 'h13', 't5', 't13', 'p2', 'c5', 'h2', 'h10', 'h7','p4', 'c12', 'h8', 'c7', 'p14', 'c8', 't8', 't11', 't12', 'h14', 'h4','c11', 't4', 'c6', 'p10', 't9', 'p6', 't3', 't6', 'p13', 'c9', 'h11', 'p5', 't10', 'p8', 'h3', 't7', 'p3', 'h9', 'c13'];
    uint8 deck_i = 0;

    struct Player {
        string[2] hand;
        uint16 tokens;
        State playerState;
        address id;
    }

    uint8 constant NB_PLAYER = 2;
    Player[] players;
    uint128 constant FEE = 10**3;
    uint128 gain = 0; 

    function join() public payable{
        require (msg.value == FEE);
        if(players.length < NB_PLAYER) {
            string[2] memory hand = [deck[deck_i++], deck[deck_i++]];
            players.push(Player(hand, 100, State.UNCERTAIN, msg.sender));
            gain += FEE;
        }
    }

    /*function quit() public {
        
    }*/

    function get() public view returns (uint128) {
        return gain;
    }

    /*constructor() public {
        hand = ["", ""];
        tokens = 100;
        playerState = State.UNCERTAIN;

        uint i;
        uint j;
        uint pos = 0;
        string[4] type = ["c", "t", "h", "p"]
        for(i=0; i<type.length; i++) {
            for(j=2; j<15; i++) {
                deck[pos] = type[i] + j;
                pos++;
            }
        }
    }*/
}

