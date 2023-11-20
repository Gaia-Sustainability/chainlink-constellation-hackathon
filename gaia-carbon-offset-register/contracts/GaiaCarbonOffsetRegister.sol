// SPDX-License-Identifier: MIT
// Gaia Sustainability Team
pragma solidity 0.8.20;

import "https://github.com/smartcontractkit/chainlink/blob/develop/contracts/src/v0.8/ChainlinkClient.sol";
import "https://github.com/smartcontractkit/chainlink/blob/develop/contracts/src/v0.8/automation/AutomationCompatible.sol";
import "https://github.com/smartcontractkit/chainlink/blob/develop/contracts/src/v0.8/shared/access/ConfirmedOwnerWithProposal.sol";


contract GaiaCarbonOffsetRegister is ChainlinkClient, AutomationCompatibleInterface {
    using Chainlink for Chainlink.Request;

    uint256 public counter;
    uint256 public tokenCounter;

    address private oracle;
    bytes32 private jobId;
    uint256 private fee;

    uint256 private immutable interval;
    uint256 private lastTimeStamp;

    string[] public TaxIdArray;
    address[] public creatorArray;      

    mapping(bytes32 => address) public toAddresses;
    mapping(bytes32 => uint256) public toTokenID;           

    mapping(uint256 => string) public tokenIDToProject;

    address public tokenAddr;
    address public creator;

    event RequestMultipleFulfilled(
        bytes32 requestId_,
        uint256 aqi_,
        uint256 no2_,
        uint256 o3_,
        uint256 pm10_,
        uint256 pm2_5_,
        address indexed requester_
    );

    event InvoiceData(
        bytes32 requestId_,
        string lat_,
        string lon_,
        uint256 timestamp_,
        address indexed requester_
    );

    constructor(address) {
        creator = msg.sender;
        oracle = 0xd57018342B19Bc74dD6f5Fa8B73c934694b3aC10;
        jobId = "c7ef2e55f68e45b4b98219b8f2854189";
        fee = 0;
        interval = 5 minutes; //needs to change
        lastTimeStamp = block.timestamp;
    }

    modifier onlyOwner() {
        require(msg.sender == creator, "Not owner");
        // Underscore is a special character only used inside
        // a function modifier and it tells Solidity to
        // execute the rest of the code.
        _;
    }

    //function -> add to array and requestMultipleParameters
    function addCarbonOffsetProject(
        string memory _TaxId
    ) public {
        TaxIdArray.push(_TaxId);
        creatorArray.push(msg.sender);

        tokenCounter += 1;
        tokenIDToProject[tokenCounter] = _TaxId;

        requestMultipleParametersFromUser(_TaxId, msg.sender);
    }


    function checkUpkeep(
        bytes calldata /* checkData */
    )
        external
        override
        returns (
            bool upkeepNeeded,
            bytes memory /* performData */
        )
    {
        upkeepNeeded = (block.timestamp - lastTimeStamp) > interval;
    }

    function performUpkeep(
        bytes calldata /* performData */
    ) external override {
        lastTimeStamp = block.timestamp;

        if (counter >= creatorArray.length) {
            counter = 0;
        }

        string memory _TaxId = TaxIdArray[counter];

        requestMultipleParameters(_TaxId);
    }

    function requestMultipleParameters(string memory _TaxId)
        public
        returns (bytes32 requestId)
    {
        Chainlink.Request memory req = _buildChainlinkRequest(
            jobId,
            address(this),
            this.fulfillMultipleParameters.selector
        );

        req.add("TaxId", _TaxId);

        // Sends the request
        requestId = _sendChainlinkRequestTo(oracle, req, fee);

    }

    function requestMultipleParametersFromUser(
        string memory _TaxId,
        address _sender
    ) public returns (bytes32 requestId) {
        Chainlink.Request memory req = _buildChainlinkRequest(
            jobId,
            address(this),
            this.fulfillMultipleParameters.selector
        );

        req.add("TaxId", _TaxId);

        // Sends the request
        requestId = _sendChainlinkRequestTo(oracle, req, fee);

    }

    function fulfillMultipleParameters(
        bytes32 requestId,
        uint256 TotalAmount_Response
    ) public recordChainlinkFulfillment(requestId) {
        emit RequestMultipleFulfilled(
            requestId,
            TotalAmount_Response,
            toAddresses[requestId]
        );



        counter = counter + 1;
    }

    function withdrawLink() public onlyOwner {
        LinkTokenInterface link = LinkTokenInterface(_chainlinkTokenAddress());
        require(
            link.transfer(msg.sender, link.balanceOf(address(this))),
            "Unable to transfer"
        );
    }
}