function cookingNumbers(input, ...operations) {
    let number = Number(input)

    for (const operation of operations) {
        switch (operation) {
            case "chop" :
                number /= 2;
                break;
            case "dice" :
                number = Math.sqrt(number);
                break;
            case "spice" :
                number++;
                break;
            case "bake" :
                number *= 3;
                break;
            case "fillet" :
                number *= 0.8;
                break;
        }

        if (number % 1 !== 0) {
            number = number.toFixed(1);
        }

        console.log(number);
    }
}

cookingNumbers('32', 'chop', 'chop', 'chop', 'chop', 'chop');
console.log("--------");
cookingNumbers('9', 'dice', 'spice', 'chop', 'bake', 'fillet');