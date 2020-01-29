pragma solidity ^0.4.17;

contract poker {
    enum State {UNCERTAIN, VALID, ALLIN, FOLD}
    string[52] deck = ['p7', 'h6', 't14', 'h12', 'c10', 'p9', 'c3', 't2', 'p11', 'c14', 'c4', 'h5', 'p12', 'c2', 'h13', 't5', 't13', 'p2', 'c5', 'h2', 'h10', 'h7','p4', 'c12', 'h8', 'c7', 'p14', 'c8', 't8', 't11', 't12', 'h14', 'h4','c11', 't4', 'c6', 'p10', 't9', 'p6', 't3', 't6', 'p13', 'c9', 'h11', 'p5', 't10', 'p8', 'h3', 't7', 'p3', 'h9', 'c13'];
    uint8 deck_i = 0;
    uint8 current_turn = 1;
    uint16 tokens_pot = 0;
    uint16 call_value = 0;

    struct Player {
        string[2] hand;
        uint16 tokens;
        State playerState;
        address id;
        uint16 raise;
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
            players.push(Player(hand, 100, State.UNCERTAIN, msg.sender, 0));
            gain += FEE;
        }
    }

    function quit() public returns (address) {
        address id_quit;
        id_quit= msg.sender;
        kickPlayer(id_quit);
        return id_quit;        
    }

    function kickPlayer(address id_quit) private returns (Player[]){
        uint index;
        for(uint j=0; j<players.length-1; j++){
            if(players[j].id==id_quit){
                index=j;
            }
        }
        if(j >= players.length) return;
        for(uint i=index; i<players.length-1; i++) {
            players[i]=players[i+1];
        }

        delete players[players.length-1];
        players.length--;
        return players;
    }

    function play(uint16 amount) public {      
      Player memory p = players[ad2Index[msg.sender]];
      require(p.playerState==State.VALID);
      if(p.raise+amount > p.tokens) { // ALLIN
        tokens_pot += p.tokens;
        p.raise += p.tokens;
        p.tokens = 0;
        p.playerState = State.ALLIN;
      }
      else if(p.raise+amount < call_value) { // FOLD
        p.playerState = State.FOLD;
      }
      else if(p.raise+amount > call_value) { // RAISE
        for (uint i=0; i<players.length; i++) {
          if(players[i].playerState==State.VALID) {
            players[i].playerState==State.UNCERTAIN;
          }
        }
        p.raise+=amount;
        call_value = p.raise;        
        tokens_pot += amount;
        p.tokens -= amount;
        p.playerState = State.VALID;
      }
      else { // CALL
        p.raise+=amount;
        tokens_pot += amount;
        p.tokens -= amount;
        p.playerState = State.VALID;
      }  
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

    function compareHands(uint p1, uint p2) public view returns (bool) {
        //Get full hand
        string[] memory handPlayer1;
        string[] memory handPlayer2;
        uint pos = 0;
        for (uint a=0; a < players[p1].hand.length; a++) {
            handPlayer1[pos] = players[p1].hand[a];
            handPlayer2[pos] = players[p2].hand[a];
            pos++;
        }
        for (uint b=0; b < card_revealed.length; b++) {
            handPlayer1[pos] = card_revealed[b];
            handPlayer2[pos] = card_revealed[b];
            pos++;
        }

        //TODO compare hand
        //Return True if p1 has a stronger hand than p2, else false

        return false;
    }

    function getWinningPlayer() public view returns (uint) {
        uint winner = 0;
        for(uint i=0; i<players.length; i++) {
            for(uint j=0; j<players.length; j++) {
                if(i!=j && compareHands(i, j) == false){
                    winner = j;
                    break;
                }
            }
            if(i==winner){
                return winner;
            }else{
                i=winner;
            }
        }
        return winner;
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

