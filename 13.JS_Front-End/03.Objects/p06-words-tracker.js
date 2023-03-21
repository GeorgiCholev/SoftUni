function printSeekedWordsCount(input) {
    let seekedWordsByCount = {};

    let [seekedWords, ...allWords] = input;
    seekedWords.split(" ").forEach(w => seekedWordsByCount[w] = 0);

    allWords.filter(w => seekedWordsByCount.hasOwnProperty(w))
        .forEach(w => seekedWordsByCount[w] = seekedWordsByCount[w] + 1);

    console.log(
        Object.entries(seekedWordsByCount)
            .sort((e1, e2) => e2[1] - e1[1])
            .map(e => `${e[0]} - ${e[1]}`)
            .join("\n")
    );
}

// printSeekedWordsCount(['this sentence',
//     'In', 'this', 'sentence', 'you', 'have', 'to', 'count', 'the', 'occurrences', 'of', 'the', 'words', 'this',
//     'and', 'sentence', 'because', 'this', 'is', 'your', 'task']);

printSeekedWordsCount(['is the',
    'first', 'sentence', 'Here', 'is', 'another', 'the', 'And', 'finally', 'the', 'the', 'sentence']);