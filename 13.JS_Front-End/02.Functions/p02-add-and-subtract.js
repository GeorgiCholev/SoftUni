function addThenSubtract(numOne, numTwo, numThree) {

    return subtractTwoNumbers(addTwoNumbers(numOne, numTwo), numThree);
    function addTwoNumbers(numOne, numTwo) {
        return numOne + numTwo;
    }

    function subtractTwoNumbers(numOne, numTwo) {
        return numOne - numTwo;
    }
}

console.log(addThenSubtract(1,
    17,
    30))