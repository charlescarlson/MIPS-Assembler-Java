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
                
                int bit_32 = 00000000000000000000000000000000;
                //bits 0-10 in Instruction if-statement
                //bits 11-15 rd
                String string_bit_1115 = Integer.toBinaryString(currentInstruction.rd); //gets input of rd as binary
                String string_bit_imm = Integer.toBinaryString(currentInstruction.immediate);//gets input of imm as binary
                int bit_imm = Integer.parseInt(string_bit_imm) + bit_32;
                int bit_1115 = Integer.parseInt(string_bit_1115) + bit_32;  //changes rd to a binary
                bit_1115 = bit_1115 + bit_imm; //add together since imm and rd both cannot exist
                bit_1115 = bit_1115 << 11; // changes rd to bits 11-15
                
                //bits 16-20 rt
                String string_bit_1620 = Integer.toBinaryString(currentInstruction.rt);
                int bit_1620 = Integer.parseInt(string_bit_1620) + bit_32;
                bit_1620 = bit_1620 << 16;
                int bit_1120 = bit_1620 ^ bit_1115; 
    
                //bits 21-25 rs
                String string_bit_2125 = Integer.toBinaryString(currentInstruction.rs);
                int bit_2125 = Integer.parseInt(string_bit_2125) + bit_32;
                bit_2125 = bit_2125 << 16;
                int bit_1125 = bit_2125 ^ bit_1120; 
                
                

                if ( (currentInstruction.instruction_id.addu == currentInstruction.instruction_id) 
                        )  {
                    //R[$rd] ← R[$rs] + R[$rt]
                    int funct = 00000000000000000000000000100001; //bits 0-5
                    int shamt = 00000000000000000000000000000000; //bits 6-10;
                    int bit_010 = funct ^ shamt; // funct XOR shamt
                    //bit 26-32 in R type is always zero

                    int bit_032 = bit_010 ^ bit_1125;
                    binary32.add(bit_032);
                    continue;
                }
                if ( (currentInstruction.instruction_id.addiu == currentInstruction.instruction_id) 
                        )  {

                    LinkedList<Integer> addiu_binary = new LinkedList<Integer>();
                    int opcode = 00000000000000000000000000001001; //bits 26-32
                    int bit_032 = opcode ^ bit_1125;
                    addiu_binary.add(bit_032);
                    continue;
                }
                if ( (currentInstruction.instruction_id.or == currentInstruction.instruction_id) ) {
                    int funct = 00000000000000000000000000100101; //bits 0-5
                    int shamt = 00000000000000000000000000000000; //bits 6-10
                    int bit_010 = funct ^ shamt; // funct XOR shamt
                    int bit_032 = bit_010 ^ bit_1125;
                    binary32.add(bit_032);
                    continue;
                }         
                if ( (currentInstruction.instruction_id.beq == currentInstruction.instruction_id) ) {
                    LinkedList<Integer> beq_binary = new LinkedList<Integer>();
                    int opcode = 00000000000000000000000000000100;
                    int bit_032 = opcode ^ bit_1125;
                    beq_binary.add(bit_032);
                    continue;
                } 
              
                if ( (currentInstruction.instruction_id.bne == currentInstruction.instruction_id) ) {
                    LinkedList<Integer> bne_binary = new LinkedList<Integer>();
                    int opcode = 00000000000000000000000000000101;
                    int bit_032 = opcode ^ bit_1125;
                    bne_binary.add(bit_032);
                    continue;
                }
                if ( (currentInstruction.instruction_id.slt == currentInstruction.instruction_id) ) {
                    LinkedList<Integer> slt_binary = new LinkedList<Integer>();
                    int funct = 00000000000000000000000000101010; //bits 0-5
                    int shamt = 00000000000000000000000000101010; //bits 6-10
                    int bit_010 = funct ^ shamt; // funct XOR shamt

                    int bit_032 = bit_010 ^ bit_1125;
                    slt_binary.add(bit_032);
                    continue;
                }               
                if ( (currentInstruction.instruction_id.lui == currentInstruction.instruction_id) ) {
                    LinkedList<Integer> lui_binary = new LinkedList<Integer>();
                    int opcode = 00000000000000000000000000001111;
                    int bit_032 = opcode ^ bit_1125;
                    lui_binary.add(bit_032);
                    continue;
                }
                if ( (currentInstruction.instruction_id.ori == currentInstruction.instruction_id) ) {
                    LinkedList<Integer> ori_binary = new LinkedList<Integer>();
                    int opcode = 00000000000000000000000000001101;
                    int bit_032 = opcode ^ bit_1125;
                    ori_binary.add(bit_032);
                    continue;
                }                

                else
                {
                    return binary32;
                }
            }
        return binary32;
    }
}

