function sortAlternatingMinMax(numbers) {

    numbers.sort((a, b) => a - b);
    let alternatingNumbers = [];
    let iterations = Math.trunc(numbers.length / 2);
    for (let i = 0; i < iterations; i++) {
        alternatingNumbers.push(numbers[i]);
        alternatingNumbers.push(numbers[numbers.length - 1 - i]);
    }
    for (let i = 0, j = numbers.length - i; i > numbers.length - 1; i++, j--) {

    }

    if (numbers.length % 2 !== 0) {
        alternatingNumbers.push(numbers[iterations]);
    }

    return alternatingNumbers;
}

console.log(sortAlternatingMinMax([1]));
console.log(sortAlternatingMinMax([0, 1, 2, 3, 4, 5, 6, 7, 8, 9]));
console.log(sortAlternatingMinMax([1, 2, 3, 4, 5, 6, 7, 8, 9]));
