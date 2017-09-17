package edu.uiowa.cs;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class Phase3 {

    /* Translate each Instruction object into
     * a 32-bit number.
     *
     * tals: list of Instructions to translate
     *
     * returns a list of instructions in their 32-bit binary representation
     *
     */
        public static List<Instruction> mals = new LinkedList<Instruction>();
        public static List<Integer> binary32 = new LinkedList<Integer>();
    
        public Phase3() {
            this.mals = mals;
            this.binary32 = binary32;
        }
    public static List<Integer> translate_instructions(List<Instruction> tals) {
        // I-Type : addiu, beq, bne, lui, ori
        // I-Type : needs opcode, rs, rt, imm
        // R-Type : addu, or, alt
        // R-Type : needs opcode, rt, rs, rd, shamt, funct
        
            int i;
            for ( i = 0; i < tals.size(); i++ ) {
                Instruction currentInstruction = tals.get(i);
                int rs = currentInstruction.rs;
                int rd = currentInstruction.rd;
                int rt = currentInstruction.rt;
                int imm = currentInstruction.immediate;
                int jumpAddress = currentInstruction.jump_address; //always 0
                int shiftAmount = currentInstruction.shift_amount; //always 0
                String label = currentInstruction.label;
                String branchLabel = currentInstruction.branch_label;


                int t0 = 0;
                int t1 = 0;
                int t2 = 0;
                int t3 = 0;

                if ( (currentInstruction.instruction_id.addu == currentInstruction.instruction_id) 
                        )  {

                    LinkedList<Integer> addu_binary = new LinkedList<Integer>();
                    int opcode = 00000000000000000000000000100001;
                    addu_binary.add(opcode);
                    continue;
                }
                if ( (currentInstruction.instruction_id.addiu == currentInstruction.instruction_id) 
                        )  {

                    LinkedList<Integer> addiu_binary = new LinkedList<Integer>();
                    int opcode = 00000000000000000000000000001001;
                    addiu_binary.add(opcode);
                    continue;
                }
                if ( (currentInstruction.instruction_id.or == currentInstruction.instruction_id) ) {
                    LinkedList<Integer> or_binary = new LinkedList<Integer>();
                    int opcode = 00000000000000000000000000100101;
                    or_binary.add(opcode);
                    continue;
                }         
                if ( (currentInstruction.instruction_id.beq == currentInstruction.instruction_id) ) {
                    LinkedList<Integer> beq_binary = new LinkedList<Integer>();
                    int opcode = 00000000000000000000000000000100;
                    beq_binary.add(opcode);
                    continue;
                } 
              
                if ( (currentInstruction.instruction_id.bne == currentInstruction.instruction_id) ) {
                    LinkedList<Integer> bne_binary = new LinkedList<Integer>();
                    int opcode = 00000000000000000000000000000101;
                    bne_binary.add(opcode);
                    continue;
                }
                if ( (currentInstruction.instruction_id.slt == currentInstruction.instruction_id) ) {
                    LinkedList<Integer> slt_binary = new LinkedList<Integer>();
                    int opcode = 00000000000000000000000000101010;
                    slt_binary.add(opcode);
                    continue;
                }               
                if ( (currentInstruction.instruction_id.lui == currentInstruction.instruction_id) ) {
                    LinkedList<Integer> lui_binary = new LinkedList<Integer>();
                    int opcode = 00000000000000000000000000001111;
                    lui_binary.add(opcode);
                    continue;
                }
                if ( (currentInstruction.instruction_id.ori == currentInstruction.instruction_id) ) {
                    LinkedList<Integer> ori_binary = new LinkedList<Integer>();
                    int opcode = 00000000000000000000000000001101;
                    ori_binary.add(opcode);
                    continue;
                }                

                else return binary32;
            }
        
        return binary32;
    }
}

