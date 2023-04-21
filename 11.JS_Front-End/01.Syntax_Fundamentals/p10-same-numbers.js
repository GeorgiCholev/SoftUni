function hasSameNumbers(integerNumber) {

    let prevNumber = integerNumber % 10;
    integerNumber = Math.trunc(integerNumber / 10);
    let currentNumber;
    let sum = prevNumber;
    let hasSameNumbers = true;
    while (integerNumber > 0) {
        currentNumber = integerNumber % 10;
        integerNumber = Math.trunc(integerNumber / 10);

        if (hasSameNumbers && currentNumber !== prevNumber) {
            hasSameNumbers = false;
        }

        sum += currentNumber;
        prevNumber = currentNumber;
    }

    console.log(hasSameNumbers);
    console.log(sum);
}

hasSameNumbers(1234)