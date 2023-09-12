package core;

import actors.ExternallyOwnedAccount;

import java.util.*;

public class SwapTransaction extends Transaction {
    public ExternallyOwnedAccount exchange;
    public String tokenIn;
    public String tokenOut;
    public double amountIn;
    private Map<String, Double> exchangeRates = new HashMap<String, Double>();

    public SwapTransaction(
            ExternallyOwnedAccount sender,
            ExternallyOwnedAccount exchange,
            String tokenIn,
            String tokenOut,
            double amountIn) {
        super(sender, exchange);
        this.exchange = exchange;
        this.tokenIn = tokenIn;
        this.tokenOut = tokenOut;
        this.amountIn = amountIn;
        exchangeRates.put("ETH", 0.5);
        exchangeRates.put("USDT", 0.65);

        if (tokenIn.equals("ETH")) {
            if (sender.sendETH(amountIn, exchange.getAddress())) {
                exchange.receiveETH(amountIn);
                returnAssetsUSDT(sender);
            }
        } else if (tokenIn.equals("USDT")) {
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

    public void returnAssetsUSDT(ExternallyOwnedAccount sender) {
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

    public void returnAssetsETH(ExternallyOwnedAccount sender) {
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
