package core.handlers;

import core.interfaces.IBlock;

public abstract class Handler {
    private Handler next;

    public static Handler link(Handler[] chain) {
        Handler head = chain[0];
        for (int i = 1; i < chain.length; ++i) {
            head.next = chain[i];
            head = chain[i];
        }
        return head;
    }

    public abstract boolean check(IBlock lastBlock, IBlock newBlock);

    protected boolean checkNext(IBlock lastBlock, IBlock newBlock) {
        if (next == null) {
            return true;
        }
        return next.check(lastBlock, newBlock);
    }
}
