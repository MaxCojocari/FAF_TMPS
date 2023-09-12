# Topic: SOLID Principles

## Author: Cojocari-Goncear Maxim

---

## Objectives

- Explore and grasp the SOLID Principles thoroughly.

- Select a specific field, outline its primary classes/models/entities, and determine the suitable methods for creating instances.

- Develop a project that adheres to the SOLID Principles.

## Used Solid Principles

All SOLID principles were implemented in this project accordingly.

## Implementation

### Concept Implementation

This project implements a simple analogue of a blockchain system that can deal with transactions, accounts and their balances.

The main entities of this system are actors who transact cryptoassets, miners, transactions, transaction pools, cryptoassets, blocks and blockchain itself. They can be enumerated as follows:

- Actors:
  - Externally owned accounts (senders, receivers)
  - Smart-contract accounts
  - Miners
- Core architectural elements:
  - Block
  - Blockchain
  - Transaction (transfer or swap of crypto)
  - Transaction pool
  - Cryptoassets (ETH, USDT)
- Cryptographic utilities:
  - MerkleTree
  - MerkleLeaf
  - HashGenerator (SHA1, SHA256)

The `Main` class is responsible for handling all instantiation processes and running the simulation. If we want to modify how the simulation behaves, we should begin by configuring the initial parameters to produce varied behavior and generate fresh results. These adjustments can also be performed within the `Main` class.

```
public class Main {
    static int maxNrUsers = 6;
    static int maxNrLiqPools = 10;
    static int maxNrVaults = 5;
    static int maxAmountAssetsRand = 1;
    static int maxAmountAssetsUser = 10;
    static int nrBlocks = 3;
    static int poolSize = 2;
    static int difficulty = 2;
    ...
}
```

To exemplify, a part from the implementation of a the Blockchain class is shown below. It implements the `IBlockchain` interface which didactates the main features and responsabilities.

```
public class Blockchain implements IBlockchain {
    private ArrayList<Block> blockchain;

    public Blockchain() {
        blockchain = new ArrayList<Block>();
    }

    public void addBlock(Block block) {
        if (blockchain.size() == 0) {
            if (validateBlock(null, block))
                blockchain.add(block);
        } else {
            Block lastBlock = getPrevBlock();
            if (validateBlock(lastBlock, block))
                blockchain.add(block);
        }
    }

    public boolean validateBlock(Block lastBlock, Block newBlock) {...}

    public boolean validateBlockchain() {...}

    public void getBlocks() {...}

    public Block getPrevBlock() {...}
}
```

In blockchain, an _account_ is a digital entity that holds assets or interacts with the network. There are two main types: Externally Owned Accounts (EOAs) for managing cryptocurrency, and Contract Accounts for executing smart contracts and dApps.

Example of implementation of EOA:

```
public class ExternallyOwnedAccount extends Account {
    private double balanceUSDT;

    public ExternallyOwnedAccount(String address, double balanceETH, double balanceUSDT) {
        super(address, 0, balanceETH);
        this.address = address;
        this.nonce = 0;
        this.balanceETH = balanceETH;
        this.balanceUSDT = balanceUSDT;
    }

    public boolean sendETH(double amount, String to) {
        if (balanceETH < amount) {
            System.out.println("INSUFFICIENT_ETH_BALANCE" + "\n");
            return false;
        }
        if (amount <= 0)
            return false;
        balanceETH -= amount;
        return true;
    }

    public boolean receiveETH(double amount) {
        if (amount <= 0)
            return false;
        balanceETH += amount;
        return true;
    }

    public boolean sendUSDT(double amount, String to) {
        if (balanceUSDT < amount) {
            System.out.println("INSUFFICIENT_USDT_BALANCE" + "\n");
            return false;
        }
        ...
    }

    public boolean receiveUSDT(double amount) {...}
    ...
}
```

