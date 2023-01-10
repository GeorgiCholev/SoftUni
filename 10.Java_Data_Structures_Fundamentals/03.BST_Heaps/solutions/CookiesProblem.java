package solutions;

import model.Cookie;

import java.util.Arrays;

public class CookiesProblem {

    public Integer solve(int k, int[] cookies) {
        if (cookies.length == 0) {
            return -1;
        }

        MinHeap<Cookie> cookiesHeap = new MinHeap<>();
        Arrays.stream(cookies).forEach(i -> cookiesHeap.add(new Cookie(i)));

        int steps = 0;
        while (cookiesHeap.peek().getSweetness() < k) {
            if (cookiesHeap.size() == 1) {
                return -1;
            }

            int leastSweet = cookiesHeap.poll().getSweetness();
            int secondLeastSweet = cookiesHeap.poll().getSweetness();
            cookiesHeap.add(new Cookie(leastSweet + (2 * secondLeastSweet)));
            steps++;
        }

        return steps;
    }
}
