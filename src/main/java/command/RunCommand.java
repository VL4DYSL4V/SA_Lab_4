package command;

import framework.command.DefaultRunCommand;
import framework.utils.ConsoleUtils;
import framework.utils.ValidationUtils;
import org.apache.commons.math3.linear.RealMatrix;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RunCommand extends DefaultRunCommand {

    private static final String VAR = "var";

    @Override
    public void execute(String[] strings) {
        try {
            Map<String, String> args = this.parseArgs(strings);
            String var = args.get(VAR);
            ValidationUtils.requireBetweenClosed(Integer.parseInt(var), 1, 8, "var must be in [1, 8]");
            RealMatrix matrix = (RealMatrix) applicationState.getVariable(var);
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

    }
}
