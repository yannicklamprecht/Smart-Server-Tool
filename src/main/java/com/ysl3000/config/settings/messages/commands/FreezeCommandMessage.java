package com.ysl3000.config.settings.messages.commands;

/**
 * Created by ysl3000
 */
public class FreezeCommandMessage extends CommandConfig {
  private String youAreNotAllowedToMove = "You're now allowed to move";
  private String wrongInput = "&4Wrong Input";
  private String paramterNotANumber = "Your given parameter {parameter} is not a number.";
  private String freezeSelfMessage = "You froze yourself.";
  private String targetFreezeMessage = "You was frozen by {player_display_name}.";
  private String senderFreezeMessage = "You froze {player_display_name}.";

  public FreezeCommandMessage(){
    super("freeze", "freezes a player",
        "/freeze <player>", "sst.freeze");
  }

  public String getYouAreNotAllowedToMove() {
    return youAreNotAllowedToMove;
  }

  public void setYouAreNotAllowedToMove(String youAreNotAllowedToMove) {
    this.youAreNotAllowedToMove = youAreNotAllowedToMove;
  }

  public String getWrongInput() {
    return wrongInput;
  }

  public void setWrongInput(String wrongInput) {
    this.wrongInput = wrongInput;
  }

  public String getParamterNotANumber() {
    return paramterNotANumber;
  }

  public void setParamterNotANumber(String paramterNotANumber) {
    this.paramterNotANumber = paramterNotANumber;
  }

  public String getFreezeSelfMessage() {
    return freezeSelfMessage;
  }

  public void setFreezeSelfMessage(String freezeSelfMessage) {
    this.freezeSelfMessage = freezeSelfMessage;
  }

  public String getTargetFreezeMessage() {
    return targetFreezeMessage;
  }

  public void setTargetFreezeMessage(String targetFreezeMessage) {
    this.targetFreezeMessage = targetFreezeMessage;
  }

  public String getSenderFreezeMessage() {
    return senderFreezeMessage;
  }

  public void setSenderFreezeMessage(String senderFreezeMessage) {
    this.senderFreezeMessage = senderFreezeMessage;
  }
}
