package edu.uiowa.cs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Phase2 {

    /* Returns a list of copies of the Instructions with the
     * immediate field of the instruction filled in
     * with the address calculated from the branch_label.
     *
     * The instruction should not be changed if it is not a branch instruction.
     *
     * unresolved: list of instructions without resolved addresses
     * first_pc: address where the first instruction will eventually be placed in memory
     */
    
    public static List<Instruction> addressResolvedTals = new LinkedList<Instruction>();
    
    public static List<Instruction> resolve_addresses(List<Instruction> unresolved, int first_pc) {
        for (int i = 0; i < addressResolvedTals.size(); i++) {
            Instruction currentInstruction = addressResolvedTals.get(i);
            
            // if line has a label
            if (currentInstruction.label != "") {
                // save the address of line
            }
            
            // if instruction is a branch
            if ( currentInstruction.instruction_id.blt == currentInstruction.instruction_id 
                    || currentInstruction.instruction_id.bge == currentInstruction.instruction_id
                    || currentInstruction.instruction_id.blt == currentInstruction.instruction_id
                    || currentInstruction.instruction_id.beq == currentInstruction.instruction_id ) 
            {
                
            }
        }
        return addressResolvedTals;
    }

}
