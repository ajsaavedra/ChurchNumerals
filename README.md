# ChurchNumerals
This is the first graduate lab in the programming languages course.

Using both Scheme and Groovy, we take concepts in functional programming and lambda calculus in 
order to create higher-order functions that can represent natural numbers.

The Church numeral zero is equal to λf . λx . x, where a given function is not applied to a given variable.

The Church numeral one is equal to  λf . λx . f x, where a given function is applied exactly once to a given variable.

The purpose of this lab is to create procedures that

1. Convert a Church numeral into an ordinary number.

2. Convert an ordinary number into a Church numeral.

3. Implement arithmetic such as subtraction and multiplication on any Church numeral.
