package core.transactions;

import java.util.*;

import actors.accounts.Account;

public class SwapTransaction extends BaseTransaction {
    private Account exchange;
    private String tokenIn;
    private String tokenOut;
    private double amountIn;
    private double fee;
    private Map<String, Double> exchangeRates = new HashMap<String, Double>();

    public SwapTransaction(String id, Account sender, Account exchange, double fee, long timestamp, String tokenIn, String tokenOut,
            double amountIn, double ethRate, double usdtRate) {
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
        assert !getTransferDone();

        if (sender.sendAssets(assetSymbol, amount, exchange.getAddress())) {
            exchange.receiveAssets(assetSymbol, amount);
            returnAssets(amount, sender);
        }

        setTransferDone();
    }

    public String getInternalInfo() {
        String s = "Sender:\t\t" + super.getSender().getAddress() + "\n";
        s += "Receiver:\t" + super.getReceiver().getAddress() + "\n";
        s += "AmountIn:\t" + amountIn + " " + tokenIn + "\n";
        s += "AmountOut:\t" + amountOut(tokenOut) + " " + tokenOut;
        return s;
    }

    public double amountOut(String currencyOut) {
        return amountIn * exchangeRates.get(currencyOut) * (1 - fee / 100);
    }

    public void returnAssets(double amountIn, Account sender) {
        if (exchange.getBalance(tokenOut) < amountOut(tokenIn)) {
            System.out.println("Exchange " + exchange.getAddress() + " doesn't have enough " + tokenOut + " for swap!");
            exchange.sendAssets(tokenIn, amountIn, sender.getAddress());
            sender.receiveAssets(tokenIn, amountIn);
            return;
        }
        System.out.println("Amount " + tokenOut + " out: " + amountOut(tokenOut));
        exchange.sendAssets(tokenOut, amountOut(tokenOut), sender.getAddress());
        sender.receiveAssets(tokenOut, amountOut(tokenOut));
    }

    public String getType() {
        return "exchange (" + tokenIn + " -> " + tokenOut + ")";
    }
}
