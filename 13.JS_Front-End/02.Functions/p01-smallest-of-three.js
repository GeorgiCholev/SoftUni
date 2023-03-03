function getSmallestNumber(...numbers) {
    function getSmallerNumber(numOne, numTwo) {
        return numOne < numTwo ? numOne : numTwo;
    }

    let smallest = Number.MAX_SAFE_INTEGER;
    for (const number of numbers) {
        smallest = getSmallerNumber(smallest, number);
    }

    return smallest;
}

console.log(getSmallestNumber(3, 5, 1, -9, 18, -11));