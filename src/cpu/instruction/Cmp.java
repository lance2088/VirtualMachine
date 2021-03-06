package cpu.instruction;

import cpu.InstructionRunner;
import cpu.InvalidFlagException;
import cpu.InstructionRunner.FLAG_NAME;
import cpu.instpar.InstructionParameter;
import cpu.instpar.InvalidParameterException;

public class Cmp implements Instruction {

	@Override
	public void execute(InstructionRunner runner,
		InstructionParameter[] parameters)
		throws InvalidInstructionException, InvalidParameterException 
	{
		if (parameters.length != 2) {
			throw new InvalidInstructionException();
		}
		
		int paramA = parameters[0].loadValue(runner);
		int paramB = parameters[1].loadValue(runner);
		
		long diff = ((long)paramA) - paramB;
		
		try {
			
			if (diff == 0) {
				runner.setFlag(FLAG_NAME.Zero, true);
			} else {
				runner.setFlag(FLAG_NAME.Zero, false);
			}
			
			if (diff < 0) {
				runner.setFlag(FLAG_NAME.Negative, true);
			} else {
				runner.setFlag(FLAG_NAME.Negative, false);
			}
			
			if (diff > Integer.MAX_VALUE) {
		        runner.setFlag(FLAG_NAME.Overflow, true);
		    } else if (diff < Integer.MIN_VALUE) {
		    	// underflow
		    	runner.setFlag(FLAG_NAME.Overflow, true);
		    } else {
		    	runner.setFlag(FLAG_NAME.Overflow, false);
		    }
			
			// TODO handle carry, adjust
			
		} catch (InvalidFlagException e) {
			// runner has no such flag
			e.printStackTrace();
			throw new InvalidInstructionException();
		}
	}

}
