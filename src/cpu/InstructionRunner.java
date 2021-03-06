package cpu;

import cpu.instpar.InstructionParameter;

public interface InstructionRunner {
	public static enum REGISTER_NAME {
		eax,
		ebx,
		ecx,
		edx,
		esp,
		ebp,
		esi,
		edi
	}
	
	public static enum FLAG_NAME {
		Carry,
		Adjust,
		Zero,
		Negative,
		Overflow
	}
	
	/**
	 * Gets the content of the selected register
	 * @param registerName The name of the selected register
	 * @return The content of the selected register
	 * @throws InvalidRegisterException If no such register found
	 */
	public int getRegisterContent(REGISTER_NAME registerName) throws InvalidRegisterException;
	public void setRegisterContent(REGISTER_NAME registerName, int content) throws InvalidRegisterException;
	public InstructionParameter identifyInstructionParameter(String parameter);
	public String fetchWord(int address);
	public void putWord(int address, String word);
	public void increaseStackframe();
	public void decreaseStackframe();
	public int getStackframeIndex();
	public void pushStack(int value);
	public int popStack();
	public void setFlag(FLAG_NAME flag, boolean value) throws InvalidFlagException;
	public boolean getFlag(FLAG_NAME flag) throws InvalidFlagException;
	public void jump(int address);
	public int getPcState();
}
