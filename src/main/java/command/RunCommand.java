package command;

import framework.command.DefaultRunCommand;
import framework.utils.ConsoleUtils;
import framework.utils.MatrixUtils;
import framework.utils.ValidationUtils;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

import javax.annotation.Nonnull;
import java.util.*;

public class RunCommand extends DefaultRunCommand {

    private static final String VAR = "var";

    @Override
    public void execute(String[] strings) {
        try {
            Map<String, String> args = this.parseArgs(strings);
            String variant = args.get(VAR);
            ValidationUtils.requireBetweenClosed(
                    Integer.parseInt(variant), 1, 7, "var must be in [1, 7]");
            RealMatrix matrix = (RealMatrix) applicationState.getVariable(variant);
            executeLaboratory(matrix);
        } catch (Exception e) {
            ConsoleUtils.println(e.getMessage());
        }
    }

    @Nonnull
    @Override
    public Set<String> getOptions() {
        Set<String> options = new HashSet<>();
        options.add(VAR);
        return options;
    }

    private void executeLaboratory(RealMatrix matrix) {
        ConsoleUtils.println(String.format("%nMatrix:%n"));
        ConsoleUtils.printMatrix(matrix, 3);

        List<Double> coefficients = getCharacteristicEquationCoefficients(matrix);
        ConsoleUtils.println(String.format("%nCharacteristic equation coefficients:%n"));
        ConsoleUtils.println(convertCoefficientsToString(coefficients, 3));

        RealMatrix raussMatrix = getRaussMatrix(coefficients);
        ConsoleUtils.println(String.format("%nRauss's matrix:%n"));
        ConsoleUtils.printMatrix(raussMatrix, 3);

        boolean asymptoticallyStable = isAsymptoticallyStable(raussMatrix);
        String notPart = asymptoticallyStable ? " " : " not ";
        ConsoleUtils.println(String.format("%nSystem is%sasymptotically stable%n", notPart));
    }

    private String convertCoefficientsToString(List<? extends Double> coefficients, int numbersAfterPoint) {
        StringBuilder out = new StringBuilder();
        String template = String.format("%%.%df ", numbersAfterPoint);
        for (Double d : coefficients) {
            out.append(String.format(template, d));
        }
        out.deleteCharAt(out.length() - 1);
        return out.toString();
    }

    private boolean isAsymptoticallyStable(RealMatrix raussMatrix) {
        ValidationUtils.requireNonNull(raussMatrix);
        double[] zeroColumn = raussMatrix.getColumn(0);
        for (double d : zeroColumn) {
            if (d <= 0) {
                return false;
            }
        }
        return true;
    }

    private RealMatrix getRaussMatrix(List<Double> characteristicEquationCoefficients) {
        int n = characteristicEquationCoefficients.size() - 1;
        ValidationUtils.requireGreaterOrEqualThan(n, 0, "Coefficients amount mut be > 0");
        RealMatrix out;
        if (n % 2 == 0) {
            out = new Array2DRowRealMatrix(n, (n + 2) / 2);
        } else {
            out = new Array2DRowRealMatrix(n + 1, (n + 1) / 2);
        }
        for (int j = 0; j < out.getColumnDimension(); j++) {
            double a1 = 2 * j < characteristicEquationCoefficients.size()
                    ? characteristicEquationCoefficients.get(j * 2)
                    : 0;
            out.setEntry(0, j, a1);
            double a2 = j * 2 + 1 < characteristicEquationCoefficients.size()
                    ? characteristicEquationCoefficients.get(j * 2 + 1)
                    : 0;
            out.setEntry(1, j, a2);
        }
        for (int i = 2; i < out.getRowDimension(); i++) {
            double factor = out.getEntry(i - 2, 0) / out.getEntry(i - 1, 0);
            for (int j = 0; j < out.getColumnDimension(); j++) {
                boolean shouldNotBeZero = (j + 1) < out.getColumnDimension();
                double subtractFrom = shouldNotBeZero ? out.getEntry(i - 2, j + 1) : 0;
                double multiplyTo = shouldNotBeZero ? out.getEntry(i - 1, j + 1) : 0;
                double r = subtractFrom - factor * multiplyTo;
                out.setEntry(i, j, r);
            }
        }
        return out;
    }

    private List<Double> getCharacteristicEquationCoefficients(RealMatrix matrix) {
        ValidationUtils.requireTrue(matrix.isSquare());
        List<Double> out = new ArrayList<>();
        out.add(1.0);
        RealMatrix A = matrix;
        for (int i = 0; i < matrix.getColumnDimension(); i++) {
            double a = -1 * A.getTrace() / (i + 1);
            out.add(a);
            RealMatrix elementary = MatrixUtils.getElementaryMatrix(matrix.getColumnDimension());
            RealMatrix B = A.add(elementary.scalarMultiply(a));
            A = matrix.multiply(B);
        }

        return out;
    }
}
