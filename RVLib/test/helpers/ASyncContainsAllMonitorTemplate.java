package helpers;

import de.bodden.rvlib.finitestate.State;
import de.bodden.rvlib.generic.IAlphabet;
import de.bodden.rvlib.generic.def.Alphabet;

public class ASyncContainsAllMonitorTemplate extends AbstractFSMMonitorTestTemplate<String,String,Object> {
	
	@Override
	protected IAlphabet<String> createAlphabet() {
		Alphabet<String> alphabet = new Alphabet<String>();
		alphabet.add(makeNewSymbol("sync"));
		alphabet.add(makeNewSymbol("containsAll"));
		return alphabet;
	}

	protected State<String> createAndWireInitialState() {
		State<String> initial = makeState(false);
		State<String> synched1 = makeState(false);
		State<String> synched2 = makeState(false);
		State<String> error = makeState(true);
		
		initial.addTransition(getSymbolByLabel("containsAll"), initial);
		initial.addTransition(getSymbolByLabel("sync"), synched1);
		synched1.addTransition(getSymbolByLabel("containsAll"), synched1);
		synched1.addTransition(getSymbolByLabel("sync"), synched2);
		synched2.addTransition(getSymbolByLabel("containsAll"), error);
		return initial;
	}

}
