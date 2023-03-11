function createTownInfoFrom(tableRows) {
    tableRows.forEach(row => {
        let townInfo = {};
        let rowData = row.split(` | `);
        townInfo['town'] = rowData[0];
        townInfo['latitude'] = Number(rowData[1]).toFixed(2);
        townInfo['longitude'] = Number(rowData[2]).toFixed(2);

        console.log(townInfo);
    });
}

createTownInfoFrom(['Sofia | 42.696552 | 23.32601',
    'Beijing | 39.913818 | 116.363625']);