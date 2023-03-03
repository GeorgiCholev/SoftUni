function calculateOddAndEvenSum(number) {
    let evenSum = 0;
    let oddSum = 0;

    while (number) {
        let lastDigit = number % 10;
        number = Math.trunc(number / 10);

        if (lastDigit % 2 === 0) {
            evenSum += lastDigit;
        } else {
            oddSum += lastDigit;
        }
    }

    console.log(`Odd sum = ${oddSum}, Even sum = ${evenSum}`);
}

calculateOddAndEvenSum(3495892137259234);