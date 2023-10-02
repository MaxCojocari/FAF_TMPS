package core.transactions;

import java.util.*;

import actors.accounts.Account;

public class SwapTransaction extends BaseTransaction {
    private Account exchange;
    private String tokenIn;
    private String tokenOut;
    private double amountIn;
    private Map<String, Double> exchangeRates = new HashMap<String, Double>();

    public SwapTransaction(
            String id,
            Account sender,
            Account exchange,
            double fee,
            long timestamp,
            String tokenIn,
            String tokenOut,
            double amountIn,
            double ethRate, double usdtRate) {
        super(id, sender, exchange, amountIn, fee, timestamp);
        this.exchange = exchange;
        this.tokenIn = tokenIn;
        this.tokenOut = tokenOut;
        this.amountIn = amountIn;
        setExchangeRates("ETH", ethRate);
        setExchangeRates("USDT", usdtRate);
        transferFrom(tokenIn, amountIn, sender, exchange);
    }

    public void setExchangeRates(String currency, double rate) {
        exchangeRates.put(currency, rate);
    }

    public void transferFrom(String assetSymbol, double amount, Account sender, Account receiver) {
        if (assetSymbol.equals("ETH")) {
            if (sender.sendETH(amountIn, exchange.getAddress())) {
                exchange.receiveETH(amountIn);
                returnAssetsUSDT(sender);
            }
        } else if (assetSymbol.equals("USDT")) {
            if (sender.sendUSDT(amountIn, exchange.getAddress())) {
                exchange.receiveUSDT(amountIn);
                returnAssetsETH(sender);
            }
        }
    }

    public String getInternalInfo() {
        String s = "SwapTx \n";
        s += "Sender:\t\t" + super.getSender().getAddress() + "\n";
        s += "Receiver:\t" + super.getReceiver().getAddress() + "\n";
        s += "AmountIn:\t" + amountIn + " " + tokenIn + "\n";
        s += "AmountOut:\t" + amountOut(tokenOut) + " " + tokenOut + "\n";
        return s;
    }

    public double amountOut(String asset) {
        return amountIn * exchangeRates.get(asset);
    }

    public void returnAssetsUSDT(Account sender) {
        if (exchange.getBalanceUSDT() < amountOut("USDT")) {
            System.out.println("Exchange " + exchange.getAddress() + " doesn't have enough USDT for swap!");
            exchange.sendETH(amountIn, sender.getAddress());
            sender.receiveETH(amountIn);
            return;
        }
        System.out.println("Amount USDT out: " + amountOut("USDT"));
        exchange.sendUSDT(amountOut("USDT"), sender.getAddress());
        sender.receiveUSDT(amountOut("USDT"));
    }

    public void returnAssetsETH(Account sender) {
        if (exchange.getBalanceETH() < amountOut("ETH")) {
            System.out.println("Exchange " + exchange.getAddress() + " doesn't have enough ETH for swap!");
            exchange.sendUSDT(amountIn, sender.getAddress());
            sender.receiveUSDT(amountIn);
            return;
        }
        ;
        System.out.println("Amount ETH out: " + amountOut("ETH"));
        exchange.sendETH(amountOut("ETH"), sender.getAddress());
        sender.receiveETH(amountOut("ETH"));
    }
}
