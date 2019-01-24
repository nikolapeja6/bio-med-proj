# bio-med

School project for the course [*'Računarstvo u bio-medicini'*][rbm] (en. [*Computers in Biomedicine*][rbm]) of my master studies at the [School of Electrical Engineering][school], [University of Belgrade][uni].

The Java eclipse project represents a tool for calculating medical diagnostics based on predefined rules and a patient’s data. It is composed of three parts:
 - Parser - reads the rule specification, constructs a syntax tree and a table of symbols.
 - Evaluator Engine - uses the syntax tree and table of symbols and the patient parameters the user has provided and evaluates the states defined by the rules.
 - REST interface - allows the user to get a list of all the states that the system can evaluate, get a list of all the parameters needed for the evaluation of a state, and evaluate a state (or all states) based on the provided data. 

[rbm]: https://www.etf.bg.ac.rs/en/fis/karton_predmeta/MS1RBM
[school]: https://www.etf.bg.ac.rs/
[uni]: http://www.bg.ac.rs/en/
