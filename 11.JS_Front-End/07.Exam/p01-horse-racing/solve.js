function solve(input) {
    let components = input.shift();
    let horses = components.split("|");

    let race = [];
    let positions = {};
    for (let i = 0; i < horses.length; i++) {
        race[i] = horses[horses.length - 1 - i];
        positions[horses[horses.length - 1 - i]] = i;
    }

    for (const command of input) {
        let components = command.split(" ");
        let horse;
        switch (components[0]) {
            case "Retake":
                let overtaking = components[1];
                let overtaken = components[2];

                if (positions[overtaking] > positions[overtaken]) {
                    let firstPosition = positions[overtaken];
                    let secondPosition = positions[overtaking];

                    positions[overtaking] = firstPosition;
                    race[firstPosition] = overtaking;

                    positions[overtaken] = secondPosition;
                    race[secondPosition] = overtaken;

                    console.log(overtaking + " retakes " + overtaken + ".");
                }
                break;
            case "Trouble":
                horse = components[1];
                if (positions[horse] !== race.length - 1) {

                    let firstPosition = positions[horse];
                    let secondPosition = positions[horse] + 1;
                    let secondHorse = race[secondPosition];

                    positions[horse] = secondPosition;
                    race[secondPosition] = horse;

                    positions[secondHorse] = firstPosition;
                    race[firstPosition] = secondHorse;

                    console.log("Trouble for " + horse + " - drops one position.")
                }
                break;
            case "Rage":
                horse = components[1];
                if (positions[horse] === 1) {
                    let firstPosition = 1;
                    let secondPosition = 0;
                    let secondHorse = race[secondPosition];

                    positions[horse] = secondPosition;
                    race[secondPosition] = horse;

                    positions[secondHorse] = firstPosition
                    race[firstPosition] = secondHorse;
                } else if (positions[horse] > 1) {
                    let firstPosition = positions[horse];
                    let secondPosition = positions[horse] - 1;
                    let secondHorse = race[secondPosition];
                    let thirdPosition = positions[horse] - 2;
                    let thirdHorse = race[thirdPosition];

                    positions[horse] = thirdPosition;
                    race[thirdPosition] = horse;

                    positions[secondHorse] = firstPosition;
                    race[firstPosition] = secondHorse;

                    positions[thirdHorse] = secondPosition;
                    race[secondPosition] = thirdHorse;
                }

                console.log(horse + " rages 2 positions ahead.");
                break;
            case "Miracle":
                horse = race[race.length - 1];

                let horseInRow = race[0];
                race[0] = horse;
                positions[horse] = 0;

                let i = 1;

                while (i <= race.length - 1) {
                    let next = race[i];
                    race[i] = horseInRow;
                    positions[horseInRow] = i;
                    horseInRow = next;
                    i++;
                }

                console.log("What a miracle - " + horse + " becomes first.");
                break;
            case "Finish":
                console.log(race.reverse().join("->"));
                console.log("The winner is: " + race[race.length - 1]);
                return;
        }
    }
}

solve((['Bella|Alexia|Sugar',
    'Retake Alexia Sugar',
    'Rage Bella',
    'Trouble Bella',
    'Finish'])
);