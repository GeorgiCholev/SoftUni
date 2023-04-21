function calculatePrice(fruitType, grams, pricePerKilo) {
    let weight = (grams / 1_000);
    let price = (weight * pricePerKilo);
    console.log(
        `I need $${price.toFixed(2)} to buy ${weight.toFixed(2)} kilograms ${fruitType}.`
    );
}

calculatePrice('orange', 2500, 1.80);