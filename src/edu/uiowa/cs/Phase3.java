package edu.uiowa.cs;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.lang.Integer;

public class Phase3 {

    /* Translate each Instruction object into
     * a 32-bit number.
     *
     * tals: list of Instructions to translate
     *
     * returns a list of instructions in their 32-bit binary representation
     *
     */
        public static List<Integer> binary32 = new LinkedList<Integer>();

    
        public Phase3() {
            this.binary32 = binary32;
        }
    public static List<Integer> translate_instructions(List<Instruction> tals) {
        //tals = Phase1.tals;
        // I-Type : addiu, beq, bne, lui, ori
        // I-Type : needs opcode, rs, rt, imm
        // R-Type : addu, or, alt
        // R-Type : needs opcode, rt, rs, rd, shamt, funct
        
            int i;
            for ( i = 0; i < tals.size(); i++ ) {
                Instruction currentInstruction = tals.get(i);

                if ( (currentInstruction.instruction_id.addu == currentInstruction.instruction_id) 
                        )  {
                    //R[$rd] ← R[$rs] + R[$rt]
                    int funct = 33; //bits 0-5
                    int shamt = 0 << 10; //bits 6-10;
                    int bit_010 = funct ^ shamt; // funct XOR shamt
                    int rd_bit = currentInstruction.rd << 15;
                    int bit_0_15 = rd_bit ^ bit_010;
                    int rt_bit = currentInstruction.rt << 20;
                    int bit_0_20 = bit_0_15 ^ rt_bit;
                    int rs_bit = currentInstruction.rs << 25;
                    int bit_0_25 = bit_0_20 ^ rs_bit; 
                    String answer = Integer.toHexString(bit_0_25);
                    //int hex_answer = Integer.parseInt(answer, 16);
                    binary32.add(Integer.parseInt(answer, 16));
                    continue;
                }
                if ( (currentInstruction.instruction_id.addiu == currentInstruction.instruction_id) 
                        )  {
                    int imm_bit = currentInstruction.immediate;
                    int rt_bit = currentInstruction.rt << 20;
                    int bit_0_20 = imm_bit ^ rt_bit;
                    int rs_bit = currentInstruction.rs << 25;
                    int bit_0_25 = bit_0_20 ^ rs_bit; 
                    int opcode_bit = 33 << 31;
                    int bit_32 = opcode_bit ^ bit_0_25;
                    String answer = Integer.toHexString(bit_32);
                    //int hex_answer = Integer.parseInt(answer, 16);
                    binary32.add(Integer.parseInt(answer, 16));
                    continue;
                }
                if ( (currentInstruction.instruction_id.or == currentInstruction.instruction_id) ) {
                    //R-Type
                    int funct = 37; //bits 0-5
                    int shamt = 0 << 10; //bits 6-10;
                    int bit_010 = funct ^ shamt; // funct XOR shamt
                    int rd_bit = currentInstruction.rd << 15;
                    int bit_0_15 = rd_bit ^ bit_010;
                    int rt_bit = currentInstruction.rt << 20;
                    int bit_0_20 = bit_0_15 ^ rt_bit;
                    int rs_bit = currentInstruction.rs << 25;
                    int bit_0_25 = bit_0_20 ^ rs_bit; 
                    String answer = Integer.toHexString(bit_0_25);
                    //int hex_answer = Integer.parseInt(answer, 16);
                    binary32.add(Integer.parseInt(answer, 16));
                    continue;
                }         
                if ( (currentInstruction.instruction_id.beq == currentInstruction.instruction_id) ) {
                    //I-Type
                    int imm_bit = currentInstruction.immediate;
                    int rt_bit = currentInstruction.rt << 20;
                    int bit_0_20 = imm_bit ^ rt_bit;
                    int rs_bit = currentInstruction.rs << 25;
                    int bit_0_25 = bit_0_20 ^ rs_bit; 
                    int opcode_bit = 4 << 31;
                    int bit_32 = opcode_bit ^ bit_0_25;
                    String answer = Integer.toHexString(bit_32);
                    //int hex_answer = Integer.parseInt(answer, 16);
                    binary32.add(Integer.parseInt(answer, 16));
                    continue;
                } 
              
                if ( (currentInstruction.instruction_id.bne == currentInstruction.instruction_id) ) {
                    //I-Type
                    int imm_bit = currentInstruction.immediate;
                    int rt_bit = currentInstruction.rt << 20;
                    int bit_0_20 = imm_bit ^ rt_bit;
                    int rs_bit = currentInstruction.rs << 25;
                    int bit_0_25 = bit_0_20 ^ rs_bit; 
                    int opcode_bit = 5 << 31;
                    int bit_32 = opcode_bit ^ bit_0_25;
                    String answer = Integer.toHexString(bit_32);
                    //int hex_answer = Integer.parseInt(answer, 16);
                    binary32.add(Integer.parseInt(answer, 16));
                    continue;
                }
                if ( (currentInstruction.instruction_id.slt == currentInstruction.instruction_id) ) {
                    //R-Type
                    int funct = 42; //bits 0-5
                    int shamt = 0 << 10; //bits 6-10;
                    int bit_010 = funct ^ shamt; // funct XOR shamt
                    int rd_bit = currentInstruction.rd << 15;
                    int bit_0_15 = rd_bit ^ bit_010;
                    int rt_bit = currentInstruction.rt << 20;
                    int bit_0_20 = bit_0_15 ^ rt_bit;
                    int rs_bit = currentInstruction.rs << 25;
                    int bit_0_25 = bit_0_20 ^ rs_bit; 
                    String answer = Integer.toHexString(bit_0_25);
                    //int hex_answer = Integer.parseInt(answer, 16);
                    binary32.add(Integer.parseInt(answer, 16));
                    continue;
                }               
                if ( (currentInstruction.instruction_id.lui == currentInstruction.instruction_id) ) {
                    //I-Type
                    //upper bit immediate, low bit are zeros 
                    int imm_bit = currentInstruction.immediate;
                    int rt_bit = 0;
                    int bit_0_20 = imm_bit ^ rt_bit;
                    int rs_bit = 0 <<25;
                    int bit_0_25 = bit_0_20 ^ rs_bit; 
                    int opcode_bit = 0 <<31;
                    int bit_32 = opcode_bit ^ bit_0_25;
                    String answer = Integer.toHexString(bit_32);
                    //int hex_answer = Integer.parseInt(answer, 16);
                    binary32.add(Integer.parseInt(answer, 16));
                    continue;
                }
                if ( (currentInstruction.instruction_id.ori == currentInstruction.instruction_id) ) {
                    //I-Type = opcode, rt, rs, imm
                    //extended imm for 16-bits and all 0's. 
                    int imm_bit = 00;
                    imm_bit = imm_bit << 15;
                    int rt_bit = currentInstruction.rt << 21;
                    int bit_0_20 = imm_bit ^ rt_bit;
                    int rs_bit = currentInstruction.rs << 26;
                    int bit_0_25 = bit_0_20 ^ rs_bit; 
                    int opcode_bit = 13 << 30;
                    int bit_32 = opcode_bit ^ bit_0_25;
                    String answer = Integer.toHexString(bit_32);
                    //int hex_answer = Integer.parseInt(answer, 16);
                    binary32.add(Integer.parseInt(answer, 16));
                    continue;
                }                

            }
        return binary32;
    }
}