The actor hierarchy illustrates inheritance as shown below. The abstract class 'Account' establishes a generic type that serves as a means within the system to separate specific implementations from the fundamental components that rely on it.

![image](https://github.com/MaxCojocari/FAF_TMPS/assets/92053176/7aa42823-bc6a-4ef6-92df-66c041b8d68f)

To gain more insight into the implementation, please examine other classes located within the `src` directory.

<!-- After running the simulation, its outcome should resemble the following:

```
Balances before
0x22c76e7ef6390a3563b029f0f27fb1b9ffc64967
3.7228629067511756 ETH
3.2646382192395063 USDT
0xa43a834beb848f032b520ffb799b44a150d125e8
2.361564985841599 ETH
1.2725895999973313 USDT
0x94f23c4938b7aff2fa3b64a218417fde1dfa1bce
7.153921210627705 ETH
5.258833872127921 USDT
0xd3c0812c993cb551e1e3f028e5cb9bfc65c0b002
9.989205143868432 ETH
7.416624977930933 USDT
0xdb0d26ed6f258bf7a4663120302162c5b2bfd52e
4.362099294308711 ETH
9.523976028216552 USDT
0x5b09c10a2a12eb2618639637c4451ca91f734942
6.942895148162393 ETH
6.571409430239784 USDT


TransferTx
Sender:         0xdb0d26ed6f258bf7a4663120302162c5b2bfd52e
Receiver:       0x5b09c10a2a12eb2618639637c4451ca91f734942
Amount:         0.9479818689466682 ETH

------------------------------------------------------
TransferTx
Sender:         0xdb0d26ed6f258bf7a4663120302162c5b2bfd52e
Receiver:       0x5b09c10a2a12eb2618639637c4451ca91f734942
Amount:         0.5034641986962498 USDT

------------------------------------------------------
TransferTx
Sender:         0xdb0d26ed6f258bf7a4663120302162c5b2bfd52e
Receiver:       0x5b09c10a2a12eb2618639637c4451ca91f734942
Amount:         0.24075028752605288 ETH

------------------------------------------------------
TransferTx
Sender:         0xdb0d26ed6f258bf7a4663120302162c5b2bfd52e
Receiver:       0x5b09c10a2a12eb2618639637c4451ca91f734942
Amount:         0.027097329198724762 USDT

------------------------------------------------------
Amount USDT out: 0.6261229645666795
SwapTx
Sender:         0x94f23c4938b7aff2fa3b64a218417fde1dfa1bce
Receiver:       0x28bc722bd7ca4106c86fd33f89dbad3537c38136
AmountIn:       0.963266099333353 ETH
AmountOut:      0.6261229645666795 USDT

------------------------------------------------------
TransferTx
Sender:         0xdb0d26ed6f258bf7a4663120302162c5b2bfd52e
Receiver:       0x5b09c10a2a12eb2618639637c4451ca91f734942
Amount:         0.03229677364102024 ETH

------------------------------------------------------
Block # 0
PreviousHash:   null
Timestamp:      1694501461605
Nonce:          144
CurrentHash:    006b8c07da684d95e3948fa8837cda05b65c088bb376aace59d357c7c95459ec
Merkle root:    e24e9c7fa2477be3d68d9357d9a7787af1606aa964f19e889c0ae7d1f2e58eaa

Block # 1
PreviousHash:   006b8c07da684d95e3948fa8837cda05b65c088bb376aace59d357c7c95459ec
Timestamp:      1694501461641
Nonce:          529
CurrentHash:    00c510e0ca2e76a45b726067db65882b242b0d91a1e3b1c2de12ee1097f4a45d
Merkle root:    e24e9c7fa2477be3d68d9357d9a7787af1606aa964f19e889c0ae7d1f2e58eaa

Block # 2
PreviousHash:   00c510e0ca2e76a45b726067db65882b242b0d91a1e3b1c2de12ee1097f4a45d
Timestamp:      1694501461664
Nonce:          579
CurrentHash:    00e41b58f1f31c7b782211078a47cbfcb1298beef4cb6306ceebe6db94902c8b
Merkle root:    285c012de7f3710f1440d0acd3a17029cb21fc1b8397883a7d0f2687f8aa3236

Balances after
0x22c76e7ef6390a3563b029f0f27fb1b9ffc64967
3.7228629067511756 ETH
3.2646382192395063 USDT
0xa43a834beb848f032b520ffb799b44a150d125e8
2.361564985841599 ETH
1.2725895999973313 USDT
0x94f23c4938b7aff2fa3b64a218417fde1dfa1bce
6.190655111294352 ETH
5.884956836694601 USDT
0xd3c0812c993cb551e1e3f028e5cb9bfc65c0b002
9.989205143868432 ETH
7.416624977930933 USDT
0xdb0d26ed6f258bf7a4663120302162c5b2bfd52e
3.1410703641949698 ETH
8.993414500321578 USDT
0x5b09c10a2a12eb2618639637c4451ca91f734942
8.163924078276134 ETH
7.101970958134759 USDT
``` -->

### Implementation of SOLID principles

#### Single Responsibility Principle

Each class in the system has a single responsibility, meaning it has only one specific reason to undergo changes. For instance, the core architecture is broken down into fundamental classes, such as:

- `Transaction` - represents a single transaction and contain information about the sender, recipient and amount;
- `Block` - represents an individual block in the blockchain;
- `TransactionPool` - manages the unconfirmed transactions and handle their inclusion in the next block;
- ...

#### Open-Closed Principle

Additional features can be incorporated into the existing ones without needing to alter the existing class content. To illustrate, if you wish to introduce a new transaction type, it is enough to extend the abstract `Transaction` class and implement the relevant abstract methods.

#### Liskov Substitution Principle

This design conforms to the LSP because the behavior of the main application does not depend, for example, on which of the two subtypes it uses `ExternallyOwnedAccount` or `ContractAccount` to manipulate balances. Both of the subtypes are substitutable for the `Account` type.

#### Interface Segregation Principle

Larger interfaces are splited into smaller ones. Just look into the `src/core/interface` directory, it contains core logic crystalized into multiple interfaces `IBlock`, `ITransaction`, `ITransactionPool`, ..., the child classes are free to implement only the methods that matter to them.

#### Dependency Inversion Principle

The process of separating software modules is achieved here through the use of distinct abstraction layers. For example, the `MerkleTree` class is independent of the specific transaction types used to create the Merkle root for the block. By employing the `ITransaction` interface, we eliminate the close interdependence between entities.

```
public class MerkleTree implements Tree {
    private ArrayList<ITransaction> leaves;
    private ArrayList<Leaf> leavesObjects = new ArrayList<Leaf>();

    public MerkleTree(ArrayList<ITransaction> leaves) {
        this.leaves = leaves;
    }

    public MerkleLeaf getRoot() {
        Queue<Leaf> queue = new LinkedList<Leaf>();

        for (ITransaction t : leaves) {
            queue.add(new MerkleLeaf(HashGenerator.computeSha256Hash(t.getInternalInfo()), null, null));
        }
        ...
    }
    ...
}
```

## Conclusions

This laboratory work has provided valuable insights into the SOLID principles of software design, which are fundamental to creating maintainable, flexible, and robust software systems.

By adhering to these principles, I learned how to enhance the modularity, reusability, and extensibility of my code. This promotes a more organized and easily maintainable codebase, reducing the risk of unintended side effects when implementing changes.

Furthermore, the laboratory work has demonstrated the importance of designing software with future scalability and adaptability in mind. By focusing on single responsibilities, open-closed principles, and other SOLID concepts, we lay the foundation for software that can accommodate new features and requirements without significant modifications to existing code.

To summarise, the knowledge gained from this laboratory work equips me with essential skills for producing high-quality software systems that can evolve with changing demands and remain resilient in the face of software development challenges.
