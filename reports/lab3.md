# Topic: Structural Design Patterns

## Author: Cojocari-Goncear Maxim

## Theory

Structural design patterns are foundational blueprints in software development that focus on simplifying the composition and integration of classes and objects. By offering guidelines on how components should relate and work together, these patterns ensure that systems are scalable, maintainable, and adaptable. They provide developers with proven solutions for building complex structures, promoting efficient code reuse and flexible system architecture.

Structural design patterns include:

- Adapter
- Bridge
- Composite
- Decorator
- Facade
- Flyweight
- Proxy

Each of these patterns offers a unique solution to specific structural problems, and they can be applied depending on the requirements and constraints of a given software product.

## Objectives

- Learn and understand Structural Design Patterns;

- Define domain classes/entities and select instantiation methods;

- Implement Structural Design Patterns in a prototype project to optimize object composition and relationships.

## Implmentation

In this laboratory work were implemented four Structural Design Patterns:

1. Facade
2. Decorator
3. Composite
4. Proxy

### Facade

Components invoved:

- [`BlockchainService`](../src/core/BlockchainService.java)

The Facade Pattern provides a unified interface to a set of interfaces in a subsystem, making the subsystem easier to use. It defines a higher-level interface that makes the subsystem more accessible and shields clients from the complexities of the subsystem's components.

In the provided `BlockchainService` implementation, the class acts as a facade for the underlying complexities of account creation and asset transfer within a blockchain system.

For example, the `BlockchainService` class offers a simplified and unified interface (`createNewAccount` and `transferAssets` methods) to the client. The client doesn't need to understand or interact with the underlying components like `AccountDirector`, `ExternallyOwnedAccountBuilder`, `ContractAccountBuilder`, or `TransferTransactionCreator`.

![image](https://github.com/MaxCojocari/FAF_TMPS/assets/92053176/1de0673b-96dd-4fd2-9c85-94a57265c359)


### Decorator

Components involed:

- [`ITransactionDetailsPage`](../src/core/transactions/decorators/ITransactionDetailsPage.java)
- [`TransactionDetailsPage`](../src/core/transactions/decorators/TransactionDetailsPage.java)
- [`TransactionPageDecorator`](../src/core/transactions/decorators/TransactionPageDecorator.java)
- [`GasMetricsDecorator`](../src/core/transactions/decorators/GasMetricsDecorator.java)
- [`MetadataDecorator`](../src/core/transactions/decorators/MetadataDecorator.java)

The Decorator Pattern enables the addition of behaviors to objects without impacting others in the same class, using decorator classes to wrap and enhance components.

In this setup, `ITransactionDetailsPage` is the central interface, implemented/extended by the main component `TransactionDetailsPage`, abstract decorator `TransactionPageDecorator` and concrete decorators like `GasMetricsDecorator` and `MetadataDecorator`. While `TransactionDetailsPage` provides basic transaction details, decorators like `GasMetricsDecorator` add gas metrics, and `MetadataDecorator` introduces extra transaction metadata.

![image](https://github.com/MaxCojocari/FAF_TMPS/assets/92053176/f718ae08-1fc2-483c-a7bd-5bf5653ec81c)


### Composite

Components involved:

- [`MerkleNode`](../src/cryptography/MerkleNode.java)
- [`MerkleLeaf`](../src/cryptography/MerkleLeaf.java)
- [`MerkleTree`](../src/cryptography/MerkleTree.java)

The provided classes demonstrate the Composite Pattern by representing a Merkle tree. A Merkle tree, in the context of the blockchain system, is a data structure used to efficiently summarize and verify the integrity of large sets of data.

The Composite Pattern allows you to compose objects into tree structures to represent part-whole hierarchies. It lets clients treat individual objects and compositions of objects uniformly. The pattern consists of three main roles:

- _Component:_ This is the base interface or abstract class that defines the common interface for concrete components and composites. In this context, the `MerkleNode` interface plays the role of the Component.

- _Leaf:_ Represents individual objects in the composition. A leaf has no children in the context of the Composite Pattern. The `MerkleLeaf` class is the Leaf in this scenario. It represents individual nodes in the Merkle tree with a hash value and potential left and right children.

- _Composite:_ Represents a group of components (which can be both other composites and leaves). It typically implements the Component interface and manages child components. The `MerkleTree` class is the Composite in this context. It manages a collection of `MerkleNode` objects (the `leaves` ArrayList) and provides methods to manipulate this collection, like `insert`, `remove`, and `search`.

![image](https://github.com/MaxCojocari/FAF_TMPS/assets/92053176/3b8cf7c4-141d-4290-af10-f26a4eb68869)


### Proxy

Components involved:

- [`IVault`](../src/core/vault/interfaces/IVault.java)
- [`Vault`](../src/core/vault/Vault.java)
- [`VaultProxy`](../src/core/vault/VaultProxy.java)

The Proxy Pattern provides a surrogate or placeholder for another object to control access to it. It can be used for various purposes like lazy initialization, logging, access control, and more.

In this implementation, the concept of a vault in a blockchain system is represented. The `Vault` class is the real object that provides the core functionalities of a vault, such as depositing, withdrawing, and checking the amount of asset deposited.

The `VaultProxy` class acts as the proxy for the `Vault`. It controls access to the `Vault` by checking if a user (represented by an account ID) has the necessary permissions to perform certain operations on the vault. The proxy ensures that only authorized users (owners) can perform sensitive operations like depositing, withdrawing, or freezing the vault.

![image](https://github.com/MaxCojocari/FAF_TMPS/assets/92053176/6a98c7ea-2feb-46a4-8eaf-37a475ed4539)


## Conclusions

In the course of this laboratory work, I delved into practical applications of structural design patterns, with a special focus on their relevance in blockchain technology. This hands-on exploration underscored the pivotal role these patterns play in architecting software solutions that are scalable, maintainable, and efficient.

During this laboratory work, I familiarized myself with various structural design patterns such as the Facade, Decorator, Composite, and Proxy. Each pattern, with its unique approach and benefits, showcased effective strategies for object organization and ensuring system coherence.

To sum up, this laboratory work enriched my understanding of structural design patterns, emphasizing their significance in crafting resilient and flexible software frameworks. The hands-on practical implementations, particularly those centered around blockchain, highlighted the tangible advantages and real-world utility of these patterns, solidifying their position as essential components in a software engineer's toolkit.
