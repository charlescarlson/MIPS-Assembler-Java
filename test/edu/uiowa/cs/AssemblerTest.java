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
            // instruction factory
            InstructionFactory instFact = new InstructionFactory();
            // test 1
            List<Instruction> input = new LinkedList<Instruction>();
            // label1: addu $t0, $zero, $zero
            input.add(instFact.CreateAddu(8, 0, "label1"));
            // addu $s0, $s7, $t4
            input.add(instFact.CreateAddu(16, 23, 12));
            // blt  $s0,$t0,label1
            input.add(instFact.CreateBlt(16, 8, "label1"));
            // addiu $s1,$s2,0xF00000
            input.add(instFact.CreateAddiu(18, 17, 0xF00000));

            // Phase 1
            Instruction[] phase1_expected = {
                    instFact.CreateAddu(8, 0, "label1"), // label1: addu $t0, $zero, $zero
                    instFact.CreateAddu(16, 23, 12), // addu $s0, $s7, $t4
                    instFact.CreateSlt(16, 8),  // slt $at,$s0,$t0
                    instFact.CreateBne("label1"),     // bne $at,$zero,label1
                    instFact.CreateLui(0x00F0), // lui $at, 0x00F0
                    instFact.CreateOri(0x0000), // ori $at, $at 0x0000
                    instFact.CreateAddu(17,18) // addu $s1,$s2,$at
            };

            // Phase 2
            Instruction[] phase2_expected = {
                    instFact.CreateAddu(8,0, 0, "label1"),//new Instruction(2,8,0,0,0,0,0,1,0),
                    instFact.CreateAddu(16,23,12),//new Instruction(2,16,23,12,0,0,0,0,0),
                    instFact.CreateSlt(16,8),//new Instruction(8,1,16,8,0,0,0,0,0),
                    instFact.CreateBne(0xfffffffc, "label1"),//new Instruction(6,0,1,0,0xfffffffc,0,0,0,1),
                    instFact.CreateLui(0x00F0),// new Instruction(9,0,0,1,0x00F0,0,0,0,0),
                    instFact.CreateOri(0x0000),// new Instruction(10,0,1,1,0x0000,0,0,0,0),
                    instFact.CreateAddu(17,18)// new Instruction(2,17,18,1,0,0,0,0,0)
            };

            // Phase 3
            Integer[] phase3_expected = {
                    // HINT: to get these, type the input program into MARS, assemble, and copy the binary values into your test case
                    0x00004021,
                    0x02ec8021,
                    0x0208082a,
                    0x1420fffc,
                    0x3c0100f0,
                    0x34210000,
                    0x02418821
            };


            testHelper(input,
                    phase1_expected,
                    phase2_expected,
                    phase3_expected);
        }

        @Test
        public void test2() {
            /* Fill in your additional test case here! */

            //testHelper(...);
        }
}