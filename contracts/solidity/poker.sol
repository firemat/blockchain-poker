pragma solidity ^0.4.17;

contract poker {
    enum State {UNCERTAIN, VALID, ALLIN, FOLD}
    //"4 of a Kind", "Straight Flush", "Straight", "Flush", "High Card", "1 Pair", "2 Pair", "Royal Flush", "3 of a Kind", "Full House"
    int[] hands_point = [7000, 8000, 4000, 5000, 0, 1000, 2000, 9000, 3000, 6000];
    string[52] deck = ['p7', 'h6', 't14', 'h12', 'c10', 'p9', 'c3', 't2', 'p11', 'c14', 'c4', 'h5', 'p12', 'c2', 'h13', 't5', 't13', 'p2', 'c5', 'h2', 'h10', 'h7','p4', 'c12', 'h8', 'c7', 'p14', 'c8', 't8', 't11', 't12', 'h14', 'h4','c11', 't4', 'c6', 'p10', 't9', 'p6', 't3', 't6', 'p13', 'c9', 'h11', 'p5', 't10', 'p8', 'h3', 't7', 'p3', 'h9', 'c13'];
    string[3] card_revealed;
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
        int score; //score =  rang de la combinaison en milliers de points + somme des rangs des cartes de la main
    }

    uint8 constant NB_PLAYER = 2;
    Player[] players;
    mapping (address => uint256) ad2Index;
    uint128 constant FEE = 10**3; // change to 10**18
    uint128 gain = 0; 

    function join() public payable returns (uint) {
        if(players.length < NB_PLAYER) {
            require (msg.value == FEE);
            ad2Index[msg.sender] = players.length;
            string[2] memory hand = [deck[deck_i++], deck[deck_i++]];
            players.push(Player(hand, 100, State.UNCERTAIN, msg.sender, 0, 0));
            gain += FEE;
            return players.length;
        }
        return 0;
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

    function drawsCard() private returns(string){
        string storage nextCard=deck[deck_i++];
        return nextCard;
    }
    
    function reveledRiver() public {
        for (uint i=0; i<5; i++){
            card_revealed[i]=drawsCard();
        }
    }

    function play(uint16 amount) public {      
      Player memory p = players[ad2Index[msg.sender]];
      require(p.playerState==State.UNCERTAIN);
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
    
    function computeScores(address id) private view {
      for(uint i = 0; i<players.length; i++) {
        Player memory p = players[ad2Index[id]];
        int[5] memory ranks = [stringToInt(p.hand[0]),stringToInt(p.hand[1]),stringToInt(card_revealed[0]),stringToInt(card_revealed[1]),stringToInt(card_revealed[2])];
        int[5] memory colors = [toColor(p.hand[0]),toColor(p.hand[1]),toColor(card_revealed[0]),toColor(card_revealed[1]),toColor(card_revealed[2])];
        p.score = rankPokerHand(ranks, colors);
      }      
    }

    function toColor(string s) private pure returns (int) {
      int color;
      bytes memory str = bytes(s);
      if(str[0]=='c') color = 1;
      else if(str[0]=='t') color = 2;
      else if(str[0]=='p') color = 4;
      else if(str[0]=='h') color = 8;
      return color;
    }

    /* score =  rang de la combinaison en milliers de points + somme des rangs des cartes de la main
    Implemented using the method described on this website
    https://www.codeproject.com/Articles/569271/A-Poker-hand-analyzer-in-JavaScript-using-bit-math*/
    function rankPokerHand(int[5] cs, int[5] ss) public view returns (int){
      int v = 0;
      int s = int(1)<<cs[0]|int(1)<<cs[1]|int(1)<<cs[2]|int(1)<<cs[3]|int(1)<<cs[4]; //rank of cards in one-hot encoding from 2-14
      for (uint i = 0; i<5; i++) {
        v = v|(v+(int(1)<<cs[i]*4)); // occurence of same card on 52 bits
      }
      v = v % 15 - ((s/(s&-s) == 31) || (s == 0x403c) ? 3 : 1); //determine hand
      if(ss[0] == (ss[1]|ss[2]|ss[3]|ss[4])) {
        v-= (s == 0x7c00) ? -5 : int8(1); // handle color
      }
      return hands_point[uint(v)]+cs[0]+cs[1]+cs[2]+cs[3]+cs[4];
    }

    function stringToInt(string s) private pure returns (int){
        bytes memory b = bytes(s);
        uint i;
        uint result = 0;
        for (i = 0; i < b.length; i++) {
            uint c = uint(b[i]);
            if (c >= 48 && c <= 57) {
                result = result * 10 + (c - 48);
            }
        }
        return int(result);
    }

    function getWinningPlayer() public view returns (uint) {
        uint winner = 0;
        int max_score = 0;
        for(uint i=0; i<players.length; i++) {
            if(players[i].score > max_score) {
              max_score = players[i].score;
              winner = i;
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

