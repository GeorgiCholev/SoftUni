function printAndSum(floor, ceil) {
    let printOutput = [];
    let sum = 0;
    for (let i = floor; i <= ceil; i++) {
        printOutput.push(i);
        sum += i;

    }

    console.log(printOutput.join(" "));
    console.log(`Sum: ${sum}`);
}

printAndSum(5, 10);