function getAsciiCodesInRange(charA, charB) {
    let asciiCodeA = charA.charCodeAt(0);
    let asciiCodeB = charB.charCodeAt(0);

    let floor = Math.min(asciiCodeA, asciiCodeB);
    let ceil = Math.max(asciiCodeA, asciiCodeB);
    let diff = ceil - floor;

    return [...Array(diff - 1).keys()]
        .map(code => String.fromCharCode(code + 1 + floor))
        .join(" ");

}

console.log(getAsciiCodesInRange('A', 'Z'));