function printWhetherPalindrome(arrNumbers) {
    function isPalindrome(number) {
        let numString = number.toString();
        for (let i = 0, j = numString.length - 1; i < numString.length / 2; i++, j--) {
            if (numString.charAt(i) !== numString.charAt(j)) {
                return false;
            }
        }

        return true;
    }

    for (const number of arrNumbers) {
        console.log(isPalindrome(number));
    }
}

printWhetherPalindrome([32,2,232,1010]);
printWhetherPalindrome([123,323,421,121]);