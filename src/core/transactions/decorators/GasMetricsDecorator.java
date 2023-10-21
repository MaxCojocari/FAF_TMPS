package core.transactions.decorators;

import java.util.Random;

import core.transactions.interfaces.Transaction;

public class GasMetricsDecorator extends TransactionPageDecorator {
    public GasMetricsDecorator(ITransactionDetailsPage decoratedPage, Transaction transaction) {
        super(decoratedPage, transaction);
    }

    public void displayGasMetrics() {
        Double gasPrice = getPrice();
        int gasUsage = getGasUsage();
        double gasFees = gasPrice * gasUsage / Math.pow(10, 9);
        String info = "Gas Price:\t" + gasPrice.toString() + "\n";
        info += "Gas Usage:\t" + gasUsage + "\n";
        info += "Gas Fees:\t" + gasFees + " ETH";
        System.out.println(info);
    }

    public void display() {
        super.display();
        displayGasMetrics();
    }

    private Double getPrice() {
        return Math.random() * 100;
    }

    private int getGasUsage() {
        Random rand = new Random();
        return rand.nextInt(1_000_000);
    }
}
