# Chainlink Constellation Hackathon
## Guides
1. Miro Project: https://miro.com/app/board/uXjVNXGK8ps=/
2. Devpost Project: https://devpost.com/software/gaia-sustainability
3. About hackathon: https://chain.link/hackathon
4. Chainlink docs: https://docs.chain.link/chainlink-nodes/v1/node-config
5. Help to create the node: https://www.blockmeadow.com/how-to-deploy-a-chainlink-node/
6. Chainlink node on AWS: https://aws-ia.github.io/cfn-ps-chainlinklabs-chainlink-node/
7. Chainlink node on Moonbeam: https://docs.moonbeam.network/node-operators/oracle-nodes/node-chainlink/

# Gaia Carbon Project
All blockchain dependencies for this project were deployed or configured on the Polygon Mumbai network.

## Project architecture diagram:

![Project Sequence Diagram](https://github.com/Gaia-Sustainability/chainlink-constellation-hackathon/blob/main/gaia_hackathon.png)

## Setup
1. Metamask funded with LINK tokens - ([faucets.chain.link](https://faucets.chain.link/mumbai)). Also, ensure you have enough test Mumbai Matic

2. Install NPM and Node > v 17

3. Create an Alchemy account, create an app, and get the keys

4. The following environment variables should be readily available (check the `env.example` file to see which ones you will need and what name they are given):

```
The following ENV VARIABLE values
ALCHEMY_WSS_URL=
ALCHEMY_RPC_URL=
PRIVATE_KEY=
```

Set your environment variables using the `env-enc` package included.  
1. Set password (this password will be used to encrypt the environment variables and decrypt too) with `npx env-enc set-pw`. Remember this password otherwise you will have to set the env vars each time!
2. Set the above-mentioned env vars with `npx env-enc set` ...and then follow the prompts
3. After you set all the env vars, you can view the decrypted, human-readable version by running `npx env-enc view`

**Note** Each time you open a fresh terminal or restart a terminal session you will need to run `npx env-enc set-pw` but not the other steps.

## Contracts
We have deployed some contracts, we give the smart contracts address to make it possible see them on the Mumbai network scan: 
1. Gaia Carbon Functions Consumer: https://mumbai.polygonscan.com/address/0x0dc1b5cb19095bb39c6740615a2995eb67226955
2. Gaia Carbon NFT: https://mumbai.polygonscan.com/address/0x83CDA4fee13B2e7F59186013035E00de0eAD8d3A

## Subscriptions
We have created the following subscriptions:
1. Chainlink VRF: https://vrf.chain.link/mumbai/6675
2. Chainlink Functions: https://functions.chain.link/mumbai/1079

## NFE Service
We have built a service to generate a fake Brazil electronic fiscal document that represents a purchase that some company made, and for the Gaia Carbon project, the purchases were related to a Wood for burn and the NCM `44011000` (universal product number) was used to filter only specific product that the project will use in a real project.
Bellow, some `curl` commands example to see the project in the production:
1. Get NFE's:
```shell
curl 'https://nfe-service-hackathon-e1676ada9c11.herokuapp.com/v1/api/nfes' \
  -H 'Accept: application/json'
```
2. Get the newest generated NFE for some company:
```shell
curl 'https://nfe-service-hackathon-e1676ada9c11.herokuapp.com/v1/api/nfes/ncm/44011000/carbon-free-calculation' \
  -H 'Accept: application/json'
```

## OpenSea NFT Marketplace
The minted NFTs have been displayed on the OpenSea marketplace for the Gaia account: https://testnets.opensea.io/GaiaSustainability

# Chainlink Constellation Hackathon
## Chainlink Youtube Videos Guides
1. [ 2023-11-08 ] - Opening Ceremony | Constellation - https://www.youtube.com/watch?v=nqdJD6roPhw
2. [ 2023-11-08 ] - Gear Up for Constellation: A Chainlink Hackathon - https://www.youtube.com/watch?v=Qhj_SQYaMEw
3. [ 2023-11-09 ] - Quickly Build Production Apps on Ethereum With Scaffold-Eth - https://www.youtube.com/watch?v=ofXQm06z39k
4. [ 2023-11-09 ] - End-to-End Smart Contract Audit - https://www.youtube.com/watch?v=ylP44hSbI1o
5. [ 2023-11-09 ] - The Avalanche Network - Built for a Multichain Future - https://www.youtube.com/watch?v=fqI4zdHtQZY
6. [ 2023-11-10 ] - Introduction to Chainlink Trust Minimized Services - https://www.youtube.com/watch?v=U9hcz6cNxuQ
7. [ 2023-11-10 ] - Introduction to Remix and Solidity - https://www.youtube.com/watch?v=leWJ-djjwjU
8. [ 2023-11-11 ] - Building Cross Chain dApps Using CCIP - https://www.youtube.com/watch?v=Dvna-eEQEsQ
9. [ 2023-11-14 ] - Deep Dive Into Chainlink Functions - https://www.youtube.com/watch?v=vt0OuOm4-aY
10. [ 2023-11-15 ] - Connect dApps to Tencent Cloud With Chainlink Functions - https://www.youtube.com/watch?v=WZKY1i-qR9g
11. [ 2023-11-15 ] - Getting Started with the Polygon LxLy Bridge - https://www.youtube.com/watch?v=DJ_qQABCiFQ
12. [ 2023-11-16 ] - Integrating Chainlink Services for Web3 Games - https://www.youtube.com/watch?v=VrohlqkglOI
13. [ 2023-11-16 ] - Creando Contratos Cross-Chain junto a Avalanche - https://youtube.com/live/Lx_kJNJsxck
14. [ 2023-11-20 ] - Realtime Web3 Security - https://www.youtube.com/watch?v=yLKlfzhR-xs
15. [ 2023-11-20 ] - Space and Time Demo for Hackathon Developers - https://www.youtube.com/watch?v=16ETEKstOFg
16. [ 2023-11-21 ] - Integrating Data Streams for DeFi Applications - https://www.youtube.com/watch?v=7jkzIzC14uQ
17. [ 2023-11-21 ] - Kickstarting Your Web3 Career - https://www.youtube.com/watch?v=9zsMeo1_0LM
18. [ 2023-11-22 ] - Build Cross-Chain NFTs With Chainlink CCIP and QuickNode - https://www.youtube.com/watch?v=Q9Ji9ehIxdg
19. [ 2023-11-22 ] - Full-Stack Web3 Development with The Graph - https://www.youtube.com/watch?v=B551dyO3awA
20. [ 2023-11-22 ] - What Venture Funds is looking for in Hackathons - https://www.youtube.com/watch?v=SOYEAjMPVYo
21. [ 2023-11-27 ] - How To Submit and Pitch a Hackathon Project - https://www.youtube.com/watch?v=3-IljWcQ2SE
22. [ 2023-12-20 ] - Closing Ceremony - https://www.youtube.com/watch?v=gTt6mVfUCqM
