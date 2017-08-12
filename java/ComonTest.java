package com.baoshichain;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.junit.Test;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.codegen.SolidityFunctionWrapperGenerator;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import rx.Observable;
import rx.functions.Action1;

import com.baoshichain.domain.BaoShiTokenContract;
import com.baoshichain.domain.BaoShiTokenContract.TransferEventResponse;


public class CommonTest {
	
	private Web3j web3;
	@Test
	public void connect(){
		web3 = Web3j.build(new HttpService("http://192.168.132.133:8545/"));
		try {
			Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().sendAsync().get();
			String clientVersion = web3ClientVersion.getWeb3ClientVersion();
			System.out.println(clientVersion);
			Credentials credentials = WalletUtils.loadCredentials("123", "D:/workspace/baoshichain/src/main/resources/UTC--2017-07-26T10-38-48.569288272Z--e559eddf4367634912316d71d4f0b52766c64a79");
			//System.out.println();credentials.getAddress();
			String addr = credentials.getAddress();
			System.out.println(addr);
			long value = web3.ethGetBalance("0xe559eddf4367634912316d71d4f0b52766c64a79", DefaultBlockParameterName.LATEST).sendAsync().get().getBalance().longValue();
			System.out.println(value);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CipherException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void wrapper(){
		web3 = Web3j.build(new HttpService("http://192.168.132.133:8545/"));
			Web3ClientVersion web3ClientVersion;
			try {
				web3ClientVersion = web3.web3ClientVersion().sendAsync().get();
				String clientVersion = web3ClientVersion.getWeb3ClientVersion();
				System.out.println(clientVersion);
				Credentials credentials = WalletUtils.loadCredentials("123", "D:/workspace/baoshichain/src/main/resources/UTC--2017-07-26T10-38-48.569288272Z--e559eddf4367634912316d71d4f0b52766c64a79");
				String[] strArray={"generate","D:/workspace/baoshichain/src/main/resources/baoShiToken.bin","D:/workspace/baoshichain/src/main/resources/baoShiToken.abi","-o","D:/workspace/baoshichain/src/test/java/","-p","com.baoshichain.token"};
				SolidityFunctionWrapperGenerator.run(strArray);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CipherException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	@Test
	public void deploy(){
		web3 = Web3j.build(new HttpService("http://192.168.132.133:8545/"));
		Web3ClientVersion web3ClientVersion;
		try {
			web3ClientVersion = web3.web3ClientVersion().sendAsync().get();
			String clientVersion = web3ClientVersion.getWeb3ClientVersion();
			System.out.println(clientVersion);
			Credentials credentials = WalletUtils.loadCredentials("123", "D:/workspace/baoshichain/src/main/resources/UTC--2017-07-26T10-38-48.569288272Z--e559eddf4367634912316d71d4f0b52766c64a79");
			BaoShiTokenContract token = BaoShiTokenContract.deploy(web3, credentials, new BigInteger("0"), new BigInteger("897721"), new BigInteger("0"), new Uint256(new BigInteger("100000000"))).get();
		//	System.out.println(token.buyPrice().get().getValue());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CipherException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void doContract(){
		web3 = Web3j.build(new HttpService("http://192.168.132.133:8545/"));
		Web3ClientVersion web3ClientVersion;
		try {
			web3ClientVersion = web3.web3ClientVersion().sendAsync().get();
			//String clientVersion = web3ClientVersion.getWeb3ClientVersion();
			//System.out.println(clientVersion);
			Credentials credentials = WalletUtils.loadCredentials("123", "D:/workspace/baoshichain/src/main/resources/UTC--2017-07-26T10-38-48.569288272Z--e559eddf4367634912316d71d4f0b52766c64a79");
			BaoShiTokenContract baoShiToken = BaoShiTokenContract.load("0x0aB740c6c7ac4D5A35aC372EA806F251f67d06A9", web3, credentials, new BigInteger("10"), new BigInteger("667300"));
			TransactionReceipt transactionReceipt = baoShiToken.transfer(new Address("0x56c34951381e338c4091282295c0c5351354e794"), new Uint256(new BigInteger("20"))).get();
			System.out.println(transactionReceipt.getTransactionHash());
			//System.out.println(baoShiToken.buyPrice().get().getValue());
			//Uint256 buyPrice = baoShiToken.buyPrice().get();
			//System.out.println(buyPrice);
			//System.out.println(baoShiToken.buyPrice().get().getValue());
			//String raw = baoShiToken.transfer(new Address("0x56c34951381e338c4091282295c0c5351354e794"), new Uint256(new BigInteger("1"))).get().getTransactionHash();
			//Object[] data = new String[] {"value:10000000000000000000",};
			//List<Type> data;
/*			Function function = new Function("buy", Arrays.asList(new Type(){
				@Override
				public Object getValue() {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public String getTypeAsString() {
					return "value:10000000000000000000";
				}}), Arrays.asList()); */
			//String encodeFunction = FunctionEncoder.encode(function);
			//System.out.println("encodeFunction:"+encodeFunction);
			//Transaction transaction = Transaction.createFunctionCallTransaction("0xe559eddf4367634912316d71d4f0b52766c64a79",BigInteger.valueOf(123123) , BaoShiToken.GAS_PRICE, BaoShiToken.GAS_LIMIT,  
			//		"0x39F986B64eC1C70ea996FB3b4626459850a1f298", encodeFunction);
			
			//org.web3j.protocol.core.methods.response.EthSendTransaction transactionResponse =
		    //         web3.ethSendTransaction(transaction).sendAsync().get();

			//String transactionHash = transactionResponse.getTransactionHash();
			
			//System.out.println(transactionHash);
			//Transaction
			
			//System.out.println(raw);
			
			//baoShiToken.buy().get().setTransactionIndex("value:10000000000000000000");
			/*Function function = new Function("buy",Arrays.asList(new Type),Arrays.asList());
			TransactionReceipt transactionReceipt = baoShiToken.buy().get();
			String txHash = transactionReceipt.getTransactionHash();
			System.out.println(txHash);
			
			String t1 = new String("aaa");
			Function function = new Function("buy", Arrays.asList(), Arrays.asList());
			String encodeFunction = FunctionEncoder.encode(function);
			Transaction transaction = Transaction.createFunctionCallTransaction("0xe559eddf4367634912316d71d4f0b52766c64a79",BigInteger.valueOf(123123) , BaoShiToken.GAS_PRICE, BaoShiToken.GAS_LIMIT,  
					"0x39F986B64eC1C70ea996FB3b4626459850a1f298", encodeFunction);
			
			org.web3j.protocol.core.methods.response.EthSendTransaction transactionResponse =
		             web3.ethSendTransaction(transaction).sendAsync().get();

			String transactionHash = transactionResponse.getTransactionHash();
			
			
*/			
			//System.out.println(encodeFunction);
		
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CipherException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	@Test
	public void eecovery(){
		//web3.
	}
	
	@Test
	public void eventCall_1(){
		web3 = Web3j.build(new HttpService("http://192.168.132.133:8545/"));
		//Web3ClientVersion web3ClientVersion;
		try {
			//web3ClientVersion = web3.web3ClientVersion().sendAsync().get();
			//String clientVersion = web3ClientVersion.getWeb3ClientVersion();
			//System.out.println(clientVersion);
			Credentials credentials = WalletUtils.loadCredentials("123", "D:/workspace/baoshichain/src/main/resources/UTC--2017-07-26T10-38-48.569288272Z--e559eddf4367634912316d71d4f0b52766c64a79");
			BaoShiTokenContract baoShiTokenContract = BaoShiTokenContract.load("0x0aB740c6c7ac4D5A35aC372EA806F251f67d06A9", web3, credentials, new BigInteger("10"), new BigInteger("667300"));
/*			baoShiTokenContract.transferEventObservable(DefaultBlockParameterName.EARLIEST,DefaultBlockParameterName.LATEST).subscribe(
					event->{System.out.println("to:"+event.address_to+";"+"from"+event.indexed_from);});
			*/
			EthFilter filter = new EthFilter(DefaultBlockParameterName.EARLIEST,
			        DefaultBlockParameterName.LATEST, "0x39Fe850F136f63F2E98B0965C0781C1D76ceC3aF");
			           //  [.addSingleTopic(...) | .addOptionalTopics(..., ...) | ...];
			web3.ethLogObservable(filter).subscribe(log -> {
			    System.out.println(log.getLogIndexRaw());
			});
			
			
			
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CipherException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	@Test
	public void listenTransferEvent(){
		web3 = Web3j.build(new HttpService("http://192.168.132.133:8545/"));
		
		//Web3ClientVersion web3ClientVersion;
		try {
			//web3ClientVersion = web3.web3ClientVersion().sendAsync().get();
			//String clientVersion = web3ClientVersion.getWeb3ClientVersion();
			//System.out.println(clientVersion);
			Credentials credentials = WalletUtils.loadCredentials("123", "D:/workspace/baoshichain/src/main/resources/UTC--2017-07-26T10-38-48.569288272Z--e559eddf4367634912316d71d4f0b52766c64a79");
			BaoShiTokenContract baoShiTokenContract = BaoShiTokenContract.load("0x0aB740c6c7ac4D5A35aC372EA806F251f67d06A9", web3, credentials, new BigInteger("10"), new BigInteger("667300"));
/*			baoShiTokenContract.transferEventObservable(DefaultBlockParameterName.EARLIEST,DefaultBlockParameterName.LATEST).subscribe(
					event->{System.out.println("to:"+event.address_to+";"+"from"+event.indexed_from);});
			*/
			/*EthFilter filter = new EthFilter(DefaultBlockParameterName.EARLIEST,
			        DefaultBlockParameterName.LATEST, "0x39Fe850F136f63F2E98B0965C0781C1D76ceC3aF");
			           //  [.addSingleTopic(...) | .addOptionalTopics(..., ...) | ...];
			web3.ethLogObservable(filter).subscribe(log -> {
			    System.out.println(log.getLogIndexRaw());
			});
			
			*/
			Observable<TransferEventResponse> observable = baoShiTokenContract.transferEventObservable(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST);
			observable.subscribe(new Action1<BaoShiTokenContract.TransferEventResponse>() {
				@Override
				public void call(TransferEventResponse transferEventResponse) {
					// TODO Auto-generated method stub
					String to = transferEventResponse.address_to.toString();
					String from = transferEventResponse.indexed_from.toString();
					String value = transferEventResponse.value.getValue().toString();
					System.out.println("to:"+to+"\n"+"from£º"+from+"\n"+"value:"+value+"\n");
				}
			});
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CipherException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void GeneKeystore(){
		try {
			WalletUtils.generateFullNewWalletFile("123", new File(""));
		} catch (NoSuchAlgorithmException | NoSuchProviderException
				| InvalidAlgorithmParameterException | CipherException
				| IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	

}
