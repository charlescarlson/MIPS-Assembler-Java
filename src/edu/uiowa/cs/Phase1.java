package edu.uiowa.cs;

import java.util.LinkedList;
import java.util.List;

public class Phase1 {

    /* Translates the MAL instruction to 1-3 TAL instructions
     * and returns the TAL instructions in a list
     *
     * mals: input program as a list of Instruction objects
     *
     * returns a list of TAL instructions (should be same size or longer than input list)
     */
    
    public static List<Instruction> m = new LinkedList<Instruction>();
    public static List<Instruction> t = new LinkedList<Instruction>();

    
    
    
    public static List<Instruction> mal_to_tal(List<Instruction> mals) {
        
            int i;
            for ( i = 0; i < mals.size(); i++ ) {
                Instruction currentInstruction = mals.get(i);
                int rs = currentInstruction.rs;
                int rd = currentInstruction.rd;
                int rt = currentInstruction.rt;
                int imm = currentInstruction.immediate;
                int jumpAddress = currentInstruction.jump_address;
                int shiftAmount = currentInstruction.shift_amount;
                String label = currentInstruction.label;
                String branchLabel = currentInstruction.branch_label;

                int upperImm = imm >>> 16;
                int lowerImm = imm << 16;

                int t1 = 0;
                int t2 = 0;
                int t3 = 0;
                int at = 0;
               

                if ( (currentInstruction.instruction_id.addiu == currentInstruction.instruction_id) 
                        || (currentInstruction.instruction_id.ori == currentInstruction.instruction_id) 
                        && (imm > 32767) )  {
                    at = 1;
                    
                    //CreateAddiu(int rt, int rs, int immediate, String label){
                    t.add( InstructionFactory.CreateLui(at, upperImm));
                    t.add( InstructionFactory.CreateOri(at, at, lowerImm));
                    t.add( InstructionFactory.CreateAddu(rt, rs, at));
                    continue;

                }

                if ( (currentInstruction.instruction_id.blt == currentInstruction.instruction_id) ) {
                    //t1 = rt;
                    //t2 = rs;
                    if (rt > rs) {
                        at = 1;
                    }
                    else at = 0;
                    
                    t.add( InstructionFactory.CreateSlt(at, rt, rs));
                    t.add( InstructionFactory.CreateBne(at, 0, branchLabel));
                    continue;
                }

                if ( (currentInstruction.instruction_id.bge == currentInstruction.instruction_id) ) {
                    
                    t.add( InstructionFactory.CreateSlt(at, t1, t2, label));
                    t.add( InstructionFactory.CreateBeq(at, t1, label));
                    continue;
                }
                else t.add(currentInstruction);
            }
        
        return t;
    }
}
