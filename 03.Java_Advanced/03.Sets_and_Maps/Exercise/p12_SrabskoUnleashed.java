package SetsAndMaps_03.Exercise;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class p12_SrabskoUnleashed {
    public static void main(String[] args) {
        Map<String, Map<String, Long>> singerProfitByVenue = new LinkedHashMap<>();

        Scanner scanner = new Scanner(System.in);
        String input;
        while (!"End".equals((input = scanner.nextLine()))) {
            String[] concertInfo = extractValidInfo(input);

            if (concertInfo == null) {
                continue;
            }

            addConcertInfoIn(concertInfo, singerProfitByVenue);
        }

        System.out.print(singerProfitByVenue.entrySet().stream()
                .map(e -> {
                    String singersSorted = e.getValue().entrySet().stream()
                            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                            .map(valueEntry -> "#  " + valueEntry.getKey() + " -> " + valueEntry.getValue())
                            .collect(Collectors.joining(System.lineSeparator()));
                    return e.getKey() + System.lineSeparator() + singersSorted;
                })
                .collect(Collectors.joining(System.lineSeparator())));
    }


    static void addConcertInfoIn(String[] concertInfo, Map<String, Map<String, Long>> map) {
        String singerName = concertInfo[0];
        String venueName = concertInfo[1];
        int ticketPrice = Integer.parseInt(concertInfo[2]);
        int ticketCount = Integer.parseInt(concertInfo[3]);
        long profit = (long) ticketPrice * ticketCount;

        map.putIfAbsent(venueName, new LinkedHashMap<>());
        Map<String, Long> profitsForSingers = map.get(venueName);
        profitsForSingers.putIfAbsent(singerName, 0L);
        profitsForSingers.put(singerName, profitsForSingers.get(singerName) + profit);

    }

    static String[] extractValidInfo(String info) {
        Pattern p = Pattern.compile
                ("^(?<singer>([A-Za-z]+\\s)+)@(?<venue>([A-Za-z]+\\s)+)(?<price>\\d+)\\s(?<count>\\d+)$");
        Matcher m = p.matcher(info);
        String[] extractedInfo = null;
        if (m.matches()) {
            extractedInfo = new String[4];
            extractedInfo[0] = m.group("singer").trim();
            extractedInfo[1] = m.group("venue").trim();
            extractedInfo[2] = (m.group("price"));
            extractedInfo[3] = m.group("count");
        }
        return extractedInfo;
    }

}
