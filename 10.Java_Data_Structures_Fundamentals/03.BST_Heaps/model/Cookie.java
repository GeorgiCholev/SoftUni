package model;

import interfaces.Decrease;

public class Cookie implements Decrease<Cookie>, Comparable<Cookie> {

    private int sweetness;

    public Cookie(int sweetness) {
        this.sweetness = sweetness;
    }

    public int getSweetness() {
        return sweetness;
    }

    public void setSweetness(int sweetness) {
        this.sweetness = sweetness;
    }

    @Override
    public void decrease() {
        throw new UnsupportedOperationException("Unsupported");
    }

    @Override
    public int compareTo(Cookie o) {
        return Integer.compare(this.sweetness, o.sweetness);
    }
}
