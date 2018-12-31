package com.ysl3000.config.settings;

/**
 * Created by ysl3000
 */
public class CommandConfig {

  private String name="";
  private String description="";
  private String usageMessage="";
  private String permission="";


  public CommandConfig() {
  }

  public CommandConfig(String name, String description, String usageMessage,
      String permission) {
    this.name = name;
    this.description = description;
    this.usageMessage = usageMessage;
    this.permission = permission;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getUsageMessage() {
    return usageMessage;
  }

  public void setUsageMessage(String usageMessage) {
    this.usageMessage = usageMessage;
  }

  public String getPermission() {
    return permission;
  }

  public void setPermission(String permission) {
    this.permission = permission;
  }
}
