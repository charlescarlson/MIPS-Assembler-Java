package edu.uiowa.cs;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class AssemblerTest {
        private static int MARS_TEXT_SEGMENT_START = 0x00400000;

        private static void testHelper(List<Instruction> input, Instruction[] expectedP1, Instruction[] expectedP2, Integer[] expectedP3) {
            // Phase 1
            List<Instruction> tals = Phase1.mal_to_tal(input);
            System.out.print(tals.toString());
            assertArrayEquals(expectedP1, tals.toArray());
            
            
            // Phase 2
            List<Instruction> resolved_tals = Phase2.resolve_addresses(tals, MARS_TEXT_SEGMENT_START);
            assertArrayEquals(expectedP2, resolved_tals.toArray());

            // Phase 3
            List<Integer> translated = Phase3.translate_instructions(resolved_tals);  
            assertArrayEquals(expectedP3, translated.toArray());
            
            
        }

        @Test
        public void test1() {
            // test 1
            List<Instruction> input = new LinkedList<Instruction>();
            // label1: addu $t0, $zero, $zero
            input.add(InstructionFactory.CreateAddu(8, 0, 0, "label1"));
            // addu $s0, $s7, $t4
            input.add(InstructionFactory.CreateAddu(16, 23, 12));
            // blt  $s0,$t0,label1
            input.add(InstructionFactory.CreateBlt(16, 8, "label1"));
            // addiu $s1,$s2,0xF00000
            input.add(InstructionFactory.CreateAddiu(17, 18, 0xF00000));

            // Phase 1
            Instruction[] phase1_expected = {
                    InstructionFactory.CreateAddu(8, 0, 0, "label1"), // label1: addu $t0, $zero, $zero
                    InstructionFactory.CreateAddu(16, 23, 12), // addu $s0, $s7, $t4
                    InstructionFactory.CreateSlt(1, 16, 8),  // slt $at,$s0,$t0
                    InstructionFactory.CreateBne(1, 0, "label1"),     // bne $at,$zero,label1
                    InstructionFactory.CreateLui(1, 0x00F0), // lui $at, 0x00F0
                    InstructionFactory.CreateOri(1, 1, 0x0000), // ori $at, $at 0x0000
                    InstructionFactory.CreateAddu(17, 18, 1) // addu $s1,$s2,$at
            };

            // Phase 2
            Instruction[] phase2_expected = {
                    InstructionFactory.CreateAddu(8,0, 0, "label1"),//new Instruction(2,8,0,0,0,0,0,1,0),
                    InstructionFactory.CreateAddu(16,23,12),//new Instruction(2,16,23,12,0,0,0,0,0),
                    InstructionFactory.CreateSlt(1, 16, 8),//new Instruction(8,1,16,8,0,0,0,0,0),
                    InstructionFactory.CreateBne(1, 0, 0xfffffffc),//new Instruction(6,0,1,0,0xfffffffc,0,0,0,1),
                    InstructionFactory.CreateLui(1, 0x00F0),// new Instruction(9,0,0,1,0x00F0,0,0,0,0),
                    InstructionFactory.CreateOri(1, 1, 0x0000),// new Instruction(10,0,1,1,0x0000,0,0,0,0),
                    InstructionFactory.CreateAddu(17, 18, 1)// new Instruction(2,17,18,1,0,0,0,0,0)
            };

            // Phase 3
            Integer[] phase3_expected = {
                    // HINT: to get these, type the input program into MARS, assemble, and copy the binary values into your test case
                    0x00004021,
                    0x02ec8021,
                    0x0208082a,
                    0x1420FFFC,
                    0x3c0100f0, 
                    0x34210000, //errors here
                    0x02418821
            };


            testHelper(input,
                    phase1_expected,
                    phase2_expected,
                    phase3_expected);
        }
        
        private static void test2Helper(List<Instruction> input2, Instruction[] test2expectedP1, Instruction[] test2expectedP2, Integer[] test2expectedP3) {
            // Phase 1
            List<Instruction> tals2 = Phase1.mal_to_tal(input2);
            System.out.print(tals2.toString());
            assertArrayEquals(test2expectedP1, tals2.toArray());
            
            
            // Phase 2
            List<Instruction> resolved_tals2 = Phase2.resolve_addresses(tals2, MARS_TEXT_SEGMENT_START);
            assertArrayEquals(test2expectedP2, resolved_tals2.toArray());

            // Phase 3
            List<Integer> translated2 = Phase3.translate_instructions(resolved_tals2);  
            assertArrayEquals(test2expectedP3, translated2.toArray());
            
            
        }
        @Test
        public void test2() {
            /* Fill in your additional test case here! */

            //testHelper(...);

            // test 2
            List<Instruction> input2 = new LinkedList<Instruction>();
            // or $k0, $s0, $t2
            input2.add(InstructionFactory.CreateOr(16,10,26));
            // beq $s0, $t5, hop
            input2.add(InstructionFactory.CreateBeq(16, 13, "hop"));
            // lui $t5, 0x0021
            input2.add(InstructionFactory.CreateLui(13, 0x0021));
            // bge $t2, $k0, hop 
            input2.add(InstructionFactory.CreateBge(26, 16, "hop"));
          
            
                        // Test 2, Phase 1
            Instruction[] test2phase1_expected = {
                    InstructionFactory.CreateOr(26, 10,16), //or $k0, $s0, $t2
                    InstructionFactory.CreateBeq(16,13,"hop"), //beq $s0, $t5, hop
                    InstructionFactory.CreateLui(13, 0x0021), //lui $t5, 0x0021
                    InstructionFactory.CreateSlt(1, 26, 10, "hop"), //slt $at, $k0, $t2
                    InstructionFactory.CreateBeq(1, 0, 0) //beq $at, $zero, $zero
            };
            
                        // Test 2, Phase 2
            Instruction[] test2phase2_expected = {
                    InstructionFactory.CreateOr(26, 10,16), //new instruction(ID.or, rd, rs, rt, 0)
                    InstructionFactory.CreateBeq(16,13,"hop"), //new instruction
                    InstructionFactory.CreateLui(13, 0x0021), //new instruction
                    InstructionFactory.CreateSlt(1, 26, 10, "hop"), //new instruction
                    InstructionFactory.CreateBeq(1, 0, 0) //new instruction()
            };
            
            
            // Test 2 Phase 3
            Integer[] test2phase3_expected = {
                    // HINT: to get these, type the input program into MARS, assemble, and copy the binary values into your test case
                0x020ad025,
                0x120d0003,
                0x3c0d0021,
                0x015a082a,
                0x10200000
            };
            
            test2Helper(input2,
                    test2phase1_expected,
                    test2phase2_expected,
                    test2phase3_expected);
        }

}