#!/bin/bash
cd ~/Library/Ethereum/
geth --datadir="elephantchain" --rpcapi personal,db,eth,net,web3 --rpc --nodiscover --mine --minerthreads=4 console
