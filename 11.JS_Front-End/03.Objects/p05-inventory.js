function printHeroInventory(heroRegister) {

    let inventory = {};
 // Map<heroLevel, Entry> inventory;

    for (const entry of heroRegister) {

        let components = entry.split(" / ");
        let heroLevel = components[1];
        let heroName = components[0];
        let items = components[2];

        inventory[heroLevel] = {
            heroName, heroLevel, items
        };
    //  inventory.put(heroLevel, new Entry(heroName, heroLevel, items))
    }

    console.log(Object.entries(inventory)
        //      inventory.entrySet()
        .sort((e1, e2) => e1[0] - e2[0])
        // .sort((e1, e2) -> e1.getKey().compareTo(e2.getKey()))
        .map(e => "Hero: " + e[1].heroName + "\n" +
            "level => " + e[1].heroLevel + "\n" +
            "items => " + e[1].items)
        .join("\n"));

}

// printHeroInventory([
//     'Isacc / 25 / Apple, GravityGun',
//     'Derek / 12 / BarrelVest, DestructionSword',
//     'Hes / 1 / Desolator, Sentinel, Antara'
// ])

printHeroInventory([
    'Batman / 2 / Banana, Gun',
    'Superman / 18 / Sword',
    'Poppy / 28 / Sentinel, Antara'
]);