function controlPieceCollection(input) {
    let pianoPiecesByName = {};

    let n = input.shift();
    for (let i = 0; i < n; i++) {
        let [piece, composer, key] = input[i].split("|");
        pianoPiecesByName[piece] = {composer, key};
    }

    let commands = {
        "Add": addPiece,
        "Remove": removePiece,
        "ChangeKey": changeKey
    };

    for (let i = n; i < input.length - 1; i++) {
        let components = input[i].split("|");
        let command = components.shift();
        console.log(commands[command](components));
    }

    printPieces();


    function addPiece(components) {
        let [piece, composer, key] = components;
        let output = piece + " is already in the collection!";

        if (!pianoPiecesByName.hasOwnProperty(piece)) {
            pianoPiecesByName[piece] = {composer, key};
            output = piece + " by " + composer + " in " + key + " added to the collection!"
        }
        return output;
    }

    function removePiece(components) {
        let [piece] = components;
        let output = "Invalid operation! " + piece + " does not exist in the collection."

        if (pianoPiecesByName.hasOwnProperty(piece)) {
            delete pianoPiecesByName[piece];
            output = "Successfully removed " + piece + "!"
        }
        return output;
    }

    function changeKey(components) {
        let [piece, newKey] = components;
        let output = "Invalid operation! " + piece + " does not exist in the collection."

        let entry = pianoPiecesByName[piece];
        if (entry) {
            entry.key = newKey;
            output = "Changed the key of " + piece + " to " + newKey + "!"
        }
        return output;
    }

    function printPieces() {
        Object.entries(pianoPiecesByName)
            .forEach(p => console.log(p[0] + " -> Composer: " + p[1]["composer"] + ", Key: " + p[1]["key"]))
    }

}

controlPieceCollection([
        '3',
        'Fur Elise|Beethoven|A Minor',
        'Moonlight Sonata|Beethoven|C# Minor',
        'Clair de Lune|Debussy|C# Minor',
        'Add|Sonata No.2|Chopin|B Minor',
        'Add|Hungarian Rhapsody No.2|Liszt|C# Minor',
        'Add|Fur Elise|Beethoven|C# Minor',
        'Remove|Clair de Lune',
        'ChangeKey|Moonlight Sonata|C# Major',
        'Stop'
    ]
);