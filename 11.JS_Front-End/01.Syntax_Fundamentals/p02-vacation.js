function calculateBillStudentsByWeek(numberOfPeople, dayOfWeek) {
    let pricePerStudent;
    switch (dayOfWeek) {
        case "Friday" :
            pricePerStudent = 8.45;
            break;
        case "Saturday" :
            pricePerStudent = 9.8;
            break;
        case "Sunday" :
            pricePerStudent = 10.46;
            break;
    }

    return calculateBillStudents(numberOfPeople, pricePerStudent);
}

function calculateBillStudents(numberOfPeople, pricePerStudent) {
    let studentBill = numberOfPeople * pricePerStudent;
    if (numberOfPeople >= 30) {
        studentBill *= 0.85;
    }
    return studentBill;
}

function calculateBillBusinessByWeek(numberOfPeople, dayOfWeek) {
    let pricePerBusiness;
    switch (dayOfWeek) {
        case "Friday" :
            pricePerBusiness = 10.9;
            break;
        case "Saturday" :
            pricePerBusiness = 15.6;
            break;
        case "Sunday" :
            pricePerBusiness = 16;
            break;
    }

    return calculateBillBusiness(numberOfPeople, pricePerBusiness);
}

function calculateBillBusiness(numberOfPeople, pricePerBusiness) {
    let businessBill = numberOfPeople * pricePerBusiness;
    if (numberOfPeople >= 100) {
        businessBill = businessBill - (pricePerBusiness * 10);
    }
    return businessBill;
}

function calculateBillRegularByWeek(numberOfPeople, dayOfWeek) {
    let pricePerRegular;
    switch (dayOfWeek) {
        case "Friday" :
            pricePerRegular = 15;
            break;
        case "Saturday" :
            pricePerRegular = 20;
            break;
        case "Sunday" :
            pricePerRegular = 22.5;
            break;
    }

    return calculateBillRegular(numberOfPeople, pricePerRegular);
}

function calculateBillRegular(numberOfPeople, pricePerRegular) {
    let regularBill = numberOfPeople * pricePerRegular;
    if (numberOfPeople >= 10 && numberOfPeople <= 20) {
        regularBill *= 0.95;
    }
    return regularBill;
}

function calculateBill(numberPeople, typeOfGroup, dayOfWeek) {
    let bill;
    switch (typeOfGroup) {
        case "Students" :
            bill = calculateBillStudentsByWeek(numberPeople, dayOfWeek);
            break;
        case "Business" :
            bill = calculateBillBusinessByWeek(numberPeople, dayOfWeek);
            break;
        case "Regular" :
            bill = calculateBillRegularByWeek(numberPeople, dayOfWeek);
            break;
    }

    return `Total price: ${bill.toFixed(2)}`;
}


console.log(calculateBill(30,
    "Students",
    "Sunday"));