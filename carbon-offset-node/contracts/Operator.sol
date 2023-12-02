// SPDX-License-Identifier: MIT
// Jether Rodrigues | jether@i3focus.com.br
// On deploy (https://docs.chain.link/chainlink-nodes/v1/fulfilling-requests/):
// 1. Injected Provider Wallet
// 2. Use the LINK contract address (Sepolia: 0x779877A7B0D9E8603169DdbD7836e478b4624789)
// 3. Use the Owner wallet address (Gaia Wallet Account: 0xCdcC503F1f5195e9E3180B5ef6Ab0AB175e4a247)
pragma solidity ^0.7.6;
import "@chainlink/contracts/src/v0.7/Operator.sol"; 
// Operator contract deployed: 0x5A2EBb86d22e6465041fbC34fc1c00e45942788D
// It's needed to add the chainlink node address in the Operator whitelist: 0xf09F68316747ee1c8C3b6F7459CAcA555bdE7724
// setAuthorizedSenders: ["0xf09F68316747ee1c8C3b6F7459CAcA555bdE7724"], after, isAuthorizedSender: 0xf09F68316747ee1c8C3b6F7459CAcA555bdE7724, and, 
// if the previous transaction works well, the chainlink node contract will be in the whitelist