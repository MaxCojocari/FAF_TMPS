package core.transactions.decorators;

import core.transactions.interfaces.Transaction;
import cryptography.HashGenerator;

public class MetadataDecorator extends TransactionPageDecorator {
    public MetadataDecorator(ITransactionDetailsPage decoratedPage, Transaction transaction) {
        super(decoratedPage, transaction);
    }

    public void displayMetadata() {
        String inputBytecode = HashGenerator.computeSha256Hash(super.transaction.getInternalInfo());
        String inputData = "Metadata:\t" + inputBytecode + "\n";
        System.out.println(inputData);
    }

    public void display() {
        super.display();
        displayMetadata();
    }
}
