package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);

        StringBuilder builder = new StringBuilder();
        String ls = System.lineSeparator();
        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(ls);

        HashMap<String, Integer> dataMap = new HashMap<>();

        for (String name : names) {
            dataMap.put(name, 0);
        }

        for (String dataItem : data) {
            String[] dataItemParts = dataItem.split(" ");
            LocalDate currentDate = LocalDate.parse(dataItemParts[0], formatter);

            if (!currentDate.isBefore(from) && !currentDate.isAfter(to)) {
                String name = dataItemParts[1];
                int workingHours = Integer.parseInt(dataItemParts[2]);
                int incomePerHour = Integer.parseInt(dataItemParts[3]);
                int income = workingHours * incomePerHour;
                dataMap.computeIfPresent(name, (k, v) -> v + income);
            }
        }

        for (String name : names) {
            builder.append(name)
                    .append(" - ")
                    .append(dataMap.get(name))
                    .append(ls);
        }

        return builder.toString().trim();
    }
}
