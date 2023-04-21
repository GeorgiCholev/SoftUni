function printDriverRoadSituation(kilometresPerHour, area) {
    let limit;

    switch (area) {
        case "motorway":
            limit = 130;
            break;
        case "interstate":
            limit = 90;
            break;
        case "city":
            limit = 50;
            break;
        case "residential":
            limit = 20;
            break;
    }

    switch (kilometresPerHour <= limit) {
        case true:
            console.log(`Driving ${kilometresPerHour} km/h in a ${limit} zone`);
            break;
        case false:
            let speeding = kilometresPerHour - limit;
            let status = speeding <= 20 ? "speeding" : speeding <= 40 ? "excessive speeding" : "reckless driving";

            console.log(`The speed is ${speeding} km/h faster than the allowed speed of ${limit} - ${status}`);
            break;
    }
}

printDriverRoadSituation(40, 'city');
printDriverRoadSituation(21, 'residential');
printDriverRoadSituation(120, 'interstate');
printDriverRoadSituation(200, 'motorway');