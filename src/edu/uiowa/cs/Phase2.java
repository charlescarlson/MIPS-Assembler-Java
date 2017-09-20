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
    
    public static LinkedList<Instruction> addressResolvedTals = new LinkedList<Instruction>();
    public static LinkedList<LinkedList<Object>> listOfMappings = new LinkedList<LinkedList<Object>>();
    
    public static List<Instruction> resolve_addresses(List<Instruction> unresolved, int first_pc) {
        int pc = first_pc;
        for (int i = 0; i < unresolved.size(); i++) {
            Instruction currentInstruction = unresolved.get(i);
            LinkedList<Object> mapping = new LinkedList<Object>();
            
            // if line has a label
            if (currentInstruction.label != "") {
                // save the address of line
                String label = currentInstruction.label;
                int label_pc = first_pc;
                mapping.add(label);
                mapping.add(label_pc);
                
            }
            first_pc += 4;
            if (mapping.size() != 0) {
                listOfMappings.add(mapping);
            }           
            // if instruction is a branch
            
        }
        
        for (int i = 0; i < unresolved.size(); i++) {
            Instruction currentInstruction = unresolved.get(i);
            if (currentInstruction.instruction_id.beq == currentInstruction.instruction_id
                    || currentInstruction.instruction_id.blt == currentInstruction.instruction_id
                    || currentInstruction.instruction_id.bge == currentInstruction.instruction_id
                    || currentInstruction.instruction_id.bne == currentInstruction.instruction_id ) {
                for (int j = 0; j < listOfMappings.size(); j++) {
                    // structure of elements in listOfMappings: {String label, int label_pc}
                    LinkedList<Object> currentMapping = listOfMappings.get(j);
                    int addr = (Integer)currentMapping.get(1);
                    if (currentMapping.get(0) == currentInstruction.branch_label) {
                        int immAddr = (addr - pc) / 4 ;
                        currentInstruction.immediate = immAddr;
                        currentInstruction.branch_label = "";
                    }
                }
            }
            pc += 4;
            addressResolvedTals.add(currentInstruction);
        }
        return addressResolvedTals;
    }

}
