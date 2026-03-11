package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, DATE_FORMATTER);

        StringBuilder reportBuilder = new StringBuilder();
        String lineSeparator = System.lineSeparator();
        reportBuilder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(lineSeparator);

        int[] salaries = new int[names.length];

        for (String dataItem : data) {
            String[] dataItemParts = dataItem.split(" ");
            LocalDate currentDate = LocalDate.parse(dataItemParts[DATE_INDEX], DATE_FORMATTER);

            if (!currentDate.isBefore(from) && !currentDate.isAfter(to)) {
                String name = dataItemParts[NAME_INDEX];
                int hoursWorked = Integer.parseInt(dataItemParts[HOURS_INDEX]);
                int hourlyRate = Integer.parseInt(dataItemParts[RATE_INDEX]);
                int income = hoursWorked * hourlyRate;

                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(name)) {
                        salaries[i] += income;
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < names.length; i++) {
            reportBuilder.append(names[i])
                    .append(" - ")
                    .append(salaries[i])
                    .append(lineSeparator);
        }

        return reportBuilder.toString().trim();
    }
}
