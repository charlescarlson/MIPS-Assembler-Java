package edu.uiowa.cs;

public class InstructionFactory {
    public Instruction CreateCopy(Instruction instruction){
        return instruction.copy();
    }
    
    public Instruction CreateLui(int immediate, String label){
        return new Instruction(Instruction.ID.lui, 0, 0, 1, immediate >>> 16, 0, 0, label, "");
    }
    
    public Instruction CreateOri(int immediate){
        return new Instruction(Instruction.ID.ori, 0, 1, 1, immediate & 0xFFFF, 0, 0, "", "");
    }
    
    public Instruction CreateAddu(int rt, int rs){
        return new Instruction(Instruction.ID.addu, rt, rs, 1, 0, 0, 0, "", "");
    }
    
    public Instruction CreateOr(int rt, int rs){
        return new Instruction(Instruction.ID.or, rt, rs, 1, 0, 0, 0, "", "");
    }
    
    public Instruction CreateSlt(int rs, int rt, String label){
        return new Instruction(Instruction.ID.slt, 1, rs, rt, 0, 0, 0, label, "");
    }
    
    public Instruction CreateBne(String branch_label){
        return new Instruction(Instruction.ID.bne, 0, 1, 0, 0, 0, 0, "", branch_label);
    }
    
    public Instruction CreateBeq(String branch_label){
        return new Instruction(Instruction.ID.beq, 0, 1, 0, 0, 0, 0, "", branch_label);
    }
}
