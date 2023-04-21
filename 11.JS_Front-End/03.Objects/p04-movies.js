function printMovieInfo(actions) {
    let moviesByName = {};

    for (const action of actions) {
        if (action.includes("addMovie ")) {
            let movieName = action.split("addMovie ")[1];
            moviesByName[movieName] = {name: movieName};
        } else if (action.includes(" directedBy ")) {
            let components = action.split(" directedBy ");
            let movieName = components[0];
            let director = components[1];

            if (moviesByName.hasOwnProperty(movieName)) {
                moviesByName[movieName]["director"] = director;
            }
        } else {
            let components = action.split(" onDate ");
            let movieName = components[0];
            let date = components[1];
            let movieObj = moviesByName[movieName];

            if (movieObj !== undefined) {
                movieObj["date"] = date;
            }
        }
    }

    Object.keys(moviesByName)
        .filter(movie => moviesByName[movie].hasOwnProperty("name") &&
                moviesByName[movie].hasOwnProperty("director") &&
                moviesByName[movie].hasOwnProperty("date")
        ).forEach(movie => console.log(JSON.stringify(moviesByName[movie])));
}


printMovieInfo(
    [
        'addMovie The Avengers',
        'addMovie Superman',
        'The Avengers directedBy Anthony Russo',
        'The Avengers onDate 30.07.2010',
        'Captain America onDate 30.07.2010',
        'Captain America directedBy Joe Russo']);