class Spreadsheet {
    List<List<Integer>> grid;
    private final int cols = 26;

    public Spreadsheet(int rows) {
        grid = new ArrayList<>(rows);
        for (int i = 0; i < rows; i++) {
            List<Integer> row = new ArrayList<>(Collections.nCopies(cols, 0));
            grid.add(row);
        }
    }

    public void setCell(String cell, int value) {
        int col = cell.charAt(0) - 'A';
        int row = Integer.parseInt(cell.substring(1)) - 1;
        grid.get(row).set(col, value);
    }

    public void resetCell(String cell) {
        int col = cell.charAt(0) - 'A';
        int row = Integer.parseInt(cell.substring(1)) - 1;
        grid.get(row).set(col, 0);
    }

    public int getValue(String formula) {
        String expr = formula.startsWith("=") ? formula.substring(1) : formula;

        String[] parts = expr.split("\\+");
        String cell1 = parts[0].trim();
        String cell2 = parts[1].trim();

        int val1 = cell1.chars().allMatch(Character::isDigit)
                ? Integer.parseInt(cell1)
                : grid.get(Integer.parseInt(cell1.substring(1)) - 1)
                      .get(cell1.charAt(0) - 'A');

        int val2 = cell2.chars().allMatch(Character::isDigit)
                ? Integer.parseInt(cell2)
                : grid.get(Integer.parseInt(cell2.substring(1)) - 1)
                      .get(cell2.charAt(0) - 'A');

        return val1 + val2;
    }
}
