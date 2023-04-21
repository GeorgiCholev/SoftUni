function printEveryNthElement(arr, n) {
    let everyNthElement = [];
    for (let i = 0; i < arr.length; i+=n) {
        everyNthElement.push(arr[i]);
    }

    return everyNthElement;
}

printEveryNthElement(['5', '20', '31', '4', '20'], 2);
printEveryNthElement(['dsa', 'asd', 'test', 'tset'], 2);

