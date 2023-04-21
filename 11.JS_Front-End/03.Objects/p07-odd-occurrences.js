function printWordsWithOddOccurrence(input) {
    let wordsByCount = {};

    input.split(" ")
        .forEach(w => {
            let key = w.toLowerCase();
            if (wordsByCount.hasOwnProperty(key)) {
                wordsByCount[key] = wordsByCount[key] + 1;
            } else {
                wordsByCount[key] = 1;
            }
        });

    console.log(
        Object.entries(wordsByCount)
            .filter(e => e[1] % 2 !== 0)
            .map(e => e[0])
            .join(" "));
}

printWordsWithOddOccurrence('Java C# Php PHP Java PhP 3 C# 3 1 5 C#');

printWordsWithOddOccurrence('Cake IS SWEET is Soft CAKE sweet Food');