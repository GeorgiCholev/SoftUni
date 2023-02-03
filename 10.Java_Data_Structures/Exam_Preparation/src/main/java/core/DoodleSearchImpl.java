package core;

import models.Doodle;

import java.util.*;

public class DoodleSearchImpl implements DoodleSearch {

    private final Map<String, Doodle> doodlesById = new HashMap<>();

    private final Map<Boolean, Set<Doodle>> doodlesWhetherAreAds = new HashMap<>();
    private final Map<String, Set<Doodle>> doodlesByTitle = new HashMap<>();

    public DoodleSearchImpl() {
        doodlesWhetherAreAds.put(Boolean.TRUE, new TreeSet<>(revenueDescThenVisitsDesc()));
        doodlesWhetherAreAds.put(Boolean.FALSE, new TreeSet<>(revenueDescThenVisitsDesc()));
    }

    @Override
    public void addDoodle(Doodle doodle) {
        doodlesById.put(doodle.getId(), doodle);

        doodlesWhetherAreAds.get(doodle.getIsAd()).add(doodle);
        doodlesByTitle.computeIfAbsent(doodle.getTitle(), k -> new TreeSet<>(adsFirst_TitleLengthAsc_VisitsDesc())).add(doodle);
    }

    @Override
    public void removeDoodle(String doodleId) {
        Doodle removedDoodle = doodlesById.remove(doodleId);
        if (removedDoodle == null) {
            throw new IllegalArgumentException();
        }

        doodlesWhetherAreAds.get(removedDoodle.getIsAd()).remove(removedDoodle);
        doodlesByTitle.computeIfAbsent(removedDoodle.getTitle(), k -> new TreeSet<>(adsFirst_TitleLengthAsc_VisitsDesc())).remove(removedDoodle);
    }

    @Override
    public int size() {
        return doodlesById.size();
    }

    @Override
    public boolean contains(Doodle doodle) {
        try {
            getDoodle(doodle.getId());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public Doodle getDoodle(String id) {
        Doodle doodle = doodlesById.get(id);
        if (doodle == null) {
            throw new IllegalArgumentException();
        }
        return doodle;
    }

    @Override
    public double getTotalRevenueFromDoodleAds() {
        return doodlesWhetherAreAds.get(Boolean.TRUE)
                .stream()
                .mapToDouble(d -> d.getRevenue() * (d.getVisits() * 1.00))
                .sum();
    }

    @Override
    public void visitDoodle(String title) {
        for (Doodle doodle : doodlesByTitle.get(title)) {
            removeDoodle(doodle.getId());
            doodle.setVisits(doodle.getVisits() + 1);
            addDoodle(doodle);
        }
    }

    @Override
    public Iterable<Doodle> searchDoodles(String searchQuery) {

        TreeSet<Doodle> orderedByQueryRelevance = new TreeSet<>(adsFirst_TitleLengthAsc_VisitsDesc());

        for (Map.Entry<String, Set<Doodle>> entry : doodlesByTitle.entrySet()) {
            if (entry.getKey().contains(searchQuery)) {
                orderedByQueryRelevance.addAll(entry.getValue());
            }
        }
        return orderedByQueryRelevance;
    }

    @Override
    public Iterable<Doodle> getDoodleAds() {
        return doodlesWhetherAreAds.get(Boolean.TRUE);
    }

    @Override
    public Iterable<Doodle> getTop3DoodlesByRevenueThenByVisits() {
        List<Doodle> doodlesAds = new ArrayList<>(doodlesWhetherAreAds.get(Boolean.TRUE));

        List<Doodle> doodlesNotAds = new ArrayList<>(doodlesWhetherAreAds.get(Boolean.FALSE));

        return getTop3ThroughMergeSort(doodlesAds, doodlesNotAds);
    }

    private List<Doodle> getTop3ThroughMergeSort(List<Doodle> doodlesAds, List<Doodle> doodlesNotAds) {
        Doodle[] top3 = new Doodle[3];
        int topIndex = 0;

        int adsIndex = 0;
        int notAdsIndex = 0;

        while (adsIndex < doodlesAds.size() && notAdsIndex < doodlesNotAds.size()) {

            Doodle doodleAd = doodlesAds.get(adsIndex);
            Doodle doodleNotAd = doodlesNotAds.get(notAdsIndex);

            int compare = revenueDescThenVisitsDesc().compare(doodleAd, doodleNotAd);

            if (compare < 0) {
                top3[topIndex] = doodleNotAd;
                notAdsIndex++;
            } else {
                top3[topIndex] = doodleAd;
                adsIndex++;
            }

            topIndex++;

            if (topIndex == 3) {
                return Arrays.stream(top3).toList();
            }
        }

        while (adsIndex < doodlesAds.size()) {
            top3[topIndex] = doodlesAds.get(adsIndex);
            adsIndex++;
            topIndex++;

            if (topIndex == 3) {
                return Arrays.stream(top3).toList();
            }
        }

        while (notAdsIndex < doodlesNotAds.size()) {
            top3[topIndex] = doodlesNotAds.get(notAdsIndex);
            notAdsIndex++;
            topIndex++;

            if (topIndex == 3) {
                return Arrays.stream(top3).toList();
            }
        }

        return Collections.emptyList();
    }


    //    title length asc, visits in descending order. but Ad Doodles should come first
    private Comparator<Doodle> adsFirst_TitleLengthAsc_VisitsDesc() {
        return (d1, d2) -> {
            int compare = 0;
            if (d1.getIsAd() && !d2.getIsAd()) {
                compare = -1;
            } else if (!d1.getIsAd() && d2.getIsAd()) {
                compare = 1;
            }

            if (compare != 0) {
                return compare;
            }

            compare = Integer.compare(d1.getTitle().length(), d2.getTitle().length());
            if (compare != 0) {
                return compare;
            }

            compare = Integer.compare(d2.getVisits(), d1.getVisits());
            return compare != 0 ? compare : d1.getId().compareTo(d2.getId());
        };
    }

    //    revenue in descending order and then by visits in descending order
    private Comparator<Doodle> revenueDescThenVisitsDesc() {
        return (d1, d2) -> {
            int compare = Double.compare(d2.getRevenue(), d1.getRevenue());
            if (compare != 0) {
                return compare;
            }

            compare = Integer.compare(d2.getVisits(), d1.getVisits());
            return compare != 0 ? compare : d1.getId().compareTo(d2.getId());
        };
    }
}
