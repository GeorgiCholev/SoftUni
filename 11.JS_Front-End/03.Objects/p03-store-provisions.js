function calcStoreProvisions(stock, orders) {
    let quantityByProducts = {};
    for (let i = 0; i < stock.length; i += 2) {
        quantityByProducts[stock[i]] = stock[i + 1];
    }

    for (let i = 0; i < orders.length; i += 2) {
        let product = orders[i];
        let quantity = Number(orders[i + 1]);

        if (quantityByProducts.hasOwnProperty(product)) {
            quantityByProducts[product] = quantity + Number(quantityByProducts[product]);
        } else {
            quantityByProducts[product] = quantity;
        }
    }

    for (const product in quantityByProducts) {
        console.log(`${product} -> ${quantityByProducts[product]}`);
    }
}

calcStoreProvisions([
        'Salt', '2', 'Fanta', '4', 'Apple', '14', 'Water', '4', 'Juice', '5'
    ],
    [
        'Sugar', '44', 'Oil', '12', 'Apple', '7', 'Tomatoes', '7', 'Bananas', '30'
    ]);

// calcStoreProvisions([
//         'Chips', '5', 'CocaCola', '9', 'Bananas', '14', 'Pasta', '4', 'Beer', '2'
//     ],
//     [
//         'Flour', '44', 'Oil', '12', 'Pasta', '7', 'Tomatoes', '70', 'Bananas', '30'
//     ]);