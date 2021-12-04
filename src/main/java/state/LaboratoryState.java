package state;

import framework.state.AbstractApplicationState;
import lombok.Getter;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;

@Getter
public class LaboratoryState extends AbstractApplicationState {

    @Override
    protected void initVariableNameToSettersMap() {
        this.variableNameToGetter.put("1", () -> new Array2DRowRealMatrix(new double[][]{
                {-3, 2.2, 0.8, 0},
                {0.4, -4.7, 6.8, 0.1},
                {0.3, -3.1, -4.4, 0.6},
                {-0.5, 0.8, 1.9, -1.3}
        }));
        this.variableNameToGetter.put("2", () -> new Array2DRowRealMatrix(new double[][]{
                {7, 2.19, 0.8, 0},
                {0.4, 5.29, 6.79, 0.1},
                {0.29, -3.1, 5.59, 5.9},
                {-0.49, 0.8, 1.89, 8.69}
        }));
        this.variableNameToGetter.put("3", () -> new Array2DRowRealMatrix(new double[][]{
                {5.012, 2.457, 2.507, 0.069},
                {0.691, 0.797, 7.463, 0.548},
                {0.223, -3.264, 1.166, 0.827},
                {-0.696, 0.421, 3.221, 7.691}
        }));
        this.variableNameToGetter.put("4", () -> new Array2DRowRealMatrix(new double[][]{
                {0.7446, 0.1372, 0.0981, 0.0035},
                {0.0319, 0.5701, 0.4213, 0.0274},
                {0.0153, -0.1879, 0.5901, 0.0413},
                {-0.0376, 0.0348, 0.1533, 0.8846}
        }));
        this.variableNameToGetter.put("5", () -> new Array2DRowRealMatrix(new double[][]{
                {-0.9, 3.1, -0.2},
                {-0.4, -2.5, 3.2},
                {1.1, -1.5, -3.1}
        }));
        this.variableNameToGetter.put("6", () -> new Array2DRowRealMatrix(new double[][]{
                {-4, 0.5, 0.7, -1.2},
                {1, -6, 1, 1},
                {0.5, 1, -3.8, 3},
                {1.5, 0, 1.5, -4.5}
        }));
        this.variableNameToGetter.put("7", () -> new Array2DRowRealMatrix(new double[][]{
                {-2, 0.1, 0.3, -0.4, 0},
                {0.3, -2.9, -0.5, 0.4, 0.1},
                {0.2, -0.5, -1.8, 1.5, 0.2},
                {0, 1, 0, -2, 0.6},
                {-1, 0, 0.1, 0, -1.3}
        }));
        this.variableNameToGetter.put("8", () -> new Array2DRowRealMatrix(new double[][]{
                {-5.5, -2, 0.2, -31},
                {0.3, -3.1, 2.1, -0.1},
                {0.5, 3.4, -4.0, 0},
                {0.1, -0.3, 1.4, -2.1}
        }));
    }

}
