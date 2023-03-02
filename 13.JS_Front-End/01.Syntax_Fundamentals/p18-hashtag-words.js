function findHashtagWords(text) {
    let words = text.split(" ");

    let usesOnlyLetters = /^[a-zA-Z]/;
    for (const word of words) {
        if (word.startsWith("#")) {
            let wordWithoutHashtag = word.substring(1);
            if (usesOnlyLetters.test(wordWithoutHashtag)) {
                console.log(wordWithoutHashtag);
            }
        }
    }
}

findHashtagWords('Nowadays everyone uses # to tag a #special word in #socialMedia');
findHashtagWords('The symbol # is known #variously in English-speaking #regions as the #number sign');