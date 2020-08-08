# Bio-Med Proj

School project for the course [Bio-Med (Računarstvo u Bio-Medicini, en. Computers in Biomedicine) course][rbm] which is part of the Master studies at the [School of Electrical Engineering][school], [University of Belgrade][uni].

The Java eclipse project represents a tool for calculating medical diagnostics based on predefined rules and a patient’s data. It is composed of three parts:
 - Parser - reads the rule specification, constructs a syntax tree and a table of symbols. Code for the Parser is located in the `workspace/MJCompiler` folder and represent a stripped-down version of the MicroJava compiler.
 - Evaluator Engine - uses the syntax tree and table of symbols and the patient parameters the user has provided and evaluates the states defined by the rules. code for the Evaluator is also located in the `workspace/MJCompiler` folder and is an extension of the Parser.
 - REST interface - allows the user to get a list of all the states that the system can evaluate, get a list of all the parameters needed for the evaluation of a state, and evaluate a state (or all states) based on the provided data. Code for the REST interface is located in the `workspace/biomed` folder.
 
An example of how to interact with the REST inteface which was used for testing during development is in the [rest_client.linq][rest-client] [LINQPad][linqpad] file.
 
The project didn't have a predefined statement. The final report describing the solution is given in Serbian in [Izvestaj.pdf][report] in the `docs` folder.

[rbm]: https://www.etf.bg.ac.rs/en/fis/karton_predmeta/MS1RBM
[school]: https://www.etf.bg.ac.rs/
[uni]: http://www.bg.ac.rs/en/
[report]: ./docs/Izvestaj.pdf
[rest-client]: ./rest_client.linq
[linqpad]: https://www.linqpad.net/
