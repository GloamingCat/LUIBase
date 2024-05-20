package lui.base.action;

import java.util.ArrayList;

public interface LState {

	void reset();

	default void resetStates(ArrayList<LState> list) {
		for (LState state : list) {
			state.reset();
		}
	}

}
