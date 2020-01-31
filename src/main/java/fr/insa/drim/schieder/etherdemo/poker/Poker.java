package fr.insa.drim.schieder.etherdemo.poker;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Int256;
import org.web3j.abi.datatypes.generated.StaticArray2;
import org.web3j.abi.datatypes.generated.Uint128;
import org.web3j.abi.datatypes.generated.Uint16;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.5.0.
 */
public class Poker extends Contract {
    private static final String BINARY = "60606040526101406040519081016040908152611b588252611f406020830152610fa09082015261138860608201526000608082018190526103e860a08301526107d060c083015261232860e0830152610bb86101008301526117706101208301526200006e91600a62000cc0565b50610680604051908101604052806040805190810160405280600281526020017f703700000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600281526020017f683600000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600381526020017f743134000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600381526020017f683132000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600381526020017f633130000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600281526020017f703900000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600281526020017f633300000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600281526020017f743200000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600381526020017f703131000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600381526020017f633134000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600281526020017f633400000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600281526020017f683500000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600381526020017f703132000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600281526020017f633200000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600381526020017f683133000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600281526020017f743500000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600381526020017f743133000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600281526020017f703200000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600281526020017f633500000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600281526020017f683200000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600381526020017f683130000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600281526020017f683700000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600281526020017f703400000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600381526020017f633132000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600281526020017f683800000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600281526020017f633700000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600381526020017f703134000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600281526020017f633800000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600281526020017f743800000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600381526020017f743131000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600381526020017f743132000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600381526020017f683134000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600281526020017f683400000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600381526020017f633131000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600281526020017f743400000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600281526020017f633600000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600381526020017f703130000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600281526020017f743900000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600281526020017f703600000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600281526020017f743300000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600281526020017f743600000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600381526020017f703133000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600281526020017f633900000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600381526020017f683131000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600281526020017f703500000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600381526020017f743130000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600281526020017f703800000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600281526020017f683300000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600281526020017f743700000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600281526020017f703300000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600281526020017f6839000000000000000000000000000000000000000000000000000000000000815250815260200160408051908101604052600381527f63313300000000000000000000000000000000000000000000000000000000006020820152905262000c8390600190603462000d16565b506038805465ffffffff00001961ffff1990911661010017169055603b80546001608060020a0319169055341562000cba57600080fd5b62000e6e565b82805482825590600052602060002090810192821562000d04579160200282015b8281111562000d04578251829061ffff1690559160200191906001019062000ce1565b5062000d1292915062000d68565b5090565b826034810192821562000d5a579160200282015b8281111562000d5a5782518290805162000d4992916020019062000d88565b509160200191906001019062000d2a565b5062000d1292915062000dfb565b62000d8591905b8082111562000d12576000815560010162000d6f565b90565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1062000dcb57805160ff191683800117855562000d04565b8280016001018555821562000d04579182015b8281111562000d0457825182559160200191906001019062000dde565b62000d8591905b8082111562000d1257600062000e19828262000e23565b5060010162000e02565b50805460018160011615610100020316600290046000825580601f1062000e4b575062000e6b565b601f01602090049060005260206000209081019062000e6b919062000d68565b50565b611a5a8062000e7e6000396000f300606060405236156100c25763ffffffff7c0100000000000000000000000000000000000000000000000000000000600035041663119f439081146100c757806312e583b61461012a5780631ea1380c1461013d57806321df0da7146101885780633055cf98146101b25780634b1274d8146101d95780636d4ce63c14610202578063a0c92f7d1461023a578063b688a3631461024f578063f061c38614610257578063f6c1053f146102b8578063f8a02d84146102d2578063fc2b8cc314610323575b600080fd5b34156100d257600080fd5b61011860046024813581810190830135806020601f8201819004810201604051908101604052818152929190602084018383808284375094965061035295505050505050565b60405190815260200160405180910390f35b341561013557600080fd5b610118610467565b341561014857600080fd5b610150610511565b6040518082604080838360005b8381101561017557808201518382015260200161015d565b5050505090500191505060405180910390f35b341561019357600080fd5b61019b61056f565b60405161ffff909116815260200160405180910390f35b34156101bd57600080fd5b6101c56105b5565b604051901515815260200160405180910390f35b34156101e457600080fd5b6101ec6106a0565b60405160ff909116815260200160405180910390f35b341561020d57600080fd5b61021561072f565b6040516fffffffffffffffffffffffffffffffff909116815260200160405180910390f35b341561024557600080fd5b61024d610747565b005b610118610786565b341561026257600080fd5b610118600460a481600560a06040519081016040529190828260a080828437820191505050505091908060a001906005806020026040519081016040529190828260a08082843750939550610946945050505050565b34156102c357600080fd5b61024d61ffff60043516610ac3565b34156102dd57600080fd5b61011860046024813581810190830135806020601f82018190048102016040519081016040528181529291906020840183838082843750949650610e9795505050505050565b341561032e57600080fd5b610336610f0f565b604051600160a060020a03909116815260200160405180910390f35b60008061035d6116ab565b50600019905082600081818151811061037257fe5b016020015160f860020a900460f860020a0260f860020a90049050806063141561039f576001925061045b565b816000815181106103ac57fe5b016020015160f860020a900460f860020a02600160f860020a031916607460f860020a0214156103df576002925061045b565b816000815181106103ec57fe5b016020015160f860020a900460f860020a02600160f860020a031916607060f860020a02141561041f576004925061045b565b8160008151811061042c57fe5b016020015160f860020a900460f860020a02600160f860020a031916606860f860020a02141561045b57600892505b8293505b505050919050565b60008080805b603954811015610509576104b360398281548110151561048957fe5b906000526020600020906004020160020160039054906101000a9004600160a060020a0316610f1b565b816039828154811015156104c357fe5b90600052602060002090600402016003015413156105015760398054829081106104e957fe5b90600052602060002090600402016003015491508092505b60010161046d565b509092915050565b6105196116bd565b6105216116bd565b60005b60395481101561056957603980548290811061053c57fe5b906000526020600020906004020160030154828260028110151561055c57fe5b6020020152600101610524565b50919050565b600160a060020a0333166000908152603a602052604081205460398054909190811061059757fe5b600091825260209091206002600490920201015461ffff1690505b90565b603954600090600290101561069a576039805460009081106105d357fe5b6000918252602090912060049091020160020154603b546301000000909104600160a060020a0316906108fc906064906fffffffffffffffffffffffffffffffff908116605f0216046fffffffffffffffffffffffffffffffff169081150290604051600060405180830381858888f19350505050151561065357600080fd5b6106906039600081548110151561066657fe5b906000526020600020906004020160020160039054906101000a9004600160a060020a0316611188565b50600190506105b2565b50600090565b6000806106ab611564565b50600090505b6039548110156107055760006039828154811015156106cc57fe5b600091825260209091206002600490920201015461ffff16116106fd576106fb60398281548110151561066657fe5b505b6001016106b1565b6038805460ff61010080830482166001019182160261ff00199092169190911790915591505b5090565b603b546fffffffffffffffffffffffffffffffff1690565b60005b60038110156107835761075b6115cc565b6035826003811061076857fe5b0190805161077a9291602001906116e3565b5060010161074a565b50565b600061079061175d565b603954600290101561093e57346103e8146107aa57600080fd5b603954600160a060020a0333166000908152603a602052604090819020919091558051908101604052806107dc6115cc565b81526020016107e96115cc565b90526039805491925090600181016108018382611789565b9160005260206000209060040201600060c0604051908101604090815285825260646020830152810160008152600160a060020a0333166020820152600060408201819052606090910152919050815161085e90829060026117b5565b50602082015160028201805461ffff191661ffff92909216919091179055604082015160028201805462ff000019166201000083600381111561089d57fe5b021790555060608201518160020160036101000a815481600160a060020a030219169083600160a060020a0316021790555060808201518160020160176101000a81548161ffff021916908361ffff16021790555060a08201516003909101555050603b80546fffffffffffffffffffffffffffffffff8082166103e801166fffffffffffffffffffffffffffffffff19909116179055603954915061072b565b600091505090565b600080808060808601516001600082121561095d57fe5b60029190910a0260608701516001600082121561097657fe5b60029190910a0260408801516001600082121561098f57fe5b60029190910a026020890151600160008212156109a857fe5b60029190910a028951600160008212156109be57fe5b9060020a02171717179150600090505b6005811015610a0d578581600581106109e357fe5b6020020151600402600160008212156109f857fe5b60029190910a028301909217916001016109ce565b81600003821682811515610a1d57fe5b05601f1480610a2d57508161403c145b610a38576001610a3b565b60035b60ff16600f8407039250846004602002015160608601516040870151602088015117171785511415610a835781617c0014610a77576001610a7b565b6004195b60000b830392505b608086015160608701516040880151602089015189516000805489908110610aa757fe5b9060005260206000209001540101010101935050505092915050565b610acb611800565b600160a060020a0333166000908152603a6020526040812054603980549091908110610af357fe5b906000526020600020906004020160c060405190810160405290816000820160028060200260405190810160405291906000835b82821015610bda578382018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610bc65780601f10610b9b57610100808354040283529160200191610bc6565b820191906000526020600020905b815481529060010190602001808311610ba957829003601f168201915b505050505081526020019060010190610b27565b50505090825250600282015461ffff8116602083015260409091019062010000900460ff166003811115610c0a57fe5b6003811115610c1557fe5b8152600282015463010000008104600160a060020a0316602083015277010000000000000000000000000000000000000000000000900461ffff1660408201526003909101546060909101529150600082604001516003811115610c7557fe5b14610c7f57600080fd5b816020015161ffff168383608001510161ffff161115610cfb5781602001516038805461ffff6201000080830482169094011690920263ffff0000199092169190911790556020820151826080018181510161ffff16905250600060208301526040820160025b90816003811115610cf357fe5b905250610e92565b603854640100000000900461ffff168360808401510161ffff161015610d2657604082016003610ce6565b603854640100000000900461ffff168360808401510161ffff161115610e45575060005b603954811015610dd95760016039805483908110610d6457fe5b906000526020600020906004020160020160029054906101000a900460ff166003811115610d8e57fe5b1415610dd15760006039805483908110610da457fe5b906000526020600020906004020160020160029054906101000a900460ff166003811115610dce57fe5b50505b600101610d4a565b82826080018181510161ffff1690525060808201516038805463ffff00001965ffff000000001990911664010000000061ffff94851602179081166201000091829004841687019093160291909117905582602083018181510361ffff16905250604082016001610ce6565b82826080018181510161ffff9081169091526038805463ffff0000198116620100009182900484168801909316029190911790555082602083018181510361ffff16905250600160408301525b505050565b6000610ea16116ab565b5081600080805b8351831015610f0657838381518110610ebd57fe5b016020015160f860020a900460f860020a0260f860020a9004905060308110158015610eea575060398111155b15610efb576030810382600a020191505b600190920191610ea8565b50949350505050565b60003361056981611188565b600080610f2661183d565b610f2e61183d565b600093505b60395484101561118157600160a060020a0385166000908152603a6020526040902054603980549091908110610f6557fe5b9060005260206000209060040201925060a0604051908101604052806110278560005b018054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561101d5780601f10610ff25761010080835404028352916020019161101d565b820191906000526020600020905b81548152906001019060200180831161100057829003601f168201915b5050505050610e97565b8152602001611037856001610f88565b815260200161104860356000610f88565b815260200161105960356001610f88565b815260200161106a60356002610f88565b9052915060a0604051908101604052806111208560005b018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156111165780601f106110eb57610100808354040283529160200191611116565b820191906000526020600020905b8154815290600101906020018083116110f957829003601f168201915b5050505050610352565b8152602001611130856001611081565b815260200161114160356000611081565b815260200161115260356001611081565b815260200161116360356002611081565b905290506111718282610946565b6003840155600190930192610f33565b5050505050565b6111906116ab565b600080805b603954600019018210156111f55784600160a060020a03166039838154811015156111bc57fe5b600091825260209091206004909102016002015463010000009004600160a060020a031614156111ea578192505b600190910190611195565b60395482106112035761045f565b50815b6039546000190181101561133e57603980546001830190811061122557fe5b906000526020600020906004020160398281548110151561124257fe5b6000918252602090912060049091020161125e81836002611857565b5060028281018054918301805461ffff191661ffff90931692909217808355905460ff620100009182900416929162ff000019169083600381111561129f57fe5b0217905550600282810180549183018054600160a060020a036301000000948590041690930276ffffffffffffffffffffffffffffffffffffffff0000001990931692909217808355905461ffff7701000000000000000000000000000000000000000000000091829004160278ffff000000000000000000000000000000000000000000000019909116179055600391820154910155600101611206565b60398054600019810190811061135057fe5b6000918252602082206004909102019061136a82826118a0565b5060028101805478ffffffffffffffffffffffffffffffffffffffffffffffffff19169055600060039091015560398054906113aa906000198301611789565b50603980548060200260200160405190810160405281815291906000602084015b8282101561155657600084815260209020600483020160c060405190810160405290816000820160028060200260405190810160405291906000835b828210156114ba578382018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156114a65780601f1061147b576101008083540402835291602001916114a6565b820191906000526020600020905b81548152906001019060200180831161148957829003601f168201915b505050505081526020019060010190611407565b50505090825250600282015461ffff8116602083015260409091019062010000900460ff1660038111156114ea57fe5b60038111156114f557fe5b8152600282015463010000008104600160a060020a03166020808401919091527701000000000000000000000000000000000000000000000090910461ffff16604083015260039092015460609091015290825260019290920191016113cb565b505050509350505050919050565b60008061156f610467565b9050603860029054906101000a900461ffff1660398281548110151561159157fe5b60009182526020909120600490910201600201805461ffff191661ffff9290921691909117905550506038805463ffff000019169055600190565b6115d46116ab565b6038805460ff198116600160ff92831681810190931691909117909255600091906034900660ff1660348110151561160857fe5b019050808054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156116a05780601f10611675576101008083540402835291602001916116a0565b820191906000526020600020905b81548152906001019060200180831161168357829003601f168201915b505050505091505090565b60206040519081016040526000815290565b604080519081016040526002815b60008152602001906001900390816116cb5790505090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061172457805160ff1916838001178555611751565b82800160010185558215611751579182015b82811115611751578251825591602001919060010190611736565b5061072b9291506118be565b604080519081016040526002815b6117736116ab565b81526020019060019003908161176b5790505090565b815481835581811511610e9257600402816004028360005260206000209182019101610e9291906118d8565b82600281019282156117f4579160200282015b828111156117f4578251829080516117e49291602001906116e3565b50916020019190600101906117c8565b5061072b929150611926565b60e060405190810160405280611814611949565b815260006020820181905260408201819052606082018190526080820181905260a09091015290565b60a0604051908101604052600081526004602082016116cb565b82600281019282156117f4579182015b828111156117f45782829080546001816001161561010002031660029004611890929190611975565b5091600101919060010190611867565b5060006118ad82826119ea565b506118bc9060010160006119ea565b565b6105b291905b8082111561072b57600081556001016118c4565b6105b291905b8082111561072b5760006118f282826118a0565b5060028101805478ffffffffffffffffffffffffffffffffffffffffffffffffff19169055600060038201556004016118de565b6105b291905b8082111561072b57600061194082826119ea565b5060010161192c565b604080519081016040526002815b61195f6116ab565b8152602001906001900390816119575790505090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106119ae5780548555611751565b8280016001018555821561175157600052602060002091601f016020900482015b828111156117515782548255916001019190600101906119cf565b50805460018160011615610100020316600290046000825580601f10611a105750610783565b601f01602090049060005260206000209081019061078391906118be5600a165627a7a72305820639cfab8c68edde0c21cfe372d50b3e57ecf0512543cab121d790e9d7734fd8a0029";

