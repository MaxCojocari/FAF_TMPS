# Topic: Behavioral Design Patterns

## Author: Cojocari-Goncear Maxim

## Theory

Behavioral design patterns represent a group within design patterns dedicated to facilitating interactions and communications among objects and classes. These patterns aim to structure the actions of objects in a manner that is both adaptable and repeatable, distancing the duties of the objects from the intricacies of their specific implementations. They tackle typical issues in the realm of object behavior, including the organization of object interactions, the management of message exchanges between objects, and the establishment of algorithms and policies for broad application across various objects and classes.

A few notable examples of behavioral design patterns include:

- Chain of Responsibility
- Command
- Interpreter
- Iterator
- Mediator
- Observer
- Strategy

## Objectives

- Studying Behavioral Design Patterns;

- Following up from the last lab work, consider the types of interactions that may occur among different components within your system;

- Initiate a new Project or enhance existing features by implementing behavioral design patterns.

## Implmentation

In this laboratory work were implemented three Behavioral Design Patterns:

1. Iterator
2. Chain of Responsability
3. Observer

### Iterator

Components invoved:

- [`Iterator`](../src/core/interfaces/Iterator.java)
- [`BlockchainIterator`](../src/core/BlockchainIterator.java)
- [`IBlockchain`](../src/core/interfaces/IBlockchain.java)
- [`Blockchain`](../src/core/Blockchain.java)

The Iterator Pattern offers a standardized way to sequentially access elements within a collection without exposing the collection's underlying structure. It introduces an interface that simplifies navigation through a collection and manages the iteration process.

In the given `BlockchainIterator` class, this pattern is utilized to abstract the complexity of navigating through blockchain blocks. This class serves as an iterator over a blockchain's blocks.

For instance, the `BlockchainIterator` class provides a coherent and straightforward interface (`getNextBlock` and `hasMore` methods) for clients. The clients are not required to engage directly with the complex mechanisms of block retrieval and iteration logic. Instead, they can easily navigate through the blocks in the blockchain, while the `BlockchainIterator` handles the details of accessing and loading the blocks, as well as maintaining the current position in the sequence.

![image](https://github.com/MaxCojocari/FAF_TMPS/assets/92053176/1de0673b-96dd-4fd2-9c85-94a57265c359)

### Chain of Responsability

Components involed:

- [`Handler`](../src/core/handlers/Handler.java)
- [`HashHandler`](../src/core/handlers/HashHandler.java)
- [`IndexHandler`](../src/core/handlers/IndexHandler.java)
- [`TimestampHandler`](../src/core/handlers/TimestampHandler.java)
- [`Blockchain`](../src/core/Blockchain.java)

The Chain of Responsibility Pattern involves decoupling the sender of a request from its receivers by allowing multiple objects to handle the request. This pattern chains the receiving objects and passes the request along the chain until an object handles it. It simplifies object interconnections and promotes loose coupling.

For example, in the blockchain context, distinct validation steps like hash checking, index verification, and timestamp validation are encapsulated in separate handler classes (`HashHandler`, `IndexHandler`, `TimestampHandler`). Each class extends the abstract `Handler` class and overrides the `check` method to implement its specific validation logic.

The `Handler` class plays a pivotal role in establishing the chain, with its static `link` method connecting the various handlers into a sequence. This method sequentially links the handlers, creating a chain where a request (in this case, block validation) can travel through each handler.

Each handler, through its `check` method, decides whether to process the request (validate a part of the block) and whether to pass the request down the chain. The decision-making process is localized within each handler, providing clear separation and modularity. The `checkNext` method in the `Handler` class facilitates the continuation of the chain, calling the next handler's `check` method if it exists.

This structure allows for dynamic addition or removal of validation steps without altering the existing handlers or the client code. The client initiating the validation needs only to interact with the head of the chain, unaware of the specific handlers and their ordering, thus simplifying the client interface and enhancing flexibility in the validation process.

![image](https://github.com/MaxCojocari/FAF_TMPS/assets/92053176/f718ae08-1fc2-483c-a7bd-5bf5653ec81c)

### Observer

Components involved:

- [`Observer`](../src/core/observers/Observer.java)
- [`AssetTransferObserver`](../src/core/observers/AssetTransferObserver.java)
- [`NewAccountObserver`](../src/core/observers/NewAccountObserver.java)
- [`Subject`](../src/core/interfaces/Subject.java)
- [`BlockchainService`](../src/core/BlockchainService.java)

![image](https://github.com/MaxCojocari/FAF_TMPS/assets/92053176/3b8cf7c4-141d-4290-af10-f26a4eb68869)

The Observer Pattern is a design pattern where an object, known as a subject, maintains a list of its dependents, called observers, and notifies them automatically of any state changes, usually by calling one of their methods. It is mainly used to implement distributed event handling systems and promotes a loose coupling between the subject and its observers.

This implementation demonstrates the Observer Pattern’s key characteristics:

- The subject (`BlockchainService`) maintains a list of observers and provides mechanisms to add or remove them;
- Observers (`NewAccountObserver`, `AssetTransferObserver`) have a common interface (`Observer`) and can react to notifications in their own way;
- There is a clear separation of concerns, where `BlockchainService` focuses on core business logic, and observers focus on responding to the service’s state changes or events.

## Conclusions

Throughout this laboratory work, I immersed myself in the practical application of behavioral design patterns, emphasizing their critical role in the field of blockchain technology. This in-depth engagement provided valuable insights into how these patterns facilitate robust and dynamic interactions within software systems.

In this exploration, I engaged with a variety of behavioral design patterns, including the Observer, Iterator, and Chain of Responsibility. Each pattern offered a unique perspective on managing interactions and communications between objects, demonstrating efficient approaches to handling complex system behaviors.

To conclude, this laboratory work has significantly deepened my understanding of behavioral design patterns, underscoring their importance in creating adaptable, efficient, and coherent software architectures. Through hands-on implementation, particularly in the context of blockchain technology, the practical benefits and real-world applicability of these patterns were made clear. This experience has reinforced their indispensable value in a software engineer's repertoire, providing essential strategies for crafting responsive and flexible software solutions.
