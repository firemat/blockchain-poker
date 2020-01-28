pragma solidity ^0.4.17;

contract mortal {
    address owner;
    constructor() public { owner = msg.sender; }
    function kill() public { if (msg.sender == owner) selfdestruct(owner); }
}

contract broutille is mortal {
    uint counter;

// https://solidity.readthedocs.io/en/develop/miscellaneous.html?highlight=Pure
    function caress() public view returns (string) {

            if(counter < 1)
            {
                return "HmmmrPurr!?";
            }
            if(counter < 3)
            {
                return "MiauHmmmrrr! <3 <3 <3";
            }
            else
            {
                return "BEURK!!! :-X";
            }
	}

    function offerYoghurt() public {
        ++counter;
    }
}