    public static final String FUNC_TOCOLOR = "toColor";

    public static final String FUNC_GETWINNINGPLAYER = "getWinningPlayer";

    public static final String FUNC_GETSCORES = "getScores";

    public static final String FUNC_GETTOKEN = "getToken";

    public static final String FUNC_VICTORYGAME = "victoryGame";

    public static final String FUNC_VICTORYROUND = "victoryRound";

    public static final String FUNC_GET = "get";

    public static final String FUNC_REVELEDRIVER = "reveledRiver";

    public static final String FUNC_JOIN = "join";

    public static final String FUNC_RANKPOKERHAND = "rankPokerHand";

    public static final String FUNC_PLAY = "play";

    public static final String FUNC_STRINGTOINT = "stringToInt";

    public static final String FUNC_QUIT = "quit";

    protected Poker(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Poker(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<BigInteger> toColor(String s) {
        final Function function = new Function(FUNC_TOCOLOR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(s)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> getWinningPlayer() {
        final Function function = new Function(
                FUNC_GETWINNINGPLAYER, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<List> getScores() {
        final Function function = new Function(FUNC_GETSCORES, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<StaticArray2<Int256>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteCall<BigInteger> getToken() {
        final Function function = new Function(FUNC_GETTOKEN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint16>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> victoryGame() {
        final Function function = new Function(
                FUNC_VICTORYGAME, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> victoryRound() {
        final Function function = new Function(
                FUNC_VICTORYROUND, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> get() {
        final Function function = new Function(FUNC_GET, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint128>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> reveledRiver() {
        final Function function = new Function(
                FUNC_REVELEDRIVER, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> join(BigInteger weiValue) {
        final Function function = new Function(
                FUNC_JOIN, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<BigInteger> rankPokerHand(List<BigInteger> cs, List<BigInteger> ss) {
        final Function function = new Function(FUNC_RANKPOKERHAND, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.StaticArray5<org.web3j.abi.datatypes.generated.Int256>(
                        org.web3j.abi.Utils.typeMap(cs, org.web3j.abi.datatypes.generated.Int256.class)), 
                new org.web3j.abi.datatypes.generated.StaticArray5<org.web3j.abi.datatypes.generated.Int256>(
                        org.web3j.abi.Utils.typeMap(ss, org.web3j.abi.datatypes.generated.Int256.class))), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> play(BigInteger amount) {
        final Function function = new Function(
                FUNC_PLAY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint16(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> stringToInt(String s) {
        final Function function = new Function(FUNC_STRINGTOINT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(s)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> quit() {
        final Function function = new Function(
                FUNC_QUIT, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<Poker> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Poker.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Poker> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Poker.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static Poker load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Poker(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Poker load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Poker(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
