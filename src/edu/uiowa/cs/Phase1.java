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
    
    public static List<Instruction> mals = new LinkedList<Instruction>();
        public static List<Instruction> tals = new LinkedList<Instruction>();
    
        public Phase1() {
            this.mals = mals;
            this.tals = tals;
        }
    
    
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
                    //addiu $t1, $t2, 0x123456
                    //rt, rs, imm
                    t1 = rt;
                    t2 = rs;
                    at = 1;
                    
                    tals.add( InstructionFactory.CreateLui(t2, upperImm));
                    tals.add( InstructionFactory.CreateOri(t1, t2, lowerImm));
                    tals.add( InstructionFactory.CreateAddu(t1, t2, t3));
                    continue;

                }

                if ( (currentInstruction.instruction_id.blt == currentInstruction.instruction_id) ) {
                    t1 = rt;
                    t2 = rs;
                    at = 1;
                    
                    tals.add( InstructionFactory.CreateSlt(at, t1, t2, label));
                    tals.add( InstructionFactory.CreateBlt(at, 0, label));
                    continue;
                }

                if ( (currentInstruction.instruction_id.bge == currentInstruction.instruction_id) ) {
                    //slt $at,r1,r2
                    //beq $at,$zero,hello
                    
                    t1 = rt;
                    t2 = rs;
                    
                    tals.add( InstructionFactory.CreateSlt(at, t1, t2, label));
                    tals.add( InstructionFactory.CreateBeq(at, t1, label));
                    continue;
                }
                else tals.add( currentInstruction);
            }
        
        return tals;
    }
}