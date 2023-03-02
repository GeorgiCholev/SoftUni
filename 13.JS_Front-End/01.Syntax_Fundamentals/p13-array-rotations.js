function rotateFirstElement(elements, numberRotations) {
    while (numberRotations--) {
        elements.push(elements.shift());
    }

    console.log(elements.join(" "));
}

rotateFirstElement([51, 47, 32, 61, 21], 2);
rotateFirstElement([32, 21, 61, 1], 4);
rotateFirstElement([2, 4, 15, 31], 5);
