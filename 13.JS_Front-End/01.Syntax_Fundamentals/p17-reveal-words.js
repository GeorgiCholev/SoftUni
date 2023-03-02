function revealWords(wordsSeparatedByCommaSpace, text) {

    let words = wordsSeparatedByCommaSpace.split(", ");

    for (let i = 0; i < text.length; i++) {

        let starWordLength = 0;
        while (text.charAt(i) === "*") {
            i++;
            starWordLength++;
        }

        if (starWordLength !== 0) {
            for (const word of words) {
                if (word.length === starWordLength) {
                    text = text.substring(0, i - starWordLength) + word + text.substring(i);
                    break;
                }
            }
            starWordLength = 0;
            i--;
        }
    }

    console.log(text);
}

revealWords('great, learning',
    'softuni is ***** place for ******** new programming languages');

revealWords("some, words, to, reveal",
    "text made up of **** ***** that need ** ****** their meaning");