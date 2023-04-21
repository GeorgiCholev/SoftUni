function calcPersonalNumber(people) {
    let personalNumberByPerson = {};

    people.forEach(p => personalNumberByPerson[p] = p.length);

    for (const key in personalNumberByPerson) {
        console.log(`Name: ${key} -- Personal Number: ${personalNumberByPerson[key]}`);
    }
}

calcPersonalNumber([
    'Silas Butler',
    'Adnaan Buckley',
    'Juan Peterson',
    'Brendan Villarreal'
]);