function bill(numberOfPeople, groupType, dayOfWeek) {

    let bill;
    switch (dayOfWeek) {
        case "Friday":
            switch (groupType) {
                case "Students":
                    bill = 8.45 * numberOfPeople;

                    if (numberOfPeople >= 30) {
                        bill = bill * 0.85;
                    }
                break;

                case "usiness":
                    if (numberOfPeople >= 30) {
                        let bill = 8.45 * numberOfPeople * 0.85;
                    }
                break;

                case "Students":
                    if (numberOfPeople >= 30) {
                        let bill = 8.45 * numberOfPeople * 0.85;
                    }
                break;
            }
            break;
        case "Saturday":
            break;
        case "Sunday":
            break;
    }
}